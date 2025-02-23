package repository.custom;

import entity.OrderDetailTableEntity;
import entity.OrdersEntity;
import javafx.collections.ObservableList;
import model.OrderProduct;
import model.Orders;
import repository.CrudDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrdersDao extends CrudDao<OrdersEntity,Integer> {
    List<String> getCustomerId();
    String getSelectCustomerName(String id);
    boolean placeOrder(OrdersEntity ordersEntity) throws SQLException;
    boolean addOrderProduct(ArrayList<OrderProduct> orderProducts);
    boolean addToOrderProductTable(OrderProduct orderProduct);
    boolean updateProductStock(ArrayList<OrderProduct> orderProducts);
    List<String> getProductId();
    List<String> getSelectProduct(String id);
    List<String> getEmployeeId();
    String selectEmployoleeName(String id);
    List<String> getOrderId();
    ObservableList<OrderDetailTableEntity> getOrderDetails();
}
