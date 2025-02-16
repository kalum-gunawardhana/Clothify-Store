package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class InventoryEntity {
    private String name;
    private String category;
    private String size;
    private Double price;
    private Integer Qty;
    private String supplier;
}
