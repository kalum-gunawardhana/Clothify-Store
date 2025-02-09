package controller;

import model.Inventory;

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
}
