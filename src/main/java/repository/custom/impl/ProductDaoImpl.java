package repository.custom.impl;

import DBConnection.connection;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.ProductTable;
import model.SupplierTable;
import repository.custom.ProductDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean add(ProductEntity entity) {
        return false;
    }

    @Override
    public ProductEntity search(Integer integer) {
        return null;
    }

    @Override
    public boolean update(ProductEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<ProductEntity> getAll() {
        return List.of();
    }

    @Override
    public List<String> getAllItemNames() {
        List<String> getAllItemNames=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select item_name from item");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getAllItemNames.add(resultSet.getString("item_name"));
            }
            return getAllItemNames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getAllCSizes() {
        List<String> allSizeList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select SizeName from size");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allSizeList.add(resultSet.getString("SizeName"));
            }
            return allSizeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getAllCategorys() {
        List<String> allCategoryList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select CategoryName from category");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allCategoryList.add(resultSet.getString("CategoryName"));
            }
            return allCategoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductTable> getAllProducts() {
        ObservableList<ProductTable> observableList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select * from product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String category = resultSet.getString("Category");
                String size = resultSet.getString("Size");
                Double price = resultSet.getDouble("Price");
                Integer quantity = resultSet.getInt("Quantity");
                Integer supplierID = resultSet.getInt("SupplierID");

                observableList.add(new ProductTable(productID,productName,category,size,price,quantity,supplierID));
            }
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProduct(Integer productID) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("DELETE FROM product WHERE ProductID = ?");
            preparedStatement.setInt(1,productID);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(ProductEntity productEntity) {
        try {
            //System.out.println(product.toString());
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("UPDATE product SET ProductName=?, Category=?, Size=?, Price=?, Quantity=?, SupplierID=? WHERE ProductID=?");
            preparedStatement.setObject(1,productEntity.getProductName());
            preparedStatement.setObject(2,productEntity.getCategory());
            preparedStatement.setObject(3,productEntity.getSize());
            preparedStatement.setObject(4,productEntity.getPrice());
            preparedStatement.setObject(5,productEntity.getQty());
            preparedStatement.setObject(6,productEntity.getSupplierId());
            preparedStatement.setObject(7,productEntity.getProductId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addProducts(ProductEntity productEntity) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("INSERT INTO product (ProductName, Category, Size, Price, Quantity, SupplierID) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setObject(1,productEntity.getProductName());
            preparedStatement.setObject(2,productEntity.getCategory());
            preparedStatement.setObject(3,productEntity.getSize());
            preparedStatement.setObject(4,productEntity.getPrice());
            preparedStatement.setObject(5,productEntity.getQty());
            preparedStatement.setObject(6,productEntity.getSupplierId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
