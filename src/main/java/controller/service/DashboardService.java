package controller.service;

import javafx.collections.ObservableList;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DashboardService {
    boolean addEmp(String name, String email, String role, Integer adminId);
    List<String> getEmpEmail();
    Object getEmpData(String email);
    boolean updateEmp(String email, String name, String role, int adminId);
    boolean deleteEmployeeByEmail(String email);
    boolean addSupplier(String name, String company, String email, String item);
    List<String> getSupplierEmails();
    Object getSupplierData(String email);
    boolean updateSupllier(String email, String name, String company, String item);
    boolean deleteSupplier(String email);
    List<String> setCategory();
    List<String> setSize();
    List<String> setSupplier();
    boolean addInventory(String name,String category,String size,Double price,Integer Qty,String supplier);
    List<String> getProduct();
    Inventory searchProduct(String name);
    boolean updateInventory(String name,String category,String size,Double price,Integer Qty,String supplier);
    boolean deleteInventory(String ProductName);
    List<String> getOrderId();
    List<String> getEmployeeId();
    String selectEmployoleeName(String id);
    List<String> getProductId();
    List<String> getSelectProduct(String id);
    boolean placeOrder(Order order) throws SQLException;
    List<String> getCustomerId();
    String getSelectCustomerName(String id);
    boolean addOrderProduct(ArrayList<OrderProduct> orderProducts);
    boolean addToOrderProductTable(OrderProduct orderProduct);
    boolean updateProductStock(ArrayList<OrderProduct> orderProducts);
    ObservableList<UserTable> getUsers();
    boolean addUser(String name, String email, String password, String role);
    User searchUser(String email);
    boolean updateUser(String name, String email, String password, String role);
    boolean deleteUser(String email);
}
