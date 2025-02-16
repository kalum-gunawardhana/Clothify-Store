package repository.custom;

import entity.EmployeeEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeTable;
import model.User;
import repository.CrudDao;

import java.sql.SQLException;

public interface EmployeeDao extends CrudDao<EmployeeEntity,Integer> {
    boolean deleteEmployee(Integer userId, Integer employeeId) throws SQLException;
    boolean updateEmployee(UserEntity userEntity, EmployeeEntity employeeEntity) throws SQLException;
    User getSelectUser(Integer userId);
    boolean addEmployee(UserEntity userEntity, EmployeeEntity employeeEntity) throws SQLException;
    ObservableList<EmployeeTable> getAllEmployee();
}
