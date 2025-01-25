package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {
    private String name;
    private String category;
    private String size;
    private Double price;
    private Integer Qty;
    private String supplier;
}
