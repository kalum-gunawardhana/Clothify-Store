package OwnerController;

import Model.Employee;
import Model.User;

import java.util.List;

public interface OwnerDashboardService {
    boolean addEmp(String name, String email, String role, Integer adminId);
    List<String> getEmpEmail();
    Object getEmpData(String email);
    boolean updateEmp(String email, String name, String role, int adminId);
    boolean deleteEmployeeByEmail(String email);
}
