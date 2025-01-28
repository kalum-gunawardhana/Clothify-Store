package OwnerController;

import DBConnection.DBConnection;
import Model.Employee;
import Model.Inventory;
import Model.Supplier;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerDashboardController implements OwnerDashboardService {
    public static OwnerDashboardController instance;

    private OwnerDashboardController() {
    }

    public static OwnerDashboardController getInstance() {
        if (instance == null) {
            instance = new OwnerDashboardController();
        }
        return instance;
    }

    @Override
    public boolean addEmp(String name, String email, String role, Integer adminId) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO employee (Name, Email, Role, AdminID) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, role);
            preparedStatement.setInt(4, adminId);

            boolean b = preparedStatement.executeUpdate() > 0;
            if (b) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getEmpEmail() {
        List<String> emailList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT Email FROM employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emailList.add(resultSet.getString("Email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emailList;
    }

    @Override
    public Object getEmpData(String email) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM employee WHERE Email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getInt("AdminID")
                );
                return employee;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmp(String email, String name, String role, int adminId) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE employee SET Name = ?, Role = ?, AdminID = ? WHERE Email = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, role);
            preparedStatement.setInt(3, adminId);
            preparedStatement.setString(4, email);

            boolean b = preparedStatement.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmployeeByEmail(String email) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM employee WHERE Email = ?");
            preparedStatement.setString(1, email);
            boolean b = preparedStatement.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addSupplier(String name, String company, String email, String item) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO supplier (Name, Company, Email, Item) VALUES (?, ?, ?, ?)");

            // Set the values for the placeholders
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, company);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, item);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if a row was inserted
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getSupplierEmails() {
        List<String> suppEmailList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT Email FROM supplier");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                suppEmailList.add(resultSet.getString("Email"));
            }
            return suppEmailList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getSupplierData(String email) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM supplier WHERE Email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Supplier supplier = new Supplier(null, rs.getString("Name"), rs.getString("Company"), rs.getString("Email"), rs.getNString("Item"));
                return supplier;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSupllier(String email, String name, String company, String item) {
        try {
            PreparedStatement pstmt = DBConnection.getInstance().getConnection().prepareStatement("UPDATE supplier SET name = ?, company = ?, item = ? WHERE email = ?");
            // Set the parameters for the prepared statement
            pstmt.setString(1, name);
            pstmt.setString(2, company);
            pstmt.setString(3, item);
            pstmt.setString(4, email); // Use the email as the identifier to update the correct supplier

            // Execute the update and return true if the update is successful
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(String email) {
        try {
            PreparedStatement pstmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM supplier WHERE email = ?");

            pstmt.setString(1, email);  // Set email parameter
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> setCategory() {
        List<String> categoryList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT CategoryName FROM category");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(resultSet.getString("CategoryName"));
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> setSize() {
        List<String> sizeList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT SizeName FROM size");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sizeList.add(resultSet.getString("SizeName"));
            }
            return sizeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> setSupplier() {
        List<String> supplierList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT SupplierID FROM supplier");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                supplierList.add(resultSet.getString("SupplierID"));
            }
            return supplierList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addInventory(String name, String category, String size, Double price, Integer Qty, String supplier) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO product (ProductName, Category, Size, Price, Quantity, SupplierID) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, size);
            preparedStatement.setDouble(4,price);
            preparedStatement.setInt(5, Qty);
            preparedStatement.setString(6, supplier);

            boolean b = preparedStatement.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getProduct() {
        List<String> productList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT ProductName FROM product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(resultSet.getString("ProductName"));
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Inventory searchProduct(String productName) {
        Inventory inventory = null;
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM product WHERE ProductName = ?");
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                inventory = new Inventory();
                inventory.setName(resultSet.getString("ProductName"));
                inventory.setCategory(resultSet.getString("Category"));
                inventory.setSize(resultSet.getString("Size"));
                inventory.setPrice(resultSet.getDouble("Price"));
                inventory.setQty(resultSet.getInt("Quantity"));
                inventory.setSupplier(resultSet.getString("SupplierID"));
                return inventory;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateInventory(String name, String category, String size, Double price, Integer Qty, String supplier) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE product SET Category = ?, Size = ?, Price = ?, Quantity = ?, SupplierID = ? WHERE ProductName = ?");
            // Set query parameters
            preparedStatement.setString(1, category);
            preparedStatement.setString(2, size);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, Qty);
            preparedStatement.setString(5, supplier);
            preparedStatement.setString(6, name);

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Return true if update was successful
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteInventory(String ProductName) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM product WHERE ProductName = ?");
            // Set the name parameter in the query
            preparedStatement.setString(1, ProductName);

            // Execute the delete
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0; // Return true if delete was successful
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
