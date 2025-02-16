package service;

import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case USER:return (T) UserServiceImpl.getInstance();
            case EMPLOYEE:return (T) EmployeeServiceImpl.getInstance();
            case PRODUCT:return (T) ProductServiceImpl.getInstance();
            case SUPPLIER:return (T) SupplierServiceImpl.getInstance();
            case ORDERS:return (T) OrdersServiceImpl.getInstance();
        }
        return null;
    }
}
