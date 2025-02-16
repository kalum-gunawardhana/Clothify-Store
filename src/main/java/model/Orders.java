package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private Integer customerId;
    private Integer employeeId;
    private String orderDate;
    private Double totalPrice;
    private String paymentType;
    private List<OrderProduct> orderItems;
}
