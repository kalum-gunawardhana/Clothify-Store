package controller.service;

public interface LoginService {
     String getLoginInfo(String email, String password);
     boolean forgetPassword(String email, String password);
}
