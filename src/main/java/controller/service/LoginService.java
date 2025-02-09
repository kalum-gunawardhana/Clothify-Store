package controller.service;

public interface LoginService {
     boolean getLoginInfo(String email, String password);
     boolean forgetPassword(String email, String password);
}
