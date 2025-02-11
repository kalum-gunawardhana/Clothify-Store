package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductTable {
    private Integer productID;
    private String productName;
    private String category;
    private String size;
    private Double price;
    private Integer qty;
    private Integer supplierId;
}
