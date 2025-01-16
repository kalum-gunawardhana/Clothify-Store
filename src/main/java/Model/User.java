package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private Timestamp regDate;
}
