package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeTable {
    private int employeeId;
    private String name;
    private String email;
    private String role;
    private Integer adminId;
}
