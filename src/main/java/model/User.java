package model;

import lombok.*;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private Timestamp regDate;
}
