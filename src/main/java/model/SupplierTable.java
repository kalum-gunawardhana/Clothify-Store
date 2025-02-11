package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierTable {
    private Integer supplierID;
    private String name;
    private String company;
    private String email;
    private String item;
}
