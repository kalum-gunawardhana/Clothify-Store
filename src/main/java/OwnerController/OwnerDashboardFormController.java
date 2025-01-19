package OwnerController;

import Model.Employee;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OwnerDashboardFormController implements Initializable {
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

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        /*URL resource = this.getClass().getResource("/View/OwnerDashboardForm.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.apEmployee.getChildren().clear();
        this.apEmployee.getChildren().add(load);*/
        apEmployee.toFront();
    }

    public void btnAddEmpOnAction(ActionEvent actionEvent) {
        boolean addedEmp = OwnerDashboardController.getInstance().addEmp(txtAddEmpName.getText(), txtAddEmpEma.getText(), txtAddEmpRol.getText(), Integer.valueOf(txtAddEmpAdmId.getText()));
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
        Employee empData = (Employee) OwnerDashboardController.getInstance().getEmpData(selectEmail);
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
        List<String> empEmail = OwnerDashboardController.getInstance().getEmpEmail();
        cbSearchEmpEmail.getItems().addAll(empEmail);
        cbSelectEmpUpdate.getItems().addAll(empEmail);
        cbEmpDelete.getItems().addAll(empEmail);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEmpEmail();
    }

    public void cbSelectEmpSearchEmail(ActionEvent actionEvent) {
        selectEmail = cbSearchEmpEmail.getSelectionModel().getSelectedItem().toString();
        //System.out.println(selectEmail);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean updated = OwnerDashboardController.getInstance().updateEmp(selectUpdateEmail, txtEmpNameUpdate.getText(), txtEmpRoleUpdate.getText(), Integer.parseInt(txtEmpAdmIDUpdate.getText()));
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
        Employee empData = (Employee) OwnerDashboardController.getInstance().getEmpData(selectUpdateEmail);
        txtEmpNameUpdate.setText(empData.getName());
        txtEmpRoleUpdate.setText(empData.getRole());
        txtEmpAdmIDUpdate.setText(String.valueOf(empData.getAdminId()));
    }

    public void selectEmpDeleteOnAction(ActionEvent actionEvent) {
        selectEmpDeletEmail = cbEmpDelete.getSelectionModel().getSelectedItem().toString();
        Employee empData = (Employee) OwnerDashboardController.getInstance().getEmpData(selectEmpDeletEmail);
        txtEmpDeleteName.setText(empData.getName());
        txtEmpDeleteRole.setText(empData.getRole());
        txtEmpDeleteOnAction.setText(String.valueOf(empData.getAdminId()));
    }

    public void btnEmpDeletOnAction(ActionEvent actionEvent) {
        boolean deleted = OwnerDashboardController.getInstance().deleteEmployeeByEmail(cbEmpDelete.getSelectionModel().getSelectedItem().toString());
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
}
