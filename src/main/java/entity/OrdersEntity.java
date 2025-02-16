package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.OrderProduct;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersEntity {
    private Integer orderId;
    private Integer customerId;
    private Integer employeeId;
    private String orderDate;
    private Double totalPrice;
    private String paymentType;
    private List<OrderProduct> orderItems;
}
