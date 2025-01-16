package LoginController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.lang3.RandomStringUtils;

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

    public void btnLoginOnAction(ActionEvent actionEvent) {
        boolean loginInfo = LoginController.getInstance().getLoginInfo(txtEmail.getText(), txtPassword.getText());
        if (loginInfo){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Login Successfully");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Login Fail");
            alert.show();
        }
        txtEmail.clear();
        txtPassword.clear();
    }

    public void hypForgetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        //hypLogin.setVisible(false);
        hypForget.toFront();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        /*hypForget.setVisible(false);
        hypLogin.setVisible(true);
        hypLogin.toFront();*/
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
        // Email server configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Sender credentials
        final String senderEmail = recipientEmail;
        final String senderPassword = "jqnr tmef vbqg szon";

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        generated = generateOTP();
        //checkPassword(generated);

        // Prepare the email message
        Message message = prepareMessage(session, senderEmail, recipientEmail, generated);

        if (message != null) {
            try {
                // Send the email
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
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Dear user,\n\nYour OTP code is: " + otp + "\n\nThank you!");
            return message;
        } catch (Exception e) {
            //Logger.getLogger(EmailService.class.getName()).severe("Error creating email message: " + e.getMessage());
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

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        boolean checked = checkPassword();
        if (checked){
            //System.out.println("correct");
            boolean password = LoginController.getInstance().forgetPassword(txtForgetEmail.getText(), txtConPass.getText());
            if (password){
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
