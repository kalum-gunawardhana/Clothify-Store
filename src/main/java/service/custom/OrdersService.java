package service.custom;

import javafx.collections.ObservableList;
import model.OrderDetailTable;
import model.Orders;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService extends SuperService {
    List<String> getCustomerId();
    String getSelectCustomerName(String id);
    boolean placeOrder(Orders orders) throws SQLException;
    List<String> getProductId();
    List<String> getSelectProduct(String id);
    List<String> getEmployeeId();
    String selectEmployoleeName(String id);
    List<String> getOrderId();
    ObservableList<OrderDetailTable> getOrderDetail();
}
