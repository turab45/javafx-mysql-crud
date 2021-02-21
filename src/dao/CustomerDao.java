package dao;

import models.Customer;

import java.util.List;
import javafx.collections.ObservableList;

public interface CustomerDao {
    public Integer addCustomer(Customer customer);
    public Integer updateCustomer(Customer customer);
    public Integer deleteCustomer(int customerId);
    public Customer getCustomerById(int customerId);
    public Integer getCustomerIdByName(String customer);
    public ObservableList<Customer> getAllCustomer();
}
