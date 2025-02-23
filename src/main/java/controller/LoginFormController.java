package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;
import service.ServiceFactory;
import service.custom.*;
import util.ServiceType;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class LoginFormController {
    public JFXPasswordField txtPassword;
    public JFXTextField txtEmail;
    public AnchorPane hypLogin;
    public AnchorPane hypForget;
    public TextField txtForgetEmail;
    public TextField txtOtp;
    public TextField txtNewPass;
    public TextField txtConPass;

    UserService userService= ServiceFactory.getInstance().getServiceType(ServiceType.USER);
    SupplierService supplierService=ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrdersService ordersService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDERS);
    EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
            txtEmail.clear();
            txtPassword.clear();

            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please enter your email and password!");

            // Display the alert
            errorAlert.showAndWait();
            return;
        }

        String loginInfo = userService.getLoginInfo(txtEmail.getText(), txtPassword.getText());

        if (loginInfo != null) {
            try {
                // Load the new scene
                Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/OwnerDashboardForm.fxml"));

                // Create and show new stage
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                // Get the current window and close it
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace(); // Or use a logger to handle exceptions
            }

            DashboardFormController dashboardFormController = new DashboardFormController();
            dashboardFormController.dashbordButtonShow(loginInfo);
        }else {
            txtEmail.clear();
            txtPassword.clear();

            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Invalid email and password!");

            // Display the alert
            errorAlert.showAndWait();
        }
        txtEmail.clear();
        txtPassword.clear();
    }

    public void hypForgetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        hypForget.toFront();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        hypLogin.toFront();
    }

    public void btnSentOnAction(ActionEvent actionEvent) {
        if (txtForgetEmail.getText().isEmpty()){
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please enter your email!");

            // Display the alert
            errorAlert.showAndWait();
            txtForgetEmail.clear();
            return;
        }
        boolean sentOTP = userService.sendOTP(txtForgetEmail.getText());
        if (sentOTP) {
            // Create an Information Alert
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Information Dialog");
            infoAlert.setHeaderText("Operation Successful");
            infoAlert.setContentText("Your OTP has been sent successfully.");

            // Show the alert
            infoAlert.showAndWait();
        }
    }

    public boolean checkPassword(){
        if (txtNewPass.getText().equals(txtConPass.getText())){
            return userService.equalsOTP(Integer.valueOf(txtOtp.getText()), txtOtp.getText());
        }
        return false;
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) throws IOException {
        if (txtOtp.getText().isEmpty() || txtNewPass.getText().isEmpty() || txtConPass.getText().isEmpty()){
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Please enter OTP and password!");

            // Display the alert
            errorAlert.showAndWait();
            txtOtp.clear();
            txtNewPass.clear();
            txtConPass.clear();
            return;
        } else if (!(txtNewPass.getText().equals(txtConPass.getText()))) {
            // Creating an Error Alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("An Error Occurred");
            errorAlert.setContentText("Doesn't match your password. Please enter your correct password!");

            // Display the alert
            errorAlert.showAndWait();
            txtOtp.clear();
            txtNewPass.clear();
            txtConPass.clear();
            return;
        }

        boolean checked = checkPassword();
        if (checked){
            //System.out.println(txtForgetEmail.getText().isEmpty());
            boolean forgottenPassword = userService.forgetPassword(txtForgetEmail.getText(), txtConPass.getText());
            if (forgottenPassword){
                // Create an Information Alert
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Information Dialog");
                infoAlert.setHeaderText("Operation Successful");
                infoAlert.setContentText("Password updated successfully!");

                // Show the alert
                infoAlert.showAndWait();

            }else {
                // Creating an Error Alert
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error Dialog");
                errorAlert.setHeaderText("An Error Occurred");
                errorAlert.setContentText("Something went wrong! Please try again.");

                // Display the alert
                errorAlert.showAndWait();
            }
        }

        txtForgetEmail.clear();
        txtOtp.clear();
        txtNewPass.clear();
        txtConPass.clear();
    }
}
