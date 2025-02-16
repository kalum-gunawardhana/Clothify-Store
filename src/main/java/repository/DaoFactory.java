package repository;

import repository.custom.impl.*;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType daoType){
        switch (daoType){
            case USER:return (T) new UserDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case PRODUCT:return (T) new ProductDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case ORDERS:return (T) new OrdersDaoImpl();
        }
        return null;
    }
}
