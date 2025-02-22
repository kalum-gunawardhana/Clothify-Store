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

    public static String generated;

    UserService userService= ServiceFactory.getInstance().getServiceType(ServiceType.USER);
    SupplierService supplierService=ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrdersService ordersService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDERS);
    EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
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
        sendOTP(txtForgetEmail.getText());
    }

    public String generateOTP(){
        String randomNumeric = RandomStringUtils.randomNumeric(6);
        return randomNumeric;
    }

    public void sendOTP(String recipientEmail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String senderEmail = recipientEmail;
        final String senderPassword = "jqnr tmef vbqg szon";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        generated = generateOTP();

        Message message = prepareMessage(session, senderEmail, recipientEmail, generated);

        if (message != null) {
            try {
                Transport.send(message);
                System.out.println("OTP email sent successfully to " + recipientEmail);
            } catch (MessagingException e) {
                System.err.println("Error sending OTP email: " + e.getMessage());
                throw new RuntimeException("Failed to send email", e);
            }
        } else {
            System.err.println("Failed to prepare email message.");
        }
    }

    private Message prepareMessage(Session session, String senderEmail, String recipientEmail, String otp) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Dear user,\n\nYour OTP code is: " + otp + "\n\nThank you!");
            return message;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean checkPassword(){
        if (txtNewPass.getText().equals(txtConPass.getText())){
            if (txtOtp.getText().equals(generated)){
                return true;
            }
        }
        return false;
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) throws IOException {
        boolean checked = checkPassword();
        if (checked){
            boolean forgottenPassword = userService.forgetPassword(txtForgetEmail.getText(), txtConPass.getText());
            if (forgottenPassword){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Password Updated Successfully");
                alert.show();

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText("Update Fail");
                alert.show();
            }
        }

        txtForgetEmail.clear();
        txtOtp.clear();
        txtNewPass.clear();
        txtConPass.clear();
    }
}
