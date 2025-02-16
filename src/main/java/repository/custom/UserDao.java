package repository.custom;

import entity.UserEntity;
import javafx.collections.ObservableList;
import model.User;
import model.UserTable;
import repository.CrudDao;

public interface UserDao extends CrudDao<UserEntity,Integer> {
    ObservableList<UserTable> getUsers();
    boolean deleteUser(String email);
    boolean updateUser(String name, String email, String password, String role);
    User searchUser(String email);
    boolean addUser(String name, String email, String password, String role);
    boolean forgetPassword(String email, String password);
    String getLoginInfo(String email, String password);
}
