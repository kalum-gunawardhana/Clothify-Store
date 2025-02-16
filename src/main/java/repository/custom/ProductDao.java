package repository.custom;

import entity.ProductEntity;
import javafx.collections.ObservableList;
import model.Product;
import model.ProductTable;
import model.SupplierTable;
import repository.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity,Integer> {
    List<String> getAllItemNames();
    List<String> getAllCSizes();
    List<String> getAllCategorys();
    ObservableList<ProductTable> getAllProducts();
    boolean deleteProduct(Integer  productID);
    boolean updateProduct(ProductEntity productEntity);
    boolean addProducts(ProductEntity productEntity);
}
