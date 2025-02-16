package service.custom.impl;

import entity.ProductEntity;
import javafx.collections.ObservableList;
import model.Product;
import model.ProductTable;
import model.SupplierTable;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.*;
import service.custom.ProductService;
import util.DaoType;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;

    private ProductServiceImpl(){}

    public static ProductServiceImpl getInstance() {
        return instance==null?instance=new ProductServiceImpl():instance;
    }

    UserDao userDao= DaoFactory.getInstance().getDaoType(DaoType.USER);
    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrdersDao ordersDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public List<String> getAllItemNames() {
        return productDao.getAllItemNames();
    }

    @Override
    public List<String> getAllCSizes() {
        return productDao.getAllCSizes();
    }

    @Override
    public List<String> getAllCategorys() {
        return productDao.getAllCategorys();
    }

    @Override
    public ObservableList<ProductTable> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        return productDao.deleteProduct(productId);
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity productEntity = new ModelMapper().map(product, ProductEntity.class);
        return productDao.updateProduct(productEntity);
    }

    @Override
    public boolean addProducts(Product product) {
        ProductEntity productEntity = new ModelMapper().map(product, ProductEntity.class);
        return productDao.addProducts(productEntity);
    }
}
