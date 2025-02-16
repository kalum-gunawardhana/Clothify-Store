package service.custom;

import javafx.collections.ObservableList;
import model.Product;
import model.ProductTable;
import model.SupplierTable;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    List<String> getAllItemNames();
    List<String> getAllCSizes();
    List<String> getAllCategorys();
    ObservableList<ProductTable> getAllProducts();
    boolean deleteProduct(Integer productId);
    boolean updateProduct(Product product);
    boolean addProducts(Product product);
}
