package daoimpl;

import dao.AppointmentDao;
import database.Database;
import models.Appointment;
import models.Contact;
import models.Customer;
import models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentDaoImpl implements AppointmentDao {

    Connection conn = Database.getConnection();

    @Override
    public Integer addAppointment(Appointment appointment) {
        Integer row = null;
        try {
            String query = "INSERT INTO appointments(Title, Description, Location, Type, Customer_ID, User_ID, Contact_ID, Create_Date, Created_By) VALUES(?,?,?,?,?,?,?,?,?)";

            Date createDate = new Date(appointment.getCreateDate().getTime());

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, appointment.getAppointmentTitle());
            pstmt.setString(2, appointment.getAppointmentDescription());
            pstmt.setString(3, appointment.getAppointmentLocation());
            pstmt.setString(4, appointment.getAppointType());
            pstmt.setInt(5, appointment.getCustomer().getCustomerId());
            pstmt.setInt(6, appointment.getUser().getId());
            pstmt.setInt(7, appointment.getContact().getContactId());
            pstmt.setDate(8, createDate);
            pstmt.setString(9, appointment.getCreatedBy());

            row = pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateAppointment(Appointment appointment) {
        Integer row = null;
        try {
            String query = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?,Customer_ID=?, User_ID=?, Contact_ID=?, Last_Update=?, Last_Updated_By=? WHERE Appointment_ID=?";

            Date updateDate = new Date(appointment.getUpdatedDate().getTime());

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, appointment.getAppointmentTitle());
            pstmt.setString(2, appointment.getAppointmentDescription());
            pstmt.setString(3, appointment.getAppointmentLocation());
            pstmt.setString(4, appointment.getAppointType());
            pstmt.setInt(5, appointment.getCustomer().getCustomerId());
            pstmt.setInt(6, appointment.getUser().getId());
            pstmt.setInt(7, appointment.getContact().getContactId());
            pstmt.setDate(8, updateDate);
            pstmt.setString(9, appointment.getUpdatedBy());
            pstmt.setInt(10, appointment.getAppointmentId());

            row = pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteAppointment(int cppointmentId) {
        Integer row = null;

        try{
            String query = "DELETE FROM appointments WHERE Appointment_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cppointmentId);

            row = pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return row;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM appointments WHERE Appointment_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, appointmentId);

            rs = pstmt.executeQuery();

            if (rs.next()){
                appointment = new Appointment();

                User user = new User(0,"","");
                user.setId(rs.getInt("User_ID"));
                Customer customer = new Customer(0,"","","","");
                customer.setCustomerId(rs.getInt("Customer_ID"));
                Contact contact = new Contact();
                contact.setContactId(rs.getInt("Contact_ID"));

              
                appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                appointment.setAppointmentTitle(rs.getString("Title"));
                appointment.setAppointmentDescription(rs.getString("Description"));
                appointment.setAppointmentLocation(rs.getString("Location"));
                appointment.setAppointType(rs.getString("Type"));
                appointment.setAppointmentStartTime(rs.getTime("Start").toString());
                appointment.setAppointmentEndTime(rs.getTime("End").toString());
                appointment.setCreateDate(rs.getDate("Create_Date"));
                appointment.setCreatedBy(rs.getString("Created_By"));
                appointment.setUpdatedDate(rs.getDate("Last_Update"));
                appointment.setUpdatedBy(rs.getString("Last_Updated_By"));
                appointment.setUser(user);
                appointment.setContact(contact);
                appointment.setCustomer(customer);
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return appointment;
    }

    @Override
    public Integer getAppointmentIdByName(String appointment) {

        Integer id = null;
        ResultSet rs = null;
        try{
            String query = "Select Appointment_ID from appointments where Title=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, appointment);

            rs = pstmt.executeQuery();

            if (rs.next()){
                id = rs.getInt("Appointment_ID");
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return id;
    }
    
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
    ContactDaoImpl contactDaoImpl = new ContactDaoImpl();
            

    @Override
    public ObservableList<Appointment> getAllAppointment() {
        ObservableList<Appointment> allAppointment = FXCollections.observableArrayList();
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM appointments";

            PreparedStatement pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Appointment appointment = new Appointment();

                
                User u = userDaoImpl.getUserById(rs.getInt("User_ID"));
                System.out.println("founde user name : "+u.getName());
                
                appointment.setUserName(u.getName());
                
                
                Customer c = customerDaoImpl.getCustomerById(rs.getInt("Customer_ID"));
                appointment.setCustomerName(c.getCustomerName());
                
                
                Contact c1 = contactDaoImpl.getContactById(rs.getInt("Contact_ID"));
                appointment.setContactName(c1.getContactName());

                appointment.setUser(u);
                appointment.setContact(c1);
                appointment.setCustomer(c);
                appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                appointment.setAppointmentTitle(rs.getString("Title"));
                appointment.setAppointmentDescription(rs.getString("Description"));
                appointment.setAppointmentLocation(rs.getString("Location"));
                appointment.setAppointType(rs.getString("Type"));
                appointment.setAppointmentStartTime(rs.getTime("Start").toString());
                appointment.setAppointmentEndTime(rs.getTime("End").toString());
                appointment.setCreateDate(rs.getDate("Create_Date"));
                appointment.setCreatedBy(rs.getString("Created_By"));
                appointment.setUpdatedDate(rs.getDate("Last_Update"));
                appointment.setUpdatedBy(rs.getString("Last_Updated_By"));
                appointment.setUser(u);
                appointment.setContact(c1);
                appointment.setCustomer(c);

                allAppointment.add(appointment);
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allAppointment;
    }

    @Override
    public List<Appointment> getAppointmentsOf(int userId) {
        List<Appointment> allAppointments = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM appointments WHERE Customer_ID=?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                Appointment appointment = new Appointment();

                User user = new User(0,"","");
                user.setId(rs.getInt("User_ID"));
                
                Customer customer = new Customer(0,"","","","");
                customer.setCustomerId(rs.getInt("Customer_ID"));
                Contact contact = new Contact();
                contact.setContactId(rs.getInt("Contact_ID"));


                appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                appointment.setAppointmentTitle(rs.getString("Title"));
                appointment.setAppointmentDescription(rs.getString("Description"));
                appointment.setAppointmentLocation(rs.getString("Location"));
                appointment.setAppointType(rs.getString("Type"));
                appointment.setAppointmentStartTime(rs.getTime("Start").toString());
                appointment.setAppointmentEndTime(rs.getTime("End").toString());
                appointment.setCreateDate(rs.getDate("Create_Date"));
                appointment.setCreatedBy(rs.getString("Created_By"));
                appointment.setUpdatedDate(rs.getDate("Last_Update"));
                appointment.setUpdatedBy(rs.getString("Last_Updated_By"));
                appointment.setUser(user);
                appointment.setContact(contact);
                appointment.setCustomer(customer);

                allAppointments.add(appointment);
            }
            
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allAppointments;
    }
}
