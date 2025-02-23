package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import DBConnection.connection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import repository.custom.impl.OrdersDaoImpl;
import service.ServiceFactory;
import service.custom.*;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    public AnchorPane apEmployee;
    public AnchorPane apSupplier;
    public AnchorPane apInventory;
    public AnchorPane apPlaceOrder;
    public JFXTextField txtPlaceOrderOrderId;
    public JFXTextField txtPlaceOrderDateAndTime;
    public JFXComboBox cbPlaceOrderEmployee;
    public JFXTextField txtEmployeeName;
    public JFXComboBox cbPlaceOrderSelectProduct;
    public JFXTextField txtPlaceOrderProductName;
    public JFXTextField txtPlaceOrderProductSIze;
    public JFXTextField txtPlaceOrderProductPrice;
    public JFXTextField txtPlaceOrderProductQty;
    public JFXComboBox cbPlaceOrderPaymentType;
    public TableView tableViewProducts;
    public TableColumn colProductId;
    public TableColumn colProductName;
    public TableColumn colSize;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXTextField txtPlaceOrderTotal;
    public JFXComboBox cbPlaceOrderSelectCustomer;
    public JFXTextField txtSelectCustomerName;
    public JFXTextField txtPlaceOrderSupplierId;
    public AnchorPane apUser;
    public JFXTextField txtUserName;
    public TableView tblUser;
    public TableColumn colUserName;
    public TableColumn colUserEmail;
    public TableColumn colUserPassword;
    public TableColumn colUserRole;
    public TableColumn colUserRegDate;
    public JFXTextField txtUserEmail;
    public JFXTextField txtUserPassword;
    public JFXComboBox cbUserRole;
    public TableView tblProduct;
    public TableColumn colProductName1;
    public TableColumn colProducCategory;
    public TableColumn colProductSize;
    public TableColumn colProductPrize;
    public TableColumn colProductQuantity;
    public TableColumn colProductSupplierId;
    public JFXTextField txtProductName;
    public JFXComboBox cbProductCategory;
    public JFXComboBox cbProductSize;
    public JFXTextField txtProductPrice;
    public JFXTextField txtProductQuantity;
    public JFXComboBox cbProductSupplierId;
    public TableColumn colProductId1;
    public JFXTextField txtSupplierName;
    public TableView tblSupplier;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public TableColumn colSupplierCompany;
    public TableColumn colSupplierEmail;
    public TableColumn colSupplierItem;
    public JFXTextField txtSupplierComapany;
    public JFXTextField txtSupplierEmail;
    public JFXComboBox cbSupplierItem;
    public TableView tblEmployee;
    public TableColumn colEmployeeID;
    public TableColumn colEmployeeName;
    public TableColumn colEmployeeEmail;
    public TableColumn colEmployeeRole;
    public TableColumn colEmployeeAdminID;
    public JFXTextField txtEmployeeName2;
    public JFXTextField txtEmployeeEmail;
    public JFXTextField txtEmployeePassword;
    public JFXComboBox cbEmployeeUserRole;
    public JFXComboBox cbEmployeeEmployeeRole;
    public JFXButton btnUser;
    public AnchorPane apReports;
    public LineChart<String, Number> lineChart;
    public NumberAxis yAxis;
    public BarChart<String, Number> barChart;
    public NumberAxis yAxis1;
    public JFXButton btnProduct;
    public JFXButton btnOrders;
    public JFXButton btnSupplier;
    public JFXButton btnEmployee;
    public AnchorPane apOrderDetails;
    public TableView tblOrderProducts;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colTotalCost;
    public TableColumn colPaymentType;
    public TableColumn colEmployeeId;
    public TableColumn colCustomerId;

    UserService userService= ServiceFactory.getInstance().getServiceType(ServiceType.USER);
    SupplierService supplierService=ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrdersService ordersService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDERS);
    EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        apEmployee.toFront();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderId();
        setDateAndTime();
        setEmployeeId();
        setProductId();
        setPaymentType();
        setCustomerIdCombo();
        loardUserTable();
        loardUserRoleCOmbo();
        loardProductTable();
        loardProductCategoryCombo();
        loardProductSizeCombo();
        loardProductSupplerIdCombo();
        loardSupplierTable();
        loardItemNamesCombo();
        loardEmployeeTable();
        loardUserRoleCombo();
        loardEmployeeRoleCombo();
        loardLineChart();
        loardBarChart();
        loadOrderDetailTable();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        apSupplier.toFront();
    }

    public void btnLoardInventoryOnAction(ActionEvent actionEvent) {
        apInventory.toFront();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        apPlaceOrder.toFront();
    }

    private void setOrderId(){
        List<String> orderId = ordersService.getOrderId();
        txtPlaceOrderOrderId.setText(String.valueOf((Integer.valueOf(orderId.get(orderId.size() - 1)))+1));
    }

    private void setDateAndTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        txtPlaceOrderDateAndTime.setText(LocalDateTime.now().format(formatter));
    }

    public void btnPlaceOrderEmployeeOnAction(ActionEvent actionEvent) {
        txtEmployeeName.setText(ordersService.getSelectCustomerName(cbPlaceOrderEmployee.getValue().toString()));
    }

    private void setEmployeeId(){
        List<String> employeeId = ordersService.getEmployeeId();
        cbPlaceOrderEmployee.getItems().addAll(employeeId);
    }

    public void btnPlaceOrderProductOnAction(ActionEvent actionEvent) {
        List<String> selectProduct = ordersService.getSelectProduct(cbPlaceOrderSelectProduct.getValue().toString());
        txtPlaceOrderProductName.setText(selectProduct.get(0));
        txtPlaceOrderProductSIze.setText(selectProduct.get(1));
        txtPlaceOrderProductPrice.setText(selectProduct.get(2));
        txtPlaceOrderSupplierId.setText(selectProduct.get(3));
    }

    private void setProductId(){
        List<String> productId = ordersService.getProductId();
        cbPlaceOrderSelectProduct.getItems().addAll(productId);
    }

    private void setPaymentType() {
        ObservableList<String> paymentTypes = FXCollections.observableArrayList("Cash", "Card");
        cbPlaceOrderPaymentType.setItems(paymentTypes);

    }

    private ObservableList<OrdersTable> orderTableObservableList = FXCollections.observableArrayList();

    public void btnPlaceOrderAddToCardOnAction(ActionEvent actionEvent) {
        if (txtSelectCustomerName.getText().isEmpty() || txtEmployeeName.getText().isEmpty() || txtPlaceOrderProductName.getText().isEmpty() || txtPlaceOrderProductQty.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Error Occurred");
            alert.setContentText("Please enter order details.");
            alert.showAndWait();
            return;
        }

        addOrderTable(Integer.valueOf(cbPlaceOrderSelectProduct.getSelectionModel().getSelectedItem().toString()), txtPlaceOrderProductName.getText(), txtPlaceOrderProductSIze.getText(), Integer.valueOf(txtPlaceOrderProductQty.getText()), Double.parseDouble(txtPlaceOrderProductPrice.getText()));
        loadTable();
        calculateTotal();
    }

    private void loadTable() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("productSize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("productQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        tableViewProducts.setItems(orderTableObservableList);
    }

    private void addOrderTable(Integer productId, String productName, String productSize, Integer productQty, Double productPrice) {
        OrdersTable orderTable = new OrdersTable(productId, productName, productSize, productQty, productPrice);

        if (orderTableObservableList.isEmpty()) {
            orderTableObservableList.add(orderTable);
        } else {
            boolean productExists = false;

            for (OrdersTable product : orderTableObservableList) {
                if (product.getProductId().equals(productId)) {
                    product.setProductQty(product.getProductQty() + productQty);
                    product.setProductPrice(product.getProductPrice() + productPrice);
                    productExists = true;
                    break;
                }
            }

            if (!productExists) {
                orderTableObservableList.add(orderTable);
            }
        }
    }


    private void calculateTotal(){
        Double total=0.0;
        for (OrdersTable orderTablePrice: orderTableObservableList){
            total+=(orderTablePrice.getProductPrice())*(orderTablePrice.getProductQty());
        }
        txtPlaceOrderTotal.setText(String.valueOf(total));
    }

    public void btnPlaceOrdersOnAction(ActionEvent actionEvent) throws SQLException {
        if (cbPlaceOrderPaymentType.getValue()==null || orderTableObservableList.isEmpty()){
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please add to cart option and select payment type!");

            // Display the alert
            errorAlert.showAndWait();
            return;
        }

        ArrayList<OrderProduct> orderDetails = new ArrayList<>();

        for (OrdersTable productList:orderTableObservableList){
            OrderProduct orderProduct = new OrderProduct(Integer.valueOf(txtPlaceOrderOrderId.getText()), productList.getProductId(), productList.getProductQty());
            orderDetails.add(orderProduct);
        }

        Integer orderIdText = Integer.valueOf(txtPlaceOrderOrderId.getText());
        Integer customerIdText = Integer.valueOf(cbPlaceOrderSelectCustomer.getSelectionModel().getSelectedItem().toString());
        Integer employeeIdText = Integer.valueOf(cbPlaceOrderEmployee.getSelectionModel().getSelectedItem().toString());
        String dateAndTimeText = txtPlaceOrderDateAndTime.getText();
        Double orderTotalText = Double.valueOf(txtPlaceOrderTotal.getText());
        String paymentTypeText = cbPlaceOrderPaymentType.getSelectionModel().getSelectedItem().toString();

        Orders order = new Orders(orderIdText, customerIdText, employeeIdText, dateAndTimeText, orderTotalText, paymentTypeText, orderDetails);
        boolean placeOrder = ordersService.placeOrder(order);
        if (placeOrder){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Confirmation");
            alert.setContentText("Order placed successfully!");
            alert.showAndWait();

            loadOrderDetailTable();
            setOrderId();
        }
    }

    public void cbPlaceOrderSelectCustomerOnAction(ActionEvent actionEvent) {
        txtSelectCustomerName.setText(ordersService.getSelectCustomerName(cbPlaceOrderSelectCustomer.getSelectionModel().getSelectedItem().toString()));
    }

    private void setCustomerIdCombo(){
        cbPlaceOrderSelectCustomer.getItems().addAll(ordersService.getCustomerId());
    }

    public void btnUserOnAction(ActionEvent actionEvent) {
        apUser.toFront();
    }

    public void btnUserAddOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtUserEmail.getText().isEmpty() || txtUserPassword.getText().isEmpty() || cbUserRole.getSelectionModel().getSelectedItem().toString().isEmpty()){
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please enter user details!");

            // Display the alert
            errorAlert.showAndWait();
            return;
        }

        boolean addedUser = userService.addUser(txtUserName.getText(), txtUserEmail.getText(), txtUserPassword.getText(), cbUserRole.getSelectionModel().getSelectedItem().toString());
        if (addedUser){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Confirmation");
            alert.setContentText("User Added Successfully!");
            alert.showAndWait();
            loardUserTable();
            clearAreas();
        }
    }

    private UserTable selectedUser;

    public void btnUserSearchOnAction(ActionEvent actionEvent) {
        selectedUser = (UserTable) tblUser.getSelectionModel().getSelectedItem();
        if (selectedUser!=null){
            User searchedUser = userService.searchUser(selectedUser.getEmail());
            txtUserName.setText(searchedUser.getName());
            txtUserEmail.setText(searchedUser.getEmail());
            txtUserPassword.setText(searchedUser.getPassword());
            cbUserRole.setValue(searchedUser.getRole());
        }else {
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Select user on the user table!");

            // Display the alert
            errorAlert.showAndWait();
        }
    }

    public void btnUserUpdateOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtUserEmail.getText().isEmpty() || txtUserPassword.getText().isEmpty() || cbUserRole.getSelectionModel().getSelectedItem().toString().isEmpty()){
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please choose first select user in the user table and then select user search!");

            // Display the alert
            errorAlert.showAndWait();
            return;
        }

        boolean updatedUser = userService.updateUser(txtUserName.getText(), txtUserEmail.getText(), txtUserPassword.getText(), cbUserRole.getSelectionModel().getSelectedItem().toString());
        if (updatedUser){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Confirmation");
            alert.setContentText("User Updated Successfully!");
            alert.showAndWait();
            loardUserTable();
            clearAreas();
        }else {
            // Creating an Error Alert
            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setTitle("Error Dialog");
            errorAlert1.setHeaderText("An Error Occurred");
            errorAlert1.setContentText("User Updated Unsuccessfully!");

            // Display the alert
            errorAlert1.showAndWait();
        }
    }

    public void btnUserDeleteOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtUserEmail.getText().isEmpty() || txtUserPassword.getText().isEmpty() || cbUserRole.getSelectionModel().getSelectedItem().toString().isEmpty()){
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please choose first select user in the user table and then select user search!");

            // Display the alert
            errorAlert.showAndWait();
            return;
        }

        boolean deletedUser = userService.deleteUser(txtUserEmail.getText());
        if (deletedUser){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Confirmation");
            alert.setContentText("User Deleted Successfully!");
            alert.showAndWait();
            loardUserTable();
            clearAreas();
        }else {
            // Creating an Error Alert
            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setTitle("Error Dialog");
            errorAlert1.setHeaderText("An Error Occurred");
            errorAlert1.setContentText("User Deleted Unsuccessfully!");

            // Display the alert
            errorAlert1.showAndWait();
        }
    }

    private void loardUserTable(){
        ObservableList<UserTable> users = userService.getUsers();

        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colUserRegDate.setCellValueFactory(new PropertyValueFactory<>("regdate"));

        tblUser.setItems(users);
    }

    private void loardUserRoleCOmbo(){
        cbUserRole.getItems().addAll("Admin","Employee");
    }

    private void clearAreas(){
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserPassword.clear();
        cbUserRole.setValue("Select Role");
    }

    public void btnClearAreasOnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserPassword.clear();
        cbUserRole.setValue("Select Role");
    }

    public void btnProductAddOnAction(ActionEvent actionEvent) {
        boolean addedProducts = productService.addProducts(new Product(null, txtProductName.getText(), cbProductCategory.getSelectionModel().getSelectedItem().toString(), cbProductSize.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(txtProductPrice.getText()), Integer.parseInt(txtProductQuantity.getText()), Integer.parseInt(cbProductSupplierId.getSelectionModel().getSelectedItem().toString())));
        if (addedProducts){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Confirmation");
            alert.setContentText("Product Added Successfully!");
            alert.showAndWait();
            loardProductTable();
            clearProductDetailAreas();
        }
    }

    private ProductTable selectedProduct;

    public void btnProductSearchOnAction(ActionEvent actionEvent) {
        selectedProduct = (ProductTable) tblProduct.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            txtProductName.setText(selectedProduct.getProductName());
            cbProductCategory.setValue(selectedProduct.getCategory().toString());
            cbProductSize.setValue(selectedProduct.getSize().toString());
            txtProductPrice.setText(String.valueOf(selectedProduct.getPrice()));
            txtProductQuantity.setText(String.valueOf(selectedProduct.getQty()));
            cbProductSupplierId.setValue(selectedProduct.getSupplierId().toString());
        }
    }

    public void btnProductUpdateOnAction(ActionEvent actionEvent) {
        boolean updatedProduct = productService.updateProduct(new Product(selectedProduct.getProductID(), txtProductName.getText(), cbProductCategory.getSelectionModel().getSelectedItem().toString(), cbProductSize.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(txtProductPrice.getText()), Integer.parseInt(txtProductQuantity.getText()), Integer.parseInt(cbProductSupplierId.getSelectionModel().getSelectedItem().toString())));
        if (updatedProduct){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Confirmation");
            alert.setContentText("Product Updated Successfully!");
            alert.showAndWait();
            loardProductTable();
            clearProductDetailAreas();
        }
    }

    public void btnProductDeleteOnAction(ActionEvent actionEvent) {
        boolean deletedProduct = productService.deleteProduct(selectedProduct.getProductID());
        if (deletedProduct){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Confirmation");
            alert.setContentText("Product Deleted Successfully!");
            alert.showAndWait();
            loardProductTable();
            clearProductDetailAreas();
        }
    }

    private void loardProductTable(){
        ObservableList<ProductTable> allProducts = productService.getAllProducts();
        colProductId1.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        colProductName1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProducCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colProductPrize.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colProductSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        tblProduct.setItems(allProducts);
    }

    private void loardProductCategoryCombo(){
        List<String> allCategorys = productService.getAllCategorys();
        cbProductCategory.getItems().addAll(allCategorys);
    }

    private void loardProductSizeCombo(){
        List<String> allCSizes = productService.getAllCSizes();
        cbProductSize.getItems().addAll(allCSizes);
    }

    private void loardProductSupplerIdCombo(){
        List<String> allCSupllierIds = supplierService.getAllCSupllierIds();
        cbProductSupplierId.getItems().addAll(allCSupllierIds);
    }

    private void clearProductDetailAreas(){
        txtProductName.clear();
        cbProductCategory.setValue("Select Category");
        cbProductSize.setValue("Select Size");
        txtProductPrice.clear();
        txtProductQuantity.clear();
        cbProductSupplierId.setValue("Select Supplier");
    }

    public void btnProductClearOnAction(ActionEvent actionEvent) {
        txtProductName.clear();
        cbProductCategory.setValue("Select Category");
        cbProductSize.setValue("Select Size");
        txtProductPrice.clear();
        txtProductQuantity.clear();
        cbProductSupplierId.setValue("Select Supplier");
    }

    public void btnSupplierAddOnAction(ActionEvent actionEvent) {
        boolean addedSupplier = supplierService.addSupplier(new Supplier(null, txtSupplierName.getText(), txtSupplierComapany.getText(), txtSupplierEmail.getText(), cbSupplierItem.getSelectionModel().getSelectedItem().toString()));
        if (addedSupplier){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Confirmation");
            alert.setContentText("Supplier Added Successfully!");
            alert.showAndWait();
            loardSupplierTable();
            clearSupplierDetailAreas();
        }
    }

    private SupplierTable getSelectedProduct;

    public void btnSupplierSearchOnAction(ActionEvent actionEvent) {
        getSelectedProduct = (SupplierTable) tblSupplier.getSelectionModel().getSelectedItem();
        txtSupplierName.setText(getSelectedProduct.getName());
        txtSupplierComapany.setText(getSelectedProduct.getCompany());
        txtSupplierEmail.setText(getSelectedProduct.getEmail());
        cbSupplierItem.setValue(getSelectedProduct.getItem());
    }

    public void btnSupplierUpdateOnAction(ActionEvent actionEvent) {
        boolean updatedSupplier = supplierService.updateSupplier(new Supplier(getSelectedProduct.getSupplierID(), txtSupplierName.getText(), txtSupplierComapany.getText(), txtSupplierEmail.getText(), cbSupplierItem.getSelectionModel().getSelectedItem().toString()));
        if (updatedSupplier){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Confirmation");
            alert.setContentText("Supplier Updated Successfully!");
            alert.showAndWait();
            loardSupplierTable();
            clearSupplierDetailAreas();
        }
    }

    public void btnSupplierDeleteOnAction(ActionEvent actionEvent) {
        boolean deletedSupplier = supplierService.deleteSupplier(getSelectedProduct.getSupplierID());
        if (deletedSupplier){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Confirmation");
            alert.setContentText("Supplier Deleted Successfully!");
            alert.showAndWait();
            loardSupplierTable();
            clearSupplierDetailAreas();
        }
    }

    public void btnSupplierClearOnAction(ActionEvent actionEvent) {
        txtSupplierName.clear();
        txtSupplierComapany.clear();
        txtSupplierEmail.clear();
        cbSupplierItem.setValue("Select Item");
    }
    
    private void loardSupplierTable(){
        ObservableList<SupplierTable> allSupplier = supplierService.getAllSupplier();

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        tblSupplier.setItems(allSupplier);
    }

    private void loardItemNamesCombo(){
        List<String> allItemNames = productService.getAllItemNames();
        cbSupplierItem.getItems().addAll(allItemNames);
    }

    private void clearSupplierDetailAreas(){
        txtSupplierName.clear();
        txtSupplierComapany.clear();
        txtSupplierEmail.clear();
        cbSupplierItem.setValue("Select Item");
    }

    private void loardEmployeeTable(){
        ObservableList<EmployeeTable> allEmployee = employeeService.getAllEmployee();

        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmployeeRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmployeeAdminID.setCellValueFactory(new PropertyValueFactory<>("adminId"));

        tblEmployee.setItems(allEmployee);
    }

    public void btnEmployeeAddOnAction(ActionEvent actionEvent) throws SQLException {
        if (txtEmployeeName2.getText().isEmpty() || txtEmployeeEmail.getText().isEmpty() || txtEmployeePassword.getText().isEmpty() || cbEmployeeUserRole.getValue()==null || cbEmployeeEmployeeRole.getValue()==null){
            // Creating an Error Alert
            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setTitle("Error Dialog");
            errorAlert1.setHeaderText("An Error Occurred");
            errorAlert1.setContentText("Please Enter Employee Details!");

            // Display the alert
            errorAlert1.showAndWait();
            return;
        }

        User user = new User(null, txtEmployeeName2.getText(), txtEmployeeEmail.getText(), txtEmployeePassword.getText(), cbEmployeeUserRole.getSelectionModel().getSelectedItem().toString(), null);
        Employee employee = new Employee(null, txtEmployeeName2.getText(), txtEmployeeEmail.getText(), cbEmployeeEmployeeRole.getSelectionModel().getSelectedItem().toString(), null);
        boolean addedEmployee = employeeService.addEmployee(user, employee);
        if (addedEmployee){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Confirmation");
            alert.setContentText("Employee Added Successfully!");
            alert.showAndWait();
            loardEmployeeTable();
            loardUserTable();
            clearEmployeeDetailAreas();
        }
    }

    private User selectUser;
    private EmployeeTable selectedEmployee;

    public void btnEmployeeSearchOnAction(ActionEvent actionEvent) {
        selectedEmployee = (EmployeeTable) tblEmployee.getSelectionModel().getSelectedItem();

        if (selectedEmployee==null){
            // Creating an Error Alert
            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setTitle("Error Dialog");
            errorAlert1.setHeaderText("An Error Occurred");
            errorAlert1.setContentText("Please select employee on employee table");

            // Display the alert
            errorAlert1.showAndWait();
            return;
        }

        selectUser = employeeService.getSelectUser(selectedEmployee.getAdminId());
        txtEmployeeName2.setText(selectedEmployee.getName());
        txtEmployeeEmail.setText(selectedEmployee.getEmail());
        txtEmployeePassword.setText(selectUser.getPassword());
        cbEmployeeUserRole.setValue(selectUser.getRole());
        cbEmployeeEmployeeRole.setValue(selectedEmployee.getRole());
    }

    public void btnEmployeeUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        if (txtEmployeeName2.getText().isEmpty() || txtEmployeeEmail.getText().isEmpty() || txtEmployeePassword.getText().isEmpty() || cbEmployeeUserRole.getValue()==null || cbEmployeeEmployeeRole.getValue()==null){
            // Creating an Error Alert
            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setTitle("Error Dialog");
            errorAlert1.setHeaderText("An Error Occurred");
            errorAlert1.setContentText("Please enter employee details!");

            // Display the alert
            errorAlert1.showAndWait();
            return;
        }

        User user = new User(selectUser.getUserId(), txtEmployeeName2.getText(), txtEmployeeEmail.getText(), txtEmployeePassword.getText(), cbEmployeeUserRole.getSelectionModel().getSelectedItem().toString(), null);
        Employee employee = new Employee(selectedEmployee.getEmployeeId(), txtEmployeeName2.getText(), txtEmployeeEmail.getText(), cbEmployeeEmployeeRole.getSelectionModel().getSelectedItem().toString(), selectUser.getUserId());
        boolean updatedEmployee = employeeService.updateEmployee(user, employee);

        if (updatedEmployee){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Confirmation");
            alert.setContentText("Employee Update Successfully!");
            alert.showAndWait();
            loardEmployeeTable();
            loardUserTable();
            clearEmployeeDetailAreas();
        }
    }

    public void btnEmployeeDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        if (selectedEmployee==null){
            // Creating an Error Alert
            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setTitle("Error Dialog");
            errorAlert1.setHeaderText("An Error Occurred");
            errorAlert1.setContentText("Please select employee on employee table");

            // Display the alert
            errorAlert1.showAndWait();
            return;
        }

        boolean deletedEmployee = employeeService.deleteEmployee(selectUser.getUserId(), selectedEmployee.getEmployeeId());
        if (deletedEmployee){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Confirmation");
            alert.setContentText("Employee Delete Successfully!");
            alert.showAndWait();
            loardEmployeeTable();
            loardUserTable();
            clearEmployeeDetailAreas();
        }
    }

    public void btnEmployeeClearOnAction(ActionEvent actionEvent) {
        txtEmployeeName2.clear();
        txtEmployeeEmail.clear();
        txtEmployeePassword.clear();
        cbEmployeeUserRole.setValue("Select User Role");
        cbEmployeeEmployeeRole.setValue("Select Employee Role");
    }

    private void loardUserRoleCombo(){
        cbEmployeeUserRole.getItems().addAll("Admin", "Employee");
    }

    private void loardEmployeeRoleCombo(){
        cbEmployeeEmployeeRole.getItems().addAll("Store Manager", "Inventory Clerk", "Customer Service Rep", "Sales Representative", "Accountant", "Marketing Specialist", "IT Support", "Operations Manager", "HR Assistant", "Receptionist");
    }

    private void clearEmployeeDetailAreas(){
        txtEmployeeName2.clear();
        txtEmployeeEmail.clear();
        txtEmployeePassword.clear();
        cbEmployeeUserRole.setValue("Select User Role");
        cbEmployeeEmployeeRole.setValue("Select Employee Role");
    }

    public void btnCustomerReportOnAction(ActionEvent actionEvent) {
        JasperDesign design = null;
        try {
            design = JRXmlLoader.load("src/main/resources/view/jrxml/customer.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReportsOnAction(ActionEvent actionEvent) {
        apReports.toFront();
    }

    public void btnEmployeeReportOnAction(ActionEvent actionEvent) {
        JasperDesign design = null;
        try {
            design = JRXmlLoader.load("src/main/resources/view/jrxml/employee.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnProductReportOnAction(ActionEvent actionEvent) {
        JasperDesign design = null;
        try {
            design = JRXmlLoader.load("src/main/resources/view/jrxml/product.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierReportOnAction(ActionEvent actionEvent) {
        JasperDesign design = null;
        try {
            design = JRXmlLoader.load("src/main/resources/view/jrxml/supplier.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private void loardLineChart(){
        // Sample Data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Sales Data");

        // Adding data points
        series.getData().add(new XYChart.Data<>("Monday", 120));
        series.getData().add(new XYChart.Data<>("Tuesday", 150));
        series.getData().add(new XYChart.Data<>("Wednesday", 180));
        series.getData().add(new XYChart.Data<>("Thursday", 200));
        series.getData().add(new XYChart.Data<>("Friday", 300));
        series.getData().add(new XYChart.Data<>("Saturday", 250));
        series.getData().add(new XYChart.Data<>("Sunday", 280));

        // Add series to chart
        lineChart.getData().add(series);
    }

    private void loardBarChart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2024 - 2025 Sales");

        // Adding sample data
        series.getData().add(new XYChart.Data<>("September ", 500));
        series.getData().add(new XYChart.Data<>("November", 700));
        series.getData().add(new XYChart.Data<>("December", 600));
        series.getData().add(new XYChart.Data<>("January", 800));
        series.getData().add(new XYChart.Data<>("February", 900));

        // Add the series to the chart
        barChart.getData().add(series);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        // Get the current window and close it
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

        try {
            // Load the new scene
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginForm.fxml"));

            // Create and show new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) {
        apOrderDetails.toFront();
    }

    private void loadOrderDetailTable(){
        ObservableList<OrderDetailTable> orderDetail = ordersService.getOrderDetail();

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        tblOrderProducts.setItems(orderDetail);
    }
}