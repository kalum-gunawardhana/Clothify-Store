package controller;

import controller.service.LoginService;
import model.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements LoginService {
    public static LoginController instance;

    private LoginController() {}

    public static LoginController getInstance(){
        if(instance == null){
            instance=new LoginController();
        }
        return instance;
    }

    @Override
    public String getLoginInfo(String email, String password) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM user WHERE Email = ?");
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

    @Override
    public boolean forgetPassword(String email, String password) {
        //System.out.println(email);
        //System.out.println(password);
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE user SET Password = ? WHERE Email = ?");
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,email);

            int update = preparedStatement.executeUpdate();
            //System.out.println(update);
            if (update>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
