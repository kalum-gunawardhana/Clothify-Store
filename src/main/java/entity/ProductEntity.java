package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductEntity {
    private Integer productId;
    private String productName;
    private String category;
    private String size;
    private Double price;
    private Integer qty;
    private Integer supplierId;
}
