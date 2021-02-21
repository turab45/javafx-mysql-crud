package models;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty customerName;
    private SimpleStringProperty customerAddress;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty phone;
    private Division division;
    private SimpleStringProperty divisionName = new SimpleStringProperty();
    private Date createDate;
    private SimpleStringProperty createdBy = new SimpleStringProperty();
    private Date updatedDate;
    private SimpleStringProperty updatedBy = new SimpleStringProperty();

    public Customer(Integer customerId,String customerName, String customerAddress, String postalCode, String phone) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.customerAddress = new SimpleStringProperty(customerAddress);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.phone = new SimpleStringProperty(phone);
        
        
    }
    
    public String getDivisionName(){
    
        return this.divisionName.get();
    }
    
    public void setDivisionName(String divisionName){
        this.divisionName.set(divisionName);
    }

    public int getCustomerId() {
        return customerId.get();
    }
    
    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId.set(customerId);
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }
    
    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public SimpleStringProperty customerAddressProperty() {
        return customerAddress;
    }
    
    public String getCustomerAddress() {
        return customerAddress.get();
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress.set(customerAddress);
    }

    public SimpleStringProperty postalCodeProperty() {
        return postalCode;
    }
    
    public String getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public SimpleStringProperty createdByProperty() {
        return createdBy;
    }
    
    public String getCreatedBy() {
        return createdBy.get();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public SimpleStringProperty updatedByProperty() {
        return updatedBy;
    }
    
    public String getUpdatedBy() {
        return updatedBy.get();
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy.set(updatedBy);
    }

    public void setUpdateDate(Date date) {
        this.updatedDate = date;
    }
    
    
}
