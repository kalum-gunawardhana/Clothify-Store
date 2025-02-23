package repository.custom.impl;

import DBConnection.connection;
import entity.OrderDetailTableEntity;
import entity.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderProduct;
import repository.custom.OrdersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public boolean add(OrdersEntity entity) {
        return false;
    }

    @Override
    public OrdersEntity search(Integer integer) {
        return null;
    }

    @Override
    public boolean update(OrdersEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<OrdersEntity> getAll() {
        return List.of();
    }

    @Override
    public List<String> getCustomerId() {
        List<String> customerIdList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select id from customer order by id asc");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customerIdList.add(resultSet.getString("id"));
            }
            return customerIdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getSelectCustomerName(String id) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select name from customer where id=?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean placeOrder(OrdersEntity ordersEntity) throws SQLException {
        Connection connection = DBConnection.connection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (OrderDate, TotalCost, PaymentType, EmployeeID, CustomerID) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setObject(1,ordersEntity.getOrderDate());
            preparedStatement.setObject(2,ordersEntity.getTotalPrice());
            preparedStatement.setObject(3,ordersEntity.getPaymentType());
            preparedStatement.setObject(4,ordersEntity.getEmployeeId());
            preparedStatement.setObject(5,ordersEntity.getCustomerId());
            boolean isOrderAdded = preparedStatement.executeUpdate() > 0;

            if (isOrderAdded){
                boolean addedOrderProduct = addOrderProduct((ArrayList<OrderProduct>) ordersEntity.getOrderItems());
                if (addedOrderProduct){
                    boolean updatedProductStock = updateProductStock((ArrayList<OrderProduct>) ordersEntity.getOrderItems());
                    if (updatedProductStock){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean addOrderProduct(ArrayList<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts){
            boolean addedToOrderProductTable = addToOrderProductTable(orderProduct);
            if (!addedToOrderProductTable){
                return false;
            }
        }
        return !orderProducts.isEmpty();
    }

    @Override
    public boolean addToOrderProductTable(OrderProduct orderProduct) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("INSERT INTO orderproduct (OrderID, ProductID, Quantity) VALUES (?, ?, ?)");
            preparedStatement.setObject(1,orderProduct.getOrderID());
            preparedStatement.setObject(2,orderProduct.getProductID());
            preparedStatement.setObject(3,orderProduct.getQuantity());

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProductStock(ArrayList<OrderProduct> orderProducts) {
        try {
            Connection connection = DBConnection.connection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE product SET Quantity = Quantity - ? WHERE ProductID = ?"
            );

            for (OrderProduct orderProduct : orderProducts) {
                preparedStatement.setInt(1, orderProduct.getQuantity());
                preparedStatement.setInt(2, orderProduct.getProductID());
                preparedStatement.addBatch();
            }

            int[] updateCounts = preparedStatement.executeBatch();
            return updateCounts.length == orderProducts.size();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getProductId() {
        List<String> getProductIdList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("SELECT ProductID FROM product ORDER BY ProductID ASC");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getProductIdList.add(resultSet.getString("ProductID"));

            }
            return getProductIdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getSelectProduct(String id) {
        List<String> productDetailList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select ProductName,Size,Price, SupplierID from product where ProductID=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String productName = resultSet.getString("ProductName");
                String size = resultSet.getString("Size");
                String price = resultSet.getString("Price");
                String supplierID = resultSet.getString("SupplierID");

                productDetailList.add(productName);
                productDetailList.add(size);
                productDetailList.add(price);
                productDetailList.add(supplierID);
            }
            return productDetailList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getEmployeeId() {
        List<String> getEmployeeIdList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select EmployeeID from employee order by EmployeeID asc");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getEmployeeIdList.add(resultSet.getString("EmployeeID"));
            }
            return getEmployeeIdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String selectEmployoleeName(String id) {
        String employeeName = null;

        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select Name from employee where EmployeeID=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return employeeName = resultSet.getString("Name"); // Get employee name
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<String> getOrderId() {
        List<String> orderIdList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("SELECT orderid FROM orders ORDER BY orderid DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                orderIdList.add(resultSet.getString("orderid"));
                return orderIdList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public ObservableList<OrderDetailTableEntity> getOrderDetails() {
        ObservableList<OrderDetailTableEntity> observableList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select * from orders");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                OrderDetailTableEntity orderDetailTableEntity = new OrderDetailTableEntity(resultSet.getInt("OrderID"), resultSet.getDate("OrderDate"), resultSet.getDouble("TotalCost"), resultSet.getString("PaymentType"), resultSet.getInt("EmployeeID"), resultSet.getInt("CustomerID"));
                observableList.add(orderDetailTableEntity);
            }
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
