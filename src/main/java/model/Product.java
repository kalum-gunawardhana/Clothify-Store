package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String productId;
    private String productName;
    private String category;
    private String size;
    private Double price;
    private Integer qty;
    private Integer supplierId;
}
