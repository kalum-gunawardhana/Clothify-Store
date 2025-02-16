package service.custom.impl;

import javafx.collections.ObservableList;
import model.User;
import model.UserTable;
import repository.DaoFactory;
import repository.custom.*;
import service.custom.UserService;
import util.DaoType;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        return instance==null?instance=new UserServiceImpl():instance;
    }

    UserDao userDao= DaoFactory.getInstance().getDaoType(DaoType.USER);
    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrdersDao ordersDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public ObservableList<UserTable> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public boolean deleteUser(String email) {
        return userDao.deleteUser(email);
    }

    @Override
    public boolean updateUser(String name, String email, String password, String role) {
        return userDao.updateUser(name, email, password, role);
    }

    @Override
    public User searchUser(String email) {
        return userDao.searchUser(email);
    }

    @Override
    public boolean addUser(String name, String email, String password, String role) {
        return userDao.addUser(name, email, password, role);
    }

    @Override
    public boolean forgetPassword(String email, String password) {
        return userDao.forgetPassword(email, password);
    }

    @Override
    public String getLoginInfo(String email, String password) {
        return userDao.getLoginInfo(email, password);
    }
}
