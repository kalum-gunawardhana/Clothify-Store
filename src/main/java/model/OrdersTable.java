package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
public class OrdersTable {
    private Integer productId;
    private String productName;
    private String productSize;
    private Integer productQty;
    private Double productPrice;
}
