package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private Integer supplierID; // Primary key
    private String name;    // Supplier's name
    private String company; // Company name
    private String email;   // Unique email
    private String item;    // Item supplied
}
