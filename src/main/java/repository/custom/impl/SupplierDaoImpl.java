package repository.custom.impl;

import DBConnection.connection;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;
import model.SupplierTable;
import repository.custom.SupplierDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean add(SupplierEntity entity) {
        return false;
    }

    @Override
    public SupplierEntity search(Integer integer) {
        return null;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<SupplierEntity> getAll() {
        return List.of();
    }

    @Override
    public ObservableList<SupplierTable> getAllSupplier() {
        ObservableList<SupplierTable> observableList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select * from supplier");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer supplierID = resultSet.getInt("SupplierID");
                String name = resultSet.getString("Name");
                String company = resultSet.getString("Company");
                String email = resultSet.getString("Email");
                String item = resultSet.getString("Item");

                observableList.add(new SupplierTable(supplierID,name,company,email,item));
            }
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(Integer supplierId) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("DELETE FROM supplier WHERE SupplierID = ?");
            preparedStatement.setInt(1,supplierId);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSupplier(SupplierEntity supplierEntity) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("UPDATE supplier SET Name = ?, Company = ?, Email = ?, Item = ? WHERE SupplierID = ?");
            preparedStatement.setObject(1,supplierEntity.getName());
            preparedStatement.setObject(2,supplierEntity.getCompany());
            preparedStatement.setObject(3,supplierEntity.getEmail());
            preparedStatement.setObject(4,supplierEntity.getItem());
            preparedStatement.setObject(5,supplierEntity.getSupplierID());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addSupplier(SupplierEntity supplierEntity) {
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("INSERT INTO supplier (Name, Company, Email, Item) VALUES (?, ?, ?, ?)");
            preparedStatement.setObject(1,supplierEntity.getName());
            preparedStatement.setObject(2,supplierEntity.getCompany());
            preparedStatement.setObject(3,supplierEntity.getEmail());
            preparedStatement.setObject(4,supplierEntity.getItem());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getAllCSupllierIds() {
        List<String> allIdList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.getInstance().getConnection().prepareStatement("select SupplierID from supplier order by SupplierID asc");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allIdList.add(resultSet.getString("SupplierID"));
            }
            return allIdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
