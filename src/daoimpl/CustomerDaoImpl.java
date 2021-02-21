package daoimpl;

import dao.CustomerDao;
import database.Database;
import models.Customer;
import models.Division;
import models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDaoImpl implements CustomerDao {

    Connection conn = Database.getConnection();

    @Override
    public Integer addCustomer(Customer customer) {
        Integer row = null;
        try {
            String query = "INSERT INTO customers(Customer_Name, Address, Postal_Code,Phone, Division_ID,Create_Date, Created_By) VALUES(?,?,?,?,?,?,?)";

            Date createdDate = new Date(customer.getCreateDate().getTime());

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getCustomerAddress());
            pstmt.setString(3, customer.getPostalCode());
            pstmt.setString(4, customer.getPhone());
            pstmt.setInt(5, customer.getDivision().getDivisionId());
            pstmt.setDate(6, createdDate);
            pstmt.setString(7, customer.getCreatedBy());


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateCustomer(Customer customer) {
        Integer row = null;
        try {
            String query = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?,Phone=?, Division_ID=?,Last_Update=?, Last_Updated_By=? WHERE Customer_ID=?";

            Date updateDate = new Date(customer.getUpdatedDate().getTime());

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getCustomerAddress());
            pstmt.setString(3, customer.getPostalCode());
            pstmt.setString(4, customer.getPhone());
            pstmt.setInt(5, customer.getDivision().getDivisionId());
            pstmt.setDate(6, updateDate);
            pstmt.setString(7, customer.getUpdatedBy());
            pstmt.setInt(8, customer.getCustomerId());


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteCustomer(int customerId) {
        Integer row = null;
        try {
            String query = "DELETE FROM customers WHERE Customer_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, customerId);


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;

    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM customers WHERE Customer_ID=?";


            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, customerId);


            rs = pstmt.executeQuery();

            if (rs.next()){
                customer = new Customer(0,"","","","");

                Division division = new Division("");
                division.setDivisionId(rs.getInt("Division_ID"));
                
                customer.setCustomerId(rs.getInt("Customer_ID"));
                customer.setCustomerName(rs.getString("Customer_Name"));
                customer.setCustomerAddress(rs.getString("Address"));
                customer.setPhone(rs.getString("Phone"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setDivision(division);
                customer.setCreateDate(rs.getDate("Create_Date"));
                customer.setCreatedBy(rs.getString("Created_By"));
                customer.setUpdatedDate(rs.getDate("Last_Update"));
                customer.setUpdatedBy(rs.getString("Last_Updated_By"));
            }

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return customer;
    }

    @Override
    public Integer getCustomerIdByName(String customer) {
        Integer row = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            String query = "SELECT Customer_ID FROM customers WHERE Customer_Name=?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, customer);
            rs = pstmt.executeQuery();

            if (rs.next())
                row = rs.getInt("Customer_ID");

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return row;
    }

    DivisionDaoImpl divisionDaoImpl = new DivisionDaoImpl();
    
    @Override
    public ObservableList<Customer> getAllCustomer() {
        ObservableList<Customer> allCustomer = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM customers";


            PreparedStatement pstmt = conn.prepareStatement(query);


            rs = pstmt.executeQuery();

            while (rs.next()){
                Customer customer = new Customer(0,"", "", "", "");

                Division division = divisionDaoImpl.getDivisionById(rs.getInt("Division_ID"));
                
                customer.setDivisionName(division.getDivisionName());
                
                customer.setCustomerId(rs.getInt("Customer_ID"));
                customer.setCustomerName(rs.getString("Customer_Name"));
                customer.setCustomerAddress(rs.getString("Address"));
                customer.setPhone(rs.getString("Phone"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setDivision(division);
                customer.setDivisionName(division.getDivisionName());
                customer.setCreateDate(rs.getDate("Create_Date"));
                customer.setCreatedBy(rs.getString("Created_By"));
                customer.setUpdatedDate(rs.getDate("Last_Update"));
                customer.setUpdatedBy(rs.getString("Last_Updated_By"));

                allCustomer.add(customer);
            }

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allCustomer;
    }
}
