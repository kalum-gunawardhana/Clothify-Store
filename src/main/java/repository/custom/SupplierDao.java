package repository.custom;

import entity.SupplierEntity;
import javafx.collections.ObservableList;
import model.Supplier;
import model.SupplierTable;
import repository.CrudDao;

import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity,Integer> {
    ObservableList<SupplierTable> getAllSupplier();
    boolean deleteSupplier(Integer supplierId);
    boolean updateSupplier(SupplierEntity supplierEntity);
    boolean addSupplier(SupplierEntity supplierEntity);
    List<String> getAllCSupllierIds();
}
