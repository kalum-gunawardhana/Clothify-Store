package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private Timestamp regDate;
}
