package DashboardController;

import Model.Employee;
import Model.Inventory;
import Model.Supplier;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
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
    public AnchorPane apDefault;
    public AnchorPane apInventory;

    public TextField txtSupplierName;
    public TextField txtSupplierCompany;
    public TextField txtSupplierEmail;
    public TextField txtSupplierItem;

    public JFXComboBox cbSelectSupplier;
    public TextField txtSearchSupplierName;
    public TextField txtSearchSupplierCompany;
    public TextField txtSearchSupplierItem;
    
    public JFXComboBox cbUpdateSelectSupplier;
    public TextField txtUpdateSupplierName;
    public TextField txtUpdateSupplierComapny;
    public TextField txtUpdateSupplierItem;

    public JFXComboBox cbDeleteSelectSupplier;
    public TextField txtDeleteSupplierName;
    public TextField txtDeleteSupplierCompany;
    public TextField txtDeleteSupplierItem;

    public TextField txtAddInventoryName;
    public TextField txtAddInventoryPrice;
    public TextField txtAddInventoryQty;
    public JFXComboBox cbInventoryAddSelectCategory;
    public JFXComboBox cbInventoryAddSelectSize;
    public JFXComboBox cbInventoryAddSelectSupplier;

    public TextField txtInventorySearchName;
    public TextField txtInventorySearchCategory;
    public TextField txtInventorySearchSize;
    public TextField txtInventorySearchPrice;
    public TextField txtInventorySearchQty;
    public JFXComboBox cbInventorySearchSelectProduct;
    public TextField txtInventorySearchSupplierId;

    public JFXComboBox cbUpdateInventorySelectProduct;
    
    public TextField txtUpdateInventoryName;
    public TextField txtInventoryUpdateCategory;
    public TextField txtInventoryUpdateSize;
    public TextField txtInventoryUpdatePrice;
    public TextField txtInventoryUpdateQty;
    public TextField txtInventoryUpdateSupplierId1;
    public TextField txtDeleteInventoryName;
    public TextField txtInventoryDeleteCategory;
    public TextField txtInventoryDeleteSize;
    public TextField txtInventoryDeletePrice;
    public TextField txtInventoryDeleteQty;
    public JFXComboBox cbDeleteInventorySelectProduct;
    public TextField txtInventoryDeleteSupplierId;

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
        setSupplierEmail();
        setCategory();
        setSize();
        setSupplier();
        setProduct();
    }

    public void cbSelectEmpSearchEmail(ActionEvent actionEvent) {
        selectEmail = cbSearchEmpEmail.getSelectionModel().getSelectedItem().toString();
        //System.out.println(selectEmail);
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

    public void btnAddSuppierOnAction(ActionEvent actionEvent) {
        boolean addSupplier = DashboardController.getInstance().addSupplier(txtSupplierName.getText(), txtSupplierCompany.getText(), txtSupplierEmail.getText(), txtSupplierItem.getText());
        if (addSupplier){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Supplier Added Successfully");
            alert.show();

            txtSupplierName.clear();
            txtSupplierCompany.clear();
            txtSupplierEmail.clear();
            txtSupplierItem.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Supplier Added Fail");
            alert.show();

            txtSupplierName.clear();
            txtSupplierCompany.clear();
            txtSupplierEmail.clear();
            txtSupplierItem.clear();
        }
    }

    public void btnClearSupplierOnAction(ActionEvent actionEvent) {

    }

    public void btnSearchSuppierOnAction(ActionEvent actionEvent) {
        Supplier supplierData = (Supplier) DashboardController.getInstance().getSupplierData(cbSelectSupplier.getSelectionModel().getSelectedItem().toString());
        txtSearchSupplierName.setText(supplierData.getName());
        txtSearchSupplierCompany.setText(supplierData.getCompany());
        txtSearchSupplierItem.setText(supplierData.getItem());
    }

    public void setSupplierEmail(){
        List<String> supplierEmails = DashboardController.getInstance().getSupplierEmails();
        cbSelectSupplier.getItems().addAll(supplierEmails);
        cbUpdateSelectSupplier.getItems().addAll(supplierEmails);
        cbDeleteSelectSupplier.getItems().addAll(supplierEmails);
    }

    public void cbUpdateSuplierOnAction(ActionEvent actionEvent) {
        Supplier supplierData = (Supplier) DashboardController.getInstance().getSupplierData(cbUpdateSelectSupplier.getSelectionModel().getSelectedItem().toString());
        txtUpdateSupplierName.setText(supplierData.getName());
        txtUpdateSupplierComapny.setText(supplierData.getCompany());
        txtUpdateSupplierItem.setText(supplierData.getItem());
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        boolean updatedSupllier = DashboardController.getInstance().updateSupllier(cbUpdateSelectSupplier.getSelectionModel().getSelectedItem().toString(), txtUpdateSupplierName.getText(), txtUpdateSupplierComapny.getText(), txtUpdateSupplierItem.getText());
        if (updatedSupllier){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Supplier Updated Successfully");
            alert.show();

            txtUpdateSupplierName.clear();
            txtUpdateSupplierComapny.clear();
            txtUpdateSupplierItem.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Supplier Updated Fail");
            alert.show();

            txtUpdateSupplierName.clear();
            txtUpdateSupplierComapny.clear();
            txtUpdateSupplierItem.clear();
        }
    }

    public void btnUpdateCleanSupplierOnAction(ActionEvent actionEvent) {

    }

    public void cbDeleteSelectSupplierOnAction(ActionEvent actionEvent) {
        Supplier supplierData = (Supplier) DashboardController.getInstance().getSupplierData(cbDeleteSelectSupplier.getSelectionModel().getSelectedItem().toString());
        txtDeleteSupplierName.setText(supplierData.getName());
        txtDeleteSupplierCompany.setText(supplierData.getCompany());
        txtDeleteSupplierItem.setText(supplierData.getItem());
    }

    public void btnDeleteSuplierOnAction(ActionEvent actionEvent) {
        boolean deleteSupplier = DashboardController.getInstance().deleteSupplier(cbDeleteSelectSupplier.getSelectionModel().getSelectedItem().toString());
        if (deleteSupplier){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Supplier Deleted Successfully");
            alert.show();

            txtDeleteSupplierName.clear();
            txtDeleteSupplierCompany.clear();
            txtDeleteSupplierItem.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Supplier Deleted Fail");
            alert.show();

            txtDeleteSupplierName.clear();
            txtDeleteSupplierName.clear();
            txtDeleteSupplierItem.clear();
        }
    }

    public void btnCleanSupplierDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnAddInventoryOnAction(ActionEvent actionEvent) {
        boolean addInventory = DashboardController.getInstance().addInventory(txtAddInventoryName.getText(), cbInventoryAddSelectCategory.getSelectionModel().getSelectedItem().toString(), cbInventoryAddSelectSize.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(txtAddInventoryPrice.getText()), Integer.parseInt(txtAddInventoryQty.getText()), cbInventoryAddSelectSupplier.getSelectionModel().getSelectedItem().toString());
        if (addInventory){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Inventory Added Successfully");
            alert.show();

            txtAddInventoryName.clear();
            cbInventoryAddSelectCategory.setValue(null);
            cbInventoryAddSelectSize.setValue(null);
            txtAddInventoryPrice.clear();
            txtAddInventoryQty.clear();
            cbInventoryAddSelectSupplier.setValue(null);

            /*txtDeleteSupplierCompany.clear();
            txtDeleteSupplierItem.clear();*/
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Inventory Added Fail");
            alert.show();

            txtAddInventoryName.clear();
            cbInventoryAddSelectCategory.setValue(null);
            cbInventoryAddSelectSize.setValue(null);
            txtAddInventoryPrice.clear();
            txtAddInventoryQty.clear();
            cbInventoryAddSelectSupplier.setValue(null);
        }
    }

    public void setCategory(){
        List<String> setCategory = DashboardController.getInstance().setCategory();
        cbInventoryAddSelectCategory.getItems().addAll(setCategory);
    }

    public void setSize(){
        List<String> setSize = DashboardController.getInstance().setSize();
        cbInventoryAddSelectSize.getItems().addAll(setSize);
    }

    public void setSupplier(){
        List<String> setSupplier = DashboardController.getInstance().setSupplier();
        cbInventoryAddSelectSupplier.getItems().addAll(setSupplier);
    }

    public void setProduct(){
        List<String> product = DashboardController.getInstance().getProduct();
        cbInventorySearchSelectProduct.getItems().addAll(product);
        cbUpdateInventorySelectProduct.getItems().addAll(product);
        cbDeleteInventorySelectProduct.getItems().addAll(product);
    }

    public void btnInventorySearchOnAction(ActionEvent actionEvent) {
        Inventory searchProduct = DashboardController.getInstance().searchProduct(cbInventorySearchSelectProduct.getSelectionModel().getSelectedItem().toString());
        txtInventorySearchName.setText(searchProduct.getName());
        txtInventorySearchCategory.setText(searchProduct.getCategory());
        txtInventorySearchSize.setText(searchProduct.getSize());
        txtInventorySearchPrice.setText(String.valueOf(searchProduct.getPrice()));
        txtInventorySearchQty.setText(String.valueOf(searchProduct.getQty()));
        txtInventorySearchSupplierId.setText(searchProduct.getSupplier());
    }

    public void cbUpdateInventoryOnAction(ActionEvent actionEvent) {
        Inventory searchProduct = DashboardController.getInstance().searchProduct(cbUpdateInventorySelectProduct.getSelectionModel().getSelectedItem().toString());
        txtUpdateInventoryName.setText(searchProduct.getName());
        txtInventoryUpdateCategory.setText(searchProduct.getCategory());
        txtInventoryUpdateSize.setText(searchProduct.getSize());
        txtInventoryUpdatePrice.setText(String.valueOf(searchProduct.getPrice()));
        txtInventoryUpdateQty.setText(String.valueOf(searchProduct.getQty()));
        txtInventoryUpdateSupplierId1.setText(searchProduct.getSupplier());
    }

    public void btnUpdateInventotyOnAction(ActionEvent actionEvent) {
        boolean updateInventory = DashboardController.getInstance().updateInventory(txtUpdateInventoryName.getText(), txtInventoryUpdateCategory.getText(), txtInventoryUpdateSize.getText(), Double.parseDouble(txtInventoryUpdatePrice.getText()), Integer.parseInt(txtInventoryUpdateQty.getText()), txtInventoryUpdateSupplierId1.getText());
        if (updateInventory){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Inventory Updated Successfully");
            alert.show();

            txtUpdateInventoryName.clear();
            //cbInventoryAddSelectCategory.setValue(null);
            //cbInventoryAddSelectSize.setValue(null);
            txtInventoryUpdateCategory.clear();
            txtInventoryUpdateSize.clear();
            //cbInventoryAddSelectSupplier.setValue(null);
            txtInventoryUpdatePrice.clear();
            txtInventoryUpdateQty.clear();
            txtInventoryUpdateSupplierId1.clear();

            /*txtDeleteSupplierCompany.clear();
            txtDeleteSupplierItem.clear();*/
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Inventory Updated Fail");
            alert.show();

            txtUpdateInventoryName.clear();
            //cbInventoryAddSelectCategory.setValue(null);
            //cbInventoryAddSelectSize.setValue(null);
            txtInventoryUpdateCategory.clear();
            txtInventoryUpdateSize.clear();
            //cbInventoryAddSelectSupplier.setValue(null);
            txtInventoryUpdatePrice.clear();
            txtInventoryUpdateQty.clear();
            txtInventoryUpdateSupplierId1.clear();
        }
    }

    public void btnDeleteInventotyOnAction(ActionEvent actionEvent) {
        boolean deleteInventory = DashboardController.getInstance().deleteInventory(cbDeleteInventorySelectProduct.getSelectionModel().getSelectedItem().toString());
        if (deleteInventory){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Inventory Deleted Successfully");
            alert.show();

            txtDeleteInventoryName.clear();
            //cbInventoryAddSelectCategory.setValue(null);
            //cbInventoryAddSelectSize.setValue(null);
            txtInventoryDeleteCategory.clear();
            txtInventoryUpdateSize.clear();
            //cbInventoryAddSelectSupplier.setValue(null);
            //txtInventoryDeleteSize.clear();
            txtInventoryDeletePrice.clear();
            txtInventoryDeleteSupplierId.clear();
            txtInventoryDeleteQty.clear();

            /*txtDeleteSupplierCompany.clear();
            txtDeleteSupplierItem.clear();*/
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Inventory Deleted Fail");
            alert.show();

            txtDeleteInventoryName.clear();
            //cbInventoryAddSelectCategory.setValue(null);
            //cbInventoryAddSelectSize.setValue(null);
            txtInventoryDeleteCategory.clear();
            txtInventoryUpdateSize.clear();
            //cbInventoryAddSelectSupplier.setValue(null);
            //txtInventoryDeleteSize.clear();
            txtInventoryDeletePrice.clear();
            txtInventoryDeleteSupplierId.clear();
            txtInventoryDeleteQty.clear();

            /*txtDeleteSupplierCompany.clear();
            txtDeleteSupplierItem.clear();*/
        }
    }

    public void cbDeleteInventoryOnAction(ActionEvent actionEvent) {
        Inventory searchProduct = DashboardController.getInstance().searchProduct(cbDeleteInventorySelectProduct.getSelectionModel().getSelectedItem().toString());
        txtDeleteInventoryName.setText(searchProduct.getName());
        txtInventoryDeleteCategory.setText(searchProduct.getCategory());
        txtInventoryDeleteSize.setText(searchProduct.getSize());
        txtInventoryDeletePrice.setText(String.valueOf(searchProduct.getPrice()));
        txtInventoryDeleteQty.setText(String.valueOf(searchProduct.getQty()));
        txtInventoryDeleteSupplierId.setText(searchProduct.getSupplier());
    }
}
