package views;

import dao.AppointmentDao;
import dao.ContactDao;
import dao.CustomerDao;
import dao.DivisionDao;
import dao.UserDao;
import daoimpl.AppointmentDaoImpl;
import daoimpl.ContactDaoImpl;
import daoimpl.CustomerDaoImpl;
import daoimpl.DivisionDaoImpl;
import daoimpl.UserDaoImpl;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import models.Appointment;
import models.Contact;
import models.Customer;
import models.Division;
import models.User;



public class AppointmentController implements Initializable{
    
    @FXML
    private TextField aapintmentNameField;
    @FXML
    private ComboBox customerCombo;
    @FXML
    private ComboBox userCombo;
    @FXML
    private ComboBox contactCombo;
    @FXML
    private TextField typeField;
    @FXML
    private TextField locationField;
    @FXML
    private TextArea descriptionArea;
    
    @FXML
    private TableColumn<Appointment, Integer> idColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> customerColumn;
    @FXML
    private TableColumn<Appointment, String> userColumn;
    @FXML
    private TableColumn<Appointment, String> contactColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
   @FXML
    private TableColumn<Appointment, Date> createDateColumn;
    @FXML
    private TableColumn<Appointment, String> createdByColumn;
    @FXML
    private TableColumn<Appointment, Date> updateDateColumn;
    @FXML
    private TableColumn<Appointment, String> updatedByColumn;
    
    @FXML 
     private TableView<Appointment> tableView;
     
    
    Appointment selectedAppointment = null;
    Integer selectedIndex = null;
    
    
    CustomerDao customerDaoImpl = new CustomerDaoImpl();
    UserDao userDaoImpl = new UserDaoImpl();
    ContactDao contactDaoImpl = new ContactDaoImpl();
    AppointmentDao appointmentDaoImpl = new AppointmentDaoImpl();
    
    @FXML
    public void goBack(ActionEvent e){
    
        Controller.homeStage.show();
        HomeController.getStage.close();
        
    }
    
    
    public void fillCustomerCombo(){
    
        ObservableList<Customer> allCustomers = customerDaoImpl.getAllCustomer();
        
        for (int i = 0; i < allCustomers.size(); i++) {
            customerCombo.getItems().add(allCustomers.get(i).getCustomerName());
        }
    }
    
    public void fillUserCombo(){
    
        ObservableList<User> allUsers = userDaoImpl.getAllUser();
        
        for (int i = 0; i < allUsers.size(); i++) {
            userCombo.getItems().add(allUsers.get(i).getName());
        }
    }
    
    public void fillContactCombo(){
    
        ObservableList<Contact> allContact = contactDaoImpl.getAllContact();
        
        for (int i = 0; i < allContact.size(); i++) {
            contactCombo.getItems().add(allContact.get(i).getContactName());
        }
    }
    
    
    public void showAppointments(){
    
        ObservableList<Appointment> list = appointmentDaoImpl.getAllAppointment();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentTitle"));
                
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentDescription"));
        
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentLocation"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentType"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentStartTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentEndTime"));
        
        //System.out.println("user name in aptctrl: "+list.get(0).getUserName());
        userColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userName"));

        customerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("customerName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contactName"));
        
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointType"));
        createDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("createDate"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createdBy"));
        updateDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("updatedDate"));
        updatedByColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("updatedBy"));
        

        tableView.setItems(list);
    }
    
   
    public void getSelected(){
        tableView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent t){
                selectedAppointment = (Appointment) tableView.getSelectionModel().getSelectedItem();
                selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        
        
        if (selectedIndex <= -1) {
            return;
        }
            Integer id = Integer.parseInt(idColumn.getCellData(selectedIndex).toString());
            String title = titleColumn.getCellData(selectedIndex).toString();
            String customerName = customerColumn.getCellData(selectedIndex);
            String userName = userColumn.getCellData(selectedIndex).toString();
            String contactName = contactColumn.getCellData(selectedIndex).toString();
            String description = descriptionColumn.getCellData(selectedIndex).toString();
            String location = locationColumn.getCellData(selectedIndex).toString();
            String type = typeColumn.getCellData(selectedIndex).toString();
            
                System.out.println("Data from combos: "+userName+", "+customerName+", "+contactName);
            
            aapintmentNameField.setText(title);
            descriptionArea.setText("");
            typeField.setText(type);
            locationField.setText(location);
            descriptionArea.setText(description);
            
           customerCombo.setValue(customerName);
           userCombo.setValue(userName);
           contactCombo.setValue(contactName);
            
        }
        
            
        });
    }


    @FXML
    public void add(){
        String title = aapintmentNameField.getText();
        String description = descriptionArea.getText();
        String type = typeField.getText();
        
        
        String location = locationField.getText();
        
        if (title == null || title.equals("") || description.equals("") || type.equals("") || userCombo.getSelectionModel().getSelectedItem() == null || customerCombo.getSelectionModel().getSelectedItem() == null || contactCombo.getSelectionModel().getSelectedItem()==null) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Fill all fields", JOptionPane.ERROR_MESSAGE);
        }else{
        
            String userName = userCombo.getSelectionModel().getSelectedItem().toString();
        
        String customerName = customerCombo.getSelectionModel().getSelectedItem().toString();
        
        String contactName = contactCombo.getSelectionModel().getSelectedItem().toString();
        User user = userDaoImpl.getUserById(userDaoImpl.getUserIdByName(userName));
        Customer customer = customerDaoImpl.getCustomerById(customerDaoImpl.getCustomerIdByName(customerName));
        Contact contact = contactDaoImpl.getContactById(contactDaoImpl.getContactIdByName(contactName));
             
            Appointment appointment = new Appointment();
            appointment.setAppointmentTitle(title);
            appointment.setAppointType(type);
            appointment.setAppointmentDescription(description);
            appointment.setAppointmentLocation(location);
            appointment.setContact(contact);
            appointment.setCustomer(customer);
            appointment.setUser(user);
            appointment.setCreateDate(new Date());
            appointment.setCreatedBy(Controller.loginUser.getName());
            
            int result = appointmentDaoImpl.addAppointment(appointment);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Appointment added successfully");
                showAppointments();
            }
        }
        
    }
    
    @FXML
    public void update(){
        getSelected();
        
        if (selectedAppointment==null || selectedIndex <= -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to update", "Select Record", JOptionPane.ERROR_MESSAGE);
            
        }else{
       
            String title = aapintmentNameField.getText();
            String description = descriptionArea.getText();
            String type = typeField.getText();
            
            Integer contactId = contactDaoImpl.getContactIdByName(contactCombo.getValue().toString());
            Integer customerId = customerDaoImpl.getCustomerIdByName(customerCombo.getValue().toString());
            Integer userId = userDaoImpl.getUserIdByName(userCombo.getValue().toString());
        
            Integer appointmentId = selectedAppointment.getAppointmentId();
            System.out.println("Appointment ID: "+appointmentId);    
            
            Customer c = new Customer(0, "", "", "", "");
            c.setCustomerId(customerId);
            User u = new User(0, "", "");
            u.setId(userId);
            Contact c1 = new Contact();
            c1.setContactId(contactId);
            
            Appointment appointment = appointmentDaoImpl.getAppointmentById(appointmentId);
            appointment.setAppointmentTitle(title);
            appointment.setAppointType(type);
            appointment.setAppointmentDescription(description);
            appointment.setUser(u);
            appointment.setCustomer(c);
            appointment.setContact(c1);
            appointment.setUpdatedBy(Controller.loginUser.getName());
            appointment.setUpdatedDate(new Date());
            
            

            int result= appointmentDaoImpl.updateAppointment(appointment);
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Record updated successfully.");
                showAppointments();
                clear();
            }
            
        
        }
    }
    
    @FXML
    public void delete(){
        getSelected();
        
        if (selectedAppointment==null || selectedIndex <= -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to delete", "Select Record", JOptionPane.ERROR_MESSAGE);
            
        }else{
            Integer appointmentId = selectedAppointment.getAppointmentId();
           

            int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete this appointment?");
            
            if (choice==0) {
                int result = appointmentDaoImpl.deleteAppointment(appointmentId);
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Appointment deleted successfully.");
                    showAppointments();
                    clear();
                }
            }
            
            
        }
    }
    
    @FXML
    public void clear(){
        aapintmentNameField.setText(null);
        typeField.setText(null);
        descriptionArea.setText(null);
        locationField.setText(null);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        fillContactCombo();
        fillCustomerCombo();
        fillUserCombo();
        showAppointments();
        getSelected();
    }
    
    
}
