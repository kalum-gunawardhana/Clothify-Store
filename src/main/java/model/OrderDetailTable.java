package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailTable {
    private Integer orderId;
    private Date orderDate;
    private Double totalCost;
    private String paymentType;
    private Integer employeeId;
    private Integer customerId;
}
