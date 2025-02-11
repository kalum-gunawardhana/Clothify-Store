package controller.formController;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.db.DBConnection;

import javax.crypto.interfaces.DHKey;
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
    public AnchorPane apAddEmployee;
    public AnchorPane apUpdateEmployee;
    public AnchorPane apSearchEmployee;
    public AnchorPane apDeleteEmployee;
    
    public TextField txtAddEmpName;
    public TextField txtAddEmpEma;
    public TextField txtAddEmpRol;
    public TextField txtAddEmpAdmId;

    public JFXComboBox cbSearchEmpEmail;
    public TextField txtEmpSeaName;
    public TextField txtEmpSeaRole;
    public TextField txtEmpSeaAdminID;

    public static String selectEmail;
    public static String selectUpdateEmail;
    public static String selectEmpDeletEmail;
    
    public JFXComboBox cbSelectEmpUpdate;
    public TextField txtEmpNameUpdate;
    public TextField txtEmpRoleUpdate;
    public TextField txtEmpAdmIDUpdate;

    public JFXComboBox cbEmpDelete;
    public TextField txtEmpDeleteName;
    public TextField txtEmpDeleteRole;
    public TextField txtEmpDeleteOnAction;

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

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        /*URL resource = this.getClass().getResource("/View/OwnerDashboardForm.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.apEmployee.getChildren().clear();
        this.apEmployee.getChildren().add(load);*/
        apEmployee.toFront();
    }

    public void btnAddEmpOnAction(ActionEvent actionEvent) {
        boolean addedEmp = DashboardController.getInstance().addEmp(txtAddEmpName.getText(), txtAddEmpEma.getText(), txtAddEmpRol.getText(), Integer.valueOf(txtAddEmpAdmId.getText()));
        if (addedEmp){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Employee Added Successfully");
            alert.show();

            txtAddEmpName.clear();
            txtAddEmpEma.clear();
            txtAddEmpRol.clear();
            txtAddEmpAdmId.clear();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Employee Added Fail");
            alert.show();

            txtAddEmpName.clear();
            txtAddEmpEma.clear();
            txtAddEmpRol.clear();
            txtAddEmpAdmId.clear();
        }
    }

    public void btnCleEmpOnAction(ActionEvent actionEvent) {
        txtAddEmpName.clear();
        txtAddEmpEma.clear();
        txtAddEmpRol.clear();
        txtAddEmpAdmId.clear();
    }

    public void btnEmpSearchOnAction(ActionEvent actionEvent) {
        Employee empData = (Employee) DashboardController.getInstance().getEmpData(selectEmail);
        txtEmpSeaName.setText(empData.getName());
        txtEmpSeaRole.setText(empData.getRole());
        txtEmpSeaAdminID.setText(String.valueOf(empData.getAdminId()));
        //System.out.println(empData);
    }

    public void btnSearchEmpClearOnAction(ActionEvent actionEvent) {
        txtEmpSeaName.clear();
        txtEmpSeaRole.clear();
        txtEmpSeaAdminID.clear();
    }

    public void setEmpEmail(){
        List<String> empEmail = DashboardController.getInstance().getEmpEmail();
        cbSearchEmpEmail.getItems().addAll(empEmail);
        cbSelectEmpUpdate.getItems().addAll(empEmail);
        cbEmpDelete.getItems().addAll(empEmail);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEmpEmail();
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
    }

    public void cbSelectEmpSearchEmail(ActionEvent actionEvent) {
        selectEmail = cbSearchEmpEmail.getSelectionModel().getSelectedItem().toString();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean updated = DashboardController.getInstance().updateEmp(selectUpdateEmail, txtEmpNameUpdate.getText(), txtEmpRoleUpdate.getText(), Integer.parseInt(txtEmpAdmIDUpdate.getText()));
        if (updated){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Employee Updated Successfully");
            alert.show();

            txtEmpNameUpdate.clear();
            txtEmpRoleUpdate.clear();
            txtEmpAdmIDUpdate.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Employee Updated Fail");
            alert.show();

            txtEmpNameUpdate.clear();
            txtEmpRoleUpdate.clear();
            txtEmpAdmIDUpdate.clear();
        }
    }

    public void btnUpdateClearOnAction(ActionEvent actionEvent) {
        txtEmpNameUpdate.clear();
        txtEmpRoleUpdate.clear();
        txtEmpAdmIDUpdate.clear();
    }

    public void selectEmpEmailUpdateOnAction(ActionEvent actionEvent) {
        selectUpdateEmail = cbSelectEmpUpdate.getSelectionModel().getSelectedItem().toString();
        Employee empData = (Employee) DashboardController.getInstance().getEmpData(selectUpdateEmail);
        txtEmpNameUpdate.setText(empData.getName());
        txtEmpRoleUpdate.setText(empData.getRole());
        txtEmpAdmIDUpdate.setText(String.valueOf(empData.getAdminId()));
    }

    public void selectEmpDeleteOnAction(ActionEvent actionEvent) {
        selectEmpDeletEmail = cbEmpDelete.getSelectionModel().getSelectedItem().toString();
        Employee empData = (Employee) DashboardController.getInstance().getEmpData(selectEmpDeletEmail);
        txtEmpDeleteName.setText(empData.getName());
        txtEmpDeleteRole.setText(empData.getRole());
        txtEmpDeleteOnAction.setText(String.valueOf(empData.getAdminId()));
    }

    public void btnEmpDeletOnAction(ActionEvent actionEvent) {
        boolean deleted = DashboardController.getInstance().deleteEmployeeByEmail(cbEmpDelete.getSelectionModel().getSelectedItem().toString());
        if (deleted){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Employee Deleted Successfully");
            alert.show();

            txtEmpDeleteOnAction.clear();
            txtEmpDeleteRole.clear();
            txtEmpDeleteName.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Employee Deleted Fail");
            alert.show();

            txtEmpDeleteOnAction.clear();
            txtEmpDeleteRole.clear();
            txtEmpDeleteName.clear();
        }
    }

    public void btnEmpDeleteClearOnAction(ActionEvent actionEvent) {
        txtEmpDeleteOnAction.clear();
        txtEmpDeleteRole.clear();
        txtEmpDeleteName.clear();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        //apSupplier.toFront();
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
}
