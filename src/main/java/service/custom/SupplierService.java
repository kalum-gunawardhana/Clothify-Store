package service.custom;

import javafx.collections.ObservableList;
import model.Supplier;
import model.SupplierTable;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    ObservableList<SupplierTable> getAllSupplier();
    boolean deleteSupplier(Integer supplierId);
    boolean updateSupplier(Supplier supplier);
    boolean addSupplier(Supplier supplier);
    List<String> getAllCSupllierIds();
}
