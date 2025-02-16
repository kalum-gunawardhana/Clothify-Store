package repository.custom.impl;

import DBConnection.connection;
import entity.EmployeeEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeTable;
import model.User;
import repository.custom.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean add(EmployeeEntity entity) {
        return false;
    }

    @Override
    public EmployeeEntity search(Integer integer) {
        return null;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean deleteEmployee(Integer userId, Integer employeeId) throws SQLException {
        Connection connection = DBConnection.connection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE EmployeeID = ?");
            preparedStatement.setInt(1,employeeId);
            boolean b = preparedStatement.executeUpdate() > 0;

            if (b){
                PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM user WHERE UserID = ?");
                preparedStatement1.setInt(1,userId);
                boolean b1 = preparedStatement1.executeUpdate() > 0;

                if (b1){
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateEmployee(UserEntity userEntity, EmployeeEntity employeeEntity) throws SQLException {
        Connection connection = DBConnection.connection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET Name = ?, Email = ?, Password = ?, Role = ? WHERE UserID = ?");
            preparedStatement.setObject(1,userEntity.getName());
            preparedStatement.setObject(2,userEntity.getEmail());
            preparedStatement.setObject(3,userEntity.getPassword());
            preparedStatement.setObject(4,userEntity.getRole());
            preparedStatement.setObject(5,userEntity.getUserId());
            boolean b = preparedStatement.executeUpdate() > 0;

            if (b){
                PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE employee SET Name = ?, Email = ?, Role = ?, AdminID = ? WHERE EmployeeID = ?");
                preparedStatement1.setObject(1,employeeEntity.getName());
                preparedStatement1.setObject(2,employeeEntity.getEmail());
                preparedStatement1.setObject(3,employeeEntity.getRole());
                preparedStatement1.setObject(4,employeeEntity.getAdminId());
                preparedStatement1.setObject(5,employeeEntity.getEmployeeId());
                boolean b1 = preparedStatement1.executeUpdate() > 0;

                if (b1){
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public User getSelectUser(Integer userId) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select * from user where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Integer userID = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String role = resultSet.getString("Role");
                return new User(userID,name,email,password,role,null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean addEmployee(UserEntity userEntity, EmployeeEntity employeeEntity) throws SQLException {
        Connection connection = DBConnection.connection.getInstance().getConnection();
        Integer lastUserId=-1;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO user (Name, Email, Password, Role, RegDate) VALUES (?, ?, ?, ?, NOW())");
            preparedStatement1.setObject(1, userEntity.getName());
            preparedStatement1.setObject(2, userEntity.getEmail());
            preparedStatement1.setObject(3, userEntity.getPassword());
            preparedStatement1.setObject(4, userEntity.getRole());
            boolean b = preparedStatement1.executeUpdate() > 0;

            if (b){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(UserID) FROM user");
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    lastUserId = resultSet.getInt(1);
                    if (lastUserId>-1){
                        PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO employee (Name, Email, Role, AdminID) VALUES (?, ?, ?, ?)");
                        preparedStatement2.setObject(1, employeeEntity.getName());
                        preparedStatement2.setObject(2, employeeEntity.getEmail());
                        preparedStatement2.setObject(3, employeeEntity.getRole());
                        preparedStatement2.setInt(4, lastUserId);
                        boolean b1 = preparedStatement2.executeUpdate() > 0;

                        if (b1){
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ObservableList<EmployeeTable> getAllEmployee() {
        ObservableList<EmployeeTable> observableList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select * from employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer employeeID = resultSet.getInt("EmployeeID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String role = resultSet.getString("Role");
                Integer adminID = resultSet.getInt("AdminID");

                observableList.add(new EmployeeTable(employeeID,name,email,role,adminID));
            }
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
