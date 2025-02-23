package service.custom.impl;

import entity.OrderDetailTableEntity;
import entity.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetailTable;
import model.Orders;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.*;
import service.custom.OrdersService;
import util.DaoType;

import java.sql.SQLException;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    private static OrdersServiceImpl instance;

    private OrdersServiceImpl(){}

    public static OrdersServiceImpl getInstance() {
        return instance==null?instance=new OrdersServiceImpl():instance;
    }

    UserDao userDao= DaoFactory.getInstance().getDaoType(DaoType.USER);
    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrdersDao ordersDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public List<String> getCustomerId() {
        return ordersDao.getCustomerId();
    }

    @Override
    public String getSelectCustomerName(String id) {
        return ordersDao.getSelectCustomerName(id);
    }

    @Override
    public boolean placeOrder(Orders orders) throws SQLException {
        OrdersEntity ordersEntity = new ModelMapper().map(orders, OrdersEntity.class);
        return ordersDao.placeOrder(ordersEntity);
    }

    @Override
    public List<String> getProductId() {
        return ordersDao.getProductId();
    }

    @Override
    public List<String> getSelectProduct(String id) {
        return ordersDao.getSelectProduct(id);
    }

    @Override
    public List<String> getEmployeeId() {
        return ordersDao.getEmployeeId();
    }

    @Override
    public String selectEmployoleeName(String id) {
        return ordersDao.selectEmployoleeName(id);
    }

    @Override
    public List<String> getOrderId() {
        return ordersDao.getOrderId();
    }

    @Override
    public ObservableList<OrderDetailTable> getOrderDetail() {
        ObservableList<OrderDetailTable> observableList = FXCollections.observableArrayList();

        ObservableList<OrderDetailTableEntity> orderDetails = ordersDao.getOrderDetails();

        orderDetails.forEach(orderDetailTableEntity -> {
            observableList.add(new ModelMapper().map(orderDetailTableEntity, OrderDetailTable.class));
        });
        return observableList;
    }
}
