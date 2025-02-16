package service.custom;

import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeTable;
import model.User;
import service.SuperService;

import java.sql.SQLException;

public interface EmployeeService extends SuperService {
    boolean deleteEmployee(Integer userId, Integer employeeId) throws SQLException;
    boolean updateEmployee(User user, Employee employee) throws SQLException;
    User getSelectUser(Integer adminId);
    boolean addEmployee(User user, Employee employee) throws SQLException;
    ObservableList<EmployeeTable> getAllEmployee();
}
