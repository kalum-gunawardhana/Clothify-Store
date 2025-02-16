package service.custom.impl;

import entity.SupplierEntity;
import javafx.collections.ObservableList;
import model.Supplier;
import model.SupplierTable;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.*;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    private static SupplierServiceImpl instance;

    private SupplierServiceImpl(){}

    public static SupplierServiceImpl getInstance() {
        return instance==null?instance=new SupplierServiceImpl():instance;
    }

    UserDao userDao= DaoFactory.getInstance().getDaoType(DaoType.USER);
    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrdersDao ordersDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public ObservableList<SupplierTable> getAllSupplier() {
        return supplierDao.getAllSupplier();
    }

    @Override
    public boolean deleteSupplier(Integer supplierId) {
        return supplierDao.deleteSupplier(supplierId);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.updateSupplier(supplierEntity);
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.addSupplier(supplierEntity);
    }

    @Override
    public List<String> getAllCSupllierIds() {
        return supplierDao.getAllCSupllierIds();
    }
}
