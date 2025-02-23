package service.custom.impl;

import javafx.collections.ObservableList;
import model.User;
import model.UserTable;
import org.apache.commons.lang3.RandomStringUtils;
import repository.DaoFactory;
import repository.custom.*;
import service.custom.UserService;
import util.DaoType;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    public static String generated;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        return instance==null?instance=new UserServiceImpl():instance;
    }

    UserDao userDao= DaoFactory.getInstance().getDaoType(DaoType.USER);
    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrdersDao ordersDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public ObservableList<UserTable> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public boolean deleteUser(String email) {
        return userDao.deleteUser(email);
    }

    @Override
    public boolean updateUser(String name, String email, String password, String role) {
        return userDao.updateUser(name, email, password, role);
    }

    @Override
    public User searchUser(String email) {
        return userDao.searchUser(email);
    }

    @Override
    public boolean addUser(String name, String email, String password, String role) {
        return userDao.addUser(name, email, password, role);
    }

    @Override
    public boolean forgetPassword(String email, String password) {
        return userDao.forgetPassword(email, password);
    }

    @Override
    public String getLoginInfo(String email, String password) {
        return userDao.getLoginInfo(email, password);
    }

    @Override
    public boolean sendOTP(String recipientEmail) {
        return sendOtp(recipientEmail);
    }

    @Override
    public boolean equalsOTP(Integer OTP, String OTP1) {
        if (OTP1.equals(generated)){
            return true;
        }
        return false;
    }

    public boolean sendOtp(String recipientEmail) {
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
                return true;
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email", e);
            }
        }
        return false;
    }

    public String generateOTP(){
        String randomNumeric = RandomStringUtils.randomNumeric(6);
        return randomNumeric;
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
}
