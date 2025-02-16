package service.custom.impl;

import entity.EmployeeEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeTable;
import model.User;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.*;
import service.custom.EmployeeService;
import util.DaoType;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    private static EmployeeServiceImpl instance;

    private EmployeeServiceImpl(){}

    public static EmployeeServiceImpl getInstance() {
        return instance==null?instance=new EmployeeServiceImpl():instance;
    }

    UserDao userDao= DaoFactory.getInstance().getDaoType(DaoType.USER);
    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrdersDao ordersDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public boolean deleteEmployee(Integer userId, Integer employeeId) throws SQLException {
        return employeeDao.deleteEmployee(userId, employeeId);
    }

    @Override
    public boolean updateEmployee(User user, Employee employee) throws SQLException {
        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.updateEmployee(userEntity, employeeEntity);
    }

    @Override
    public User getSelectUser(Integer adminId) {
        return employeeDao.getSelectUser(adminId);
    }

    @Override
    public boolean addEmployee(User user, Employee employee) throws SQLException {
        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.addEmployee(userEntity, employeeEntity);
    }

    @Override
    public ObservableList<EmployeeTable> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }
}
