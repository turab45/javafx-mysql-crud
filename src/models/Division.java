package models;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

public class Division {

    Integer divisionId;
    private SimpleStringProperty divisionName;
    Country country;
    Date createDate;
    String createdBy;
    Date updatedDate;

    String updatedBy;

    public Division(String divisionName) {
        this.divisionName = new SimpleStringProperty(divisionName);
    }

    public String getDivisionName() {
        return divisionName.get();
    }
    
    public SimpleStringProperty divisionNameProperty() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName.set(divisionName);
    }

    
    
    
    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
