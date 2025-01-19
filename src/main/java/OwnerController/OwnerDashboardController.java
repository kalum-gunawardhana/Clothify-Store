package OwnerController;

import DBConnection.DBConnection;
import LoginController.LoginController;
import Model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerDashboardController implements OwnerDashboardService{
    public static OwnerDashboardController instance;

    private OwnerDashboardController() {}

    public static OwnerDashboardController getInstance(){
        if(instance == null){
            instance=new OwnerDashboardController();
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
            if (b){
                return true;
            }else {
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
}
