package controller.formController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.DashboardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        List<String> orderIdList = DashboardController.getInstance().getOrderId();
        txtPlaceOrderOrderId.setText(String.valueOf((Integer.valueOf(orderIdList.get(orderIdList.size() - 1)))+1));
    }

    private void setDateAndTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        txtPlaceOrderDateAndTime.setText(LocalDateTime.now().format(formatter));
    }

    public void btnPlaceOrderEmployeeOnAction(ActionEvent actionEvent) {
        txtEmployeeName.setText(DashboardController.getInstance().selectEmployoleeName(cbPlaceOrderEmployee.getValue().toString()));
    }

    private void setEmployeeId(){
        List<String> employeeIdList = DashboardController.getInstance().getEmployeeId();
        cbPlaceOrderEmployee.getItems().addAll(employeeIdList);
    }

    public void btnPlaceOrderProductOnAction(ActionEvent actionEvent) {
        List<String> selectProductList = DashboardController.getInstance().getSelectProduct(cbPlaceOrderSelectProduct.getValue().toString());
        txtPlaceOrderProductName.setText(selectProductList.get(0));
        txtPlaceOrderProductSIze.setText(selectProductList.get(1));
        txtPlaceOrderProductPrice.setText(selectProductList.get(2));
        txtPlaceOrderSupplierId.setText(selectProductList.get(3));
    }

    private void setProductId(){
        List<String> productIdList = DashboardController.getInstance().getProductId();
        System.out.println(productIdList.toString());
        cbPlaceOrderSelectProduct.getItems().addAll(productIdList);
    }

    private void setPaymentType() {
        ObservableList<String> paymentTypes = FXCollections.observableArrayList("Cash", "Card");
        cbPlaceOrderPaymentType.setItems(paymentTypes);

    }

    private ObservableList<OrderTable> orderTableObservableList = FXCollections.observableArrayList();

    public void btnPlaceOrderAddToCardOnAction(ActionEvent actionEvent) {
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
        OrderTable orderTable = new OrderTable(productId, productName, productSize, productQty, productPrice);

        if (orderTableObservableList.isEmpty()) {
            orderTableObservableList.add(orderTable);
        } else {
            boolean productExists = false;

            for (OrderTable product : orderTableObservableList) {
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
        for (OrderTable orderTablePrice: orderTableObservableList){
            total+=(orderTablePrice.getProductPrice())*(orderTablePrice.getProductQty());
        }
        txtPlaceOrderTotal.setText(String.valueOf(total));
    }

    public void btnPlaceOrdersOnAction(ActionEvent actionEvent) throws SQLException {
        ArrayList<OrderProduct> orderDetails = new ArrayList<>();

        for (OrderTable productList:orderTableObservableList){
            OrderProduct orderProduct = new OrderProduct(Integer.valueOf(txtPlaceOrderOrderId.getText()), productList.getProductId(), productList.getProductQty());
            orderDetails.add(orderProduct);
        }

        Integer orderIdText = Integer.valueOf(txtPlaceOrderOrderId.getText());
        Integer customerIdText = Integer.valueOf(cbPlaceOrderSelectCustomer.getSelectionModel().getSelectedItem().toString());
        Integer employeeIdText = Integer.valueOf(cbPlaceOrderEmployee.getSelectionModel().getSelectedItem().toString());
        String dateAndTimeText = txtPlaceOrderDateAndTime.getText();
        Double orderTotalText = Double.valueOf(txtPlaceOrderTotal.getText());
        String paymentTypeText = cbPlaceOrderPaymentType.getSelectionModel().getSelectedItem().toString();

        Order order = new Order(orderIdText, customerIdText, employeeIdText, dateAndTimeText, orderTotalText, paymentTypeText, orderDetails);
        boolean placedOrder = DashboardController.getInstance().placeOrder(order);
        if (placedOrder){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Confirmation");
            alert.setContentText("Order placed successfully!");
            alert.showAndWait();
        }
    }

    public void cbPlaceOrderSelectCustomerOnAction(ActionEvent actionEvent) {
        txtSelectCustomerName.setText(DashboardController.getInstance().getSelectCustomerName(cbPlaceOrderSelectCustomer.getSelectionModel().getSelectedItem().toString()));
    }

    private void setCustomerIdCombo(){
        cbPlaceOrderSelectCustomer.getItems().addAll(DashboardController.getInstance().getCustomerId());
    }

    public void btnUserOnAction(ActionEvent actionEvent) {
        apUser.toFront();
    }

    public void btnUserAddOnAction(ActionEvent actionEvent) {
        boolean addedUser = DashboardController.getInstance().addUser(txtUserName.getText(), txtUserEmail.getText(), txtUserPassword.getText(), cbUserRole.getSelectionModel().getSelectedItem().toString());
        if (addedUser){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Confirmation");
            alert.setContentText("User Added Successfully!");
            alert.showAndWait();
            loardUserTable();
            clearAreas();
        }
    }

    public void btnUserSearchOnAction(ActionEvent actionEvent) {
        User searchUser = DashboardController.getInstance().searchUser(txtUserEmail.getText());
        //System.out.println(searchUser.toString());
        txtUserName.setText(searchUser.getName());
        txtUserEmail.setText(searchUser.getEmail());
        txtUserPassword.setText(searchUser.getPassword());
        cbUserRole.setValue(searchUser.getRole());
    }

    public void btnUserUpdateOnAction(ActionEvent actionEvent) {
        boolean updatedUser = DashboardController.getInstance().updateUser(txtUserName.getText(), txtUserEmail.getText(), txtUserPassword.getText(), cbUserRole.getSelectionModel().getSelectedItem().toString());
        if (updatedUser){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Confirmation");
            alert.setContentText("User Updated Successfully!");
            alert.showAndWait();
            loardUserTable();
            clearAreas();
        }
    }

    public void btnUserDeleteOnAction(ActionEvent actionEvent) {
        boolean deletedUser = DashboardController.getInstance().deleteUser(txtUserEmail.getText());
        if (deletedUser){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Confirmation");
            alert.setContentText("User Deleted Successfully!");
            alert.showAndWait();
            loardUserTable();
            clearAreas();
        }
    }

    private void loardUserTable(){
        ObservableList<UserTable> users = DashboardController.getInstance().getUsers();

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
        boolean addedProducts = DashboardController.getInstance().addProducts(new Product(null, txtProductName.getText(), cbProductCategory.getSelectionModel().getSelectedItem().toString(), cbProductSize.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(txtProductPrice.getText()), Integer.parseInt(txtProductQuantity.getText()), Integer.parseInt(cbProductSupplierId.getSelectionModel().getSelectedItem().toString())));
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
            // Update UI fields with selected product details
            txtProductName.setText(selectedProduct.getProductName());
            cbProductCategory.setValue(selectedProduct.getCategory().toString());
            cbProductSize.setValue(selectedProduct.getSize().toString());
            txtProductPrice.setText(String.valueOf(selectedProduct.getPrice()));
            txtProductQuantity.setText(String.valueOf(selectedProduct.getQty()));
            cbProductSupplierId.setValue(selectedProduct.getSupplierId().toString());
        }
    }

    public void btnProductUpdateOnAction(ActionEvent actionEvent) {
        //System.out.println(selectedProduct.toString());
        boolean updatedProduct = DashboardController.getInstance().updateProduct(new Product(selectedProduct.getProductID(),txtProductName.getText(),cbProductCategory.getSelectionModel().getSelectedItem().toString(),cbProductSize.getSelectionModel().getSelectedItem().toString(),Double.parseDouble(txtProductPrice.getText()),Integer.parseInt(txtProductQuantity.getText()),Integer.parseInt(cbProductSupplierId.getSelectionModel().getSelectedItem().toString())));
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
        boolean deletedProduct = DashboardController.getInstance().deleteProduct(selectedProduct.getProductID());
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
        ObservableList<ProductTable> allProducts = DashboardController.getInstance().getAllProducts();
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
        List<String> allCategorys = DashboardController.getInstance().getAllCategorys();
        cbProductCategory.getItems().addAll(allCategorys);
    }

    private void loardProductSizeCombo(){
        List<String> allsizes = DashboardController.getInstance().getAllCSizes();
        cbProductSize.getItems().addAll(allsizes);
    }

    private void loardProductSupplerIdCombo(){
        List<String> allids = DashboardController.getInstance().getAllCSupllierIds();
        cbProductSupplierId.getItems().addAll(allids);
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
        boolean addedSupplier = DashboardController.getInstance().addSupplier(new Supplier(null, txtSupplierName.getText(), txtSupplierComapany.getText(), txtSupplierEmail.getText(), cbSupplierItem.getSelectionModel().getSelectedItem().toString()));
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
        //System.out.println(selectedItem.toString());
        txtSupplierName.setText(getSelectedProduct.getName());
        txtSupplierComapany.setText(getSelectedProduct.getCompany());
        txtSupplierEmail.setText(getSelectedProduct.getEmail());
        cbSupplierItem.setValue(getSelectedProduct.getItem());
    }

    public void btnSupplierUpdateOnAction(ActionEvent actionEvent) {
        boolean updatedSupplier = DashboardController.getInstance().updateSupplier(new Supplier(getSelectedProduct.getSupplierID(), txtSupplierName.getText(), txtSupplierComapany.getText(), txtSupplierEmail.getText(), cbSupplierItem.getSelectionModel().getSelectedItem().toString()));
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
        boolean deletedSupplier = DashboardController.getInstance().deleteSupplier(getSelectedProduct.getSupplierID());
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
        ObservableList<SupplierTable> allSupplier = DashboardController.getInstance().getAllSupplier();

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        tblSupplier.setItems(allSupplier);
    }

    private void loardItemNamesCombo(){
        List<String> allItemNames = DashboardController.getInstance().getAllItemNames();
        cbSupplierItem.getItems().addAll(allItemNames);
    }

    private void clearSupplierDetailAreas(){
        txtSupplierName.clear();
        txtSupplierComapany.clear();
        txtSupplierEmail.clear();
        cbSupplierItem.setValue("Select Item");
    }

    private void loardEmployeeTable(){
        ObservableList<EmployeeTable> allEmployee = DashboardController.getInstance().getAllEmployee();

        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmployeeRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmployeeAdminID.setCellValueFactory(new PropertyValueFactory<>("adminId"));

        tblEmployee.setItems(allEmployee);
    }

    public void btnEmployeeAddOnAction(ActionEvent actionEvent) throws SQLException {
        User user = new User(null, txtEmployeeName2.getText(), txtEmployeeEmail.getText(), txtEmployeePassword.getText(), cbEmployeeUserRole.getSelectionModel().getSelectedItem().toString(), null);
        Employee employee = new Employee(null, txtEmployeeName2.getText(), txtEmployeeEmail.getText(), cbEmployeeEmployeeRole.getSelectionModel().getSelectedItem().toString(), null);
        boolean addedEmployee = DashboardController.getInstance().addEmployee(user, employee);
        if (addedEmployee){
            if (addedEmployee){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Confirmation");
                alert.setContentText("Employee Added Successfully!");
                alert.showAndWait();
                //loardSupplierTable();
                //clearSupplierDetailAreas();
                loardEmployeeTable();
                loardUserTable();
                clearEmployeeDetailAreas();
            }
        }
    }

    private User selectUser;
    private EmployeeTable selectedEmployee;

    public void btnEmployeeSearchOnAction(ActionEvent actionEvent) {
        selectedEmployee = (EmployeeTable) tblEmployee.getSelectionModel().getSelectedItem();
        //System.out.println(selectedEmployee.toString());
        selectUser = DashboardController.getInstance().getSelectUser(selectedEmployee.getAdminId());
        txtEmployeeName2.setText(selectedEmployee.getName());
        txtEmployeeEmail.setText(selectedEmployee.getEmail());
        txtEmployeePassword.setText(selectUser.getPassword());
        cbEmployeeUserRole.setValue(selectUser.getRole());
        cbEmployeeEmployeeRole.setValue(selectedEmployee.getRole());
    }

    public void btnEmployeeUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        User user = new User(selectUser.getUserId(), txtEmployeeName2.getText(), txtEmployeeEmail.getText(), txtEmployeePassword.getText(), cbEmployeeUserRole.getSelectionModel().getSelectedItem().toString(), null);
        Employee employee = new Employee(selectedEmployee.getEmployeeId(), txtEmployeeName2.getText(), txtEmployeeEmail.getText(), cbEmployeeEmployeeRole.getSelectionModel().getSelectedItem().toString(), selectUser.getUserId());
        boolean b = DashboardController.getInstance().updateEmployee(user, employee);

        if (b){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Confirmation");
            alert.setContentText("Employee Update Successfully!");
            alert.showAndWait();
            //loardSupplierTable();
            //clearSupplierDetailAreas();
            loardEmployeeTable();
            loardUserTable();
            clearEmployeeDetailAreas();
        }
    }

    public void btnEmployeeDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        boolean deletedEmployee = DashboardController.getInstance().deleteEmployee(selectUser.getUserId(), selectedEmployee.getEmployeeId());
        if (deletedEmployee){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Confirmation");
            alert.setContentText("Employee Delete Successfully!");
            alert.showAndWait();
            //loardSupplierTable();
            //clearSupplierDetailAreas();
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

    public void dashbordButtonShow(String role){
        //System.out.println(role);
        if (role.equals("Employee")){
            //System.out.println("correct");
            //btnUser.setDisable(true);
        }
    }

    public void btnCustomerReportOnAction(ActionEvent actionEvent) {
        JasperDesign design = null;
        try {
            design = JRXmlLoader.load("src/main/resources/view/jrxml/orders.jrxml");
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
}
