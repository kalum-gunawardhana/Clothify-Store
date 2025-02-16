package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EmployeeEntity {
    private Integer employeeId;
    private String name;
    private String email;
    private String role;
    private Integer adminId;
}
