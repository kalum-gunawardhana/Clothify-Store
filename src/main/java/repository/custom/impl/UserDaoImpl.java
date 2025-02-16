package repository.custom.impl;

import DBConnection.connection;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.UserTable;
import repository.custom.UserDao;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean add(UserEntity entity) {
        return false;
    }

    @Override
    public UserEntity search(Integer integer) {
        return null;
    }

    @Override
    public boolean update(UserEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<UserEntity> getAll() {
        return List.of();
    }

    @Override
    public ObservableList<UserTable> getUsers() {
        ObservableList<UserTable> observableList = FXCollections.observableArrayList();

        String query = "SELECT * FROM user";

        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String role = resultSet.getString("Role");
                Date regDate = resultSet.getDate("RegDate");

                observableList.add(new UserTable(name, email, password, role, regDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    @Override
    public boolean deleteUser(String email) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("DELETE FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(String name, String email, String password, String role) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("UPDATE user SET Name = ?, Email = ?, Password = ?, Role = ? WHERE Email = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, email);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User searchUser(String email) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("SELECT * FROM user WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role"),
                        resultSet.getTimestamp("RegDate")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean addUser(String name, String email, String password, String role) {
        String query = "INSERT INTO user (Name, Email, Password, Role, RegDate) VALUES (?, ?, ?, ?, NOW())";

        try {
            Connection connection = DBConnection.connection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean forgetPassword(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("UPDATE user SET Password = ? WHERE Email = ?");
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,email);

            int update = preparedStatement.executeUpdate();
            if (update>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getLoginInfo(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("SELECT * FROM user WHERE Email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String emailFromDB = resultSet.getString("Email");
                String passwords = resultSet.getString("Password");
                String role = resultSet.getString("Role");
                if (passwords.equals(password)){
                    //System.out.println(role);
                    return role;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
