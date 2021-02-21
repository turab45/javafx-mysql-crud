package models;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
    private SimpleIntegerProperty appointmentId = new SimpleIntegerProperty();
    private SimpleStringProperty appointmentTitle = new SimpleStringProperty();
     private SimpleStringProperty appointmentDescription = new SimpleStringProperty();
     private SimpleStringProperty appointmentLocation = new SimpleStringProperty();
     private SimpleStringProperty appointType = new SimpleStringProperty();
     private SimpleStringProperty appointmentStartTime = new SimpleStringProperty();
     private SimpleStringProperty appointmentEndTime = new SimpleStringProperty();
    private Customer customer;
    private SimpleStringProperty customerName = new SimpleStringProperty();
    private User user;
    private SimpleStringProperty userName = new SimpleStringProperty();
    private Contact contact;
    private SimpleStringProperty contactName = new SimpleStringProperty();
    private Date createDate;

    public String getContactName() {
        return contactName.get();
    }

    public void setContactName(String contactName) {
        this.contactName.set(contactName);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    private SimpleStringProperty createdBy = new SimpleStringProperty();
    private Date updatedDate;
    private SimpleStringProperty updatedBy = new SimpleStringProperty();

    public Integer getAppointmentId() {
        return appointmentId.get();
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId.set(appointmentId);
    }

    public String getAppointmentTitle() {
        return appointmentTitle.get();
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle.set(appointmentTitle);
    }

    public String getAppointmentDescription() {
        return appointmentDescription.get();
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription.set(appointmentDescription);
    }

    public String getAppointmentLocation() {
        return appointmentLocation.get();
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation.set(appointmentLocation);
    }

    public String getAppointType() {
        return appointType.get();
    }

    public void setAppointType(String appointType) {
        this.appointType.set(appointType);
    }

    public String getAppointmentStartTime() {
        return appointmentStartTime.get();
    }

    public void setAppointmentStartTime(String appointmentStartTime) {
        this.appointmentStartTime.set(appointmentStartTime);
    }

    public String getAppointmentEndTime() {
        return appointmentEndTime.get();
    }

    public void setAppointmentEndTime(String appointmentEndTime) {
        this.appointmentEndTime.set(appointmentEndTime);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getUpdatedBy() {
        return updatedBy.get();
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy.set(updatedBy);
    }
}
