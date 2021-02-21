package daoimpl;

import dao.ContactDao;
import database.Database;
import models.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactDaoImpl implements ContactDao {

    Connection conn = Database.getConnection();

    @Override
    public Integer addContact(Contact contact) {

        Integer row = null;

        try{
            String query = "INSERT INTO contacts(Contact_Name, Email) VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, contact.getContactName());
            pstmt.setString(2, contact.getContactEmail());

            row = pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return row;
    }

    @Override
    public Integer updateContact(Contact contact) {
        Integer row = null;

        try{
            String query = "UPDATE contacts SET Contact_Name=?, Email=? WHERE Contact_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, contact.getContactName());
            pstmt.setString(2, contact.getContactEmail());
            pstmt.setInt(3, contact.getContactId());

            row = pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return row;
    }

    @Override
    public Integer deleteContact(int contactId) {
        Integer row = null;

        try{
            String query = "DELETE FROM contacts WHERE Contact_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, contactId);

            row = pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return row;
    }

    @Override
    public Contact getContactById(int contactId) {
        Contact contact = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM contacts WHERE Contact_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, contactId);

            rs = pstmt.executeQuery();

            if (rs.next()){
                contact = new Contact();
                contact.setContactId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setContactEmail(rs.getString("Email"));
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return contact;
    }

    @Override
    public Integer getContactIdByName(String contact) {

        Integer id = null;
        ResultSet rs = null;
        try{
            String query = "Select Contact_ID from contacts where Contact_Name=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, contact);

            rs = pstmt.executeQuery();

            if (rs.next()){
                id = rs.getInt("Contact_ID");
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public ObservableList<Contact> getAllContact() {
        ObservableList<Contact> allContact = FXCollections.observableArrayList();
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM contacts";

            PreparedStatement pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();

            while(rs.next()){
                Contact contact = new Contact();
                contact.setContactId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setContactEmail(rs.getString("Email"));

                allContact.add(contact);
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allContact;
    }
}
