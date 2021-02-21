package views;

import dao.AppointmentDao;
import dao.CustomerDao;
import dao.DivisionDao;
import dao.UserDao;
import daoimpl.AppointmentDaoImpl;
import daoimpl.CustomerDaoImpl;
import daoimpl.DivisionDaoImpl;
import daoimpl.UserDaoImpl;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import models.Appointment;
import models.Customer;
import models.Division;
import models.User;

public class CustomerController implements Initializable{
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextArea addressField;
    @FXML
    private ComboBox divisionCombo;

    @FXML
    private TableColumn<Customer, Integer> idColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> postalCodeColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> divisionColumn;
    @FXML
    private TableColumn<Customer, Date> createDateColumn;
    @FXML
    private TableColumn<Customer, String> createdByColumn;
    @FXML
    private TableColumn<Customer, Date> updateDateColumn;
    @FXML
    private TableColumn<Customer, String> updatedByColumn;

    @FXML
    private TableView<Customer> tableView;
    
    AppointmentDao appointmentDaoImpl = new AppointmentDaoImpl();
    CustomerDao customerDaoImpl = new CustomerDaoImpl();
    DivisionDao divisionDaoImpl = new DivisionDaoImpl();
    
    Customer selectedCutomer = null;
    int selectedIndex = -1;
    
    public void showCustomer() {
       ObservableList<Customer> list = customerDaoImpl.getAllCustomer();

       
        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        
        divisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("divisionName"));
        
        
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        createDateColumn.setCellValueFactory(new PropertyValueFactory<Customer, Date>("createDate"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("createdBy"));
        updateDateColumn.setCellValueFactory(new PropertyValueFactory<Customer, Date>("updatedDate"));
        updatedByColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("updatedBy"));
        
        tableView.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCustomer();
        fillCombo();
        getSelected();
    }
    
    public void fillCombo(){
    
        ObservableList<Division> allDivisions = divisionDaoImpl.getAllDivision();
        
        for (int i = 0; i < allDivisions.size(); i++) {
            divisionCombo.getItems().add(allDivisions.get(i).getDivisionName());
        }
        
    }
    
    @FXML
    public void onClick(){
    
//        selectedCutomer = tableView.getSelectionModel().getSelectedItem();
//        selectedIndex = tableView.getSelectionModel().getSelectedIndex();
    }
    
    public void getSelected(){
        tableView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent t){
                selectedCutomer = tableView.getSelectionModel().getSelectedItem();
                selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        
        
        if (selectedIndex <= -1) {
            JOptionPane.showConfirmDialog(null, "Please select record to update", "Please select", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
            Integer id = Integer.parseInt(idColumn.getCellData(selectedIndex).toString());
            String userName = nameColumn.getCellData(selectedIndex).toString();
            String divisionName = divisionColumn.getCellData(selectedIndex).toString();
            String phone = phoneColumn.getCellData(selectedIndex).toString();
            String postalCode = postalCodeColumn.getCellData(selectedIndex).toString();
            String address = addressColumn.getCellData(selectedIndex).toString();
            
            nameField.setText(userName);
            phoneField.setText(phone);
            postalCodeField.setText(postalCode);
            addressField.setText(address);
            divisionCombo.setValue(divisionName);
            
        }
        
            
        });
    }
    
    @FXML
    public void goBack(ActionEvent e){
    
        Controller.homeStage.show();
        HomeController.getStage.close();
        
    }
    
    @FXML
    public void add(){
    String customerName = nameField.getText();
    String phone = phoneField.getText();
    String postalCode = postalCodeField.getText();
    String address = addressField.getText();
    
        if (!customerName.equals("") || divisionCombo.getSelectionModel().getSelectedItem() != null || !phone.equals("") || !postalCode.equals("") || !address.equals("")) {
            String division = divisionCombo.getSelectionModel().getSelectedItem().toString();
            Integer divisionId = divisionDaoImpl.getDivisionIdByName(division);
            Division division1 = new Division("");
            division1.setDivisionId(divisionId);
            
            Customer customer = new Customer(0, "", "", "", "");
            customer.setCustomerName(customerName);
            customer.setDivision(division1);
            customer.setCustomerAddress(address);
            customer.setPhone(phone);
            customer.setPostalCode(postalCode);
            customer.setCreateDate(new Date());
            customer.setCreatedBy(Controller.loginUser.getName());
            
            int result = customerDaoImpl.addCustomer(customer);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(null, " Record added successfully");
                showCustomer();
                clear();
            }
            
        }else{
        JOptionPane.showConfirmDialog(null, "Please fill add the fields", "Fields required", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    @FXML
    public void update(){
        getSelected();
        
        if (selectedCutomer == null || selectedIndex <= -1) {
            JOptionPane.showConfirmDialog(null, "Please select a record to update", "Select Record", JOptionPane.ERROR_MESSAGE);
            
        }else{
       
            Integer customerId = (Integer) selectedCutomer.getCustomerId();
            System.out.println("Customer ID: "+selectedCutomer.getCustomerId());    
            
            Customer customer = customerDaoImpl.getCustomerById(customerId);
           
            Integer divisionId = divisionDaoImpl.getDivisionIdByName(divisionCombo.getValue().toString());
            Division d = new Division("");
            d.setDivisionId(divisionId);
                String customerName = nameField.getText();
                String phone = phoneField.getText();
                String postalCode = postalCodeField.getText();
                String address = addressField.getText();
            
            
        
            
            
            customer.setCustomerName(customerName);
            
            customer.setCustomerAddress(address);
            customer.setPhone(phone);
            customer.setPostalCode(postalCode);
            customer.setDivision(d);
            customer.setUpdateDate(new Date());
            customer.setUpdatedBy(Controller.loginUser.getName());
            
            int result = customerDaoImpl.updateCustomer(customer);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Record updated successfully.");
                showCustomer();
                clear();
            }
            
        
        }
        
        
        
        
    }
    
     @FXML
    public void delete(){
         if (selectedCutomer != null) {
             
             
             List<Appointment> allAppointments = appointmentDaoImpl.getAppointmentsOf(selectedCutomer.getCustomerId());
             
             if (allAppointments.size() > 0) {
                 JOptionPane.showMessageDialog(null, "This suctomer has some appointmetns. Please delete them first.");
             }else{
             
                 int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete this?");
             
             if (choice == 0) {
                 int result = customerDaoImpl.deleteCustomer(selectedCutomer.getCustomerId());
                 
                 if (result > 0) {
                     JOptionPane.showMessageDialog(null, "Record deleted successfully.");
                     showCustomer();
                     clear();
                 }
             }
             
             }
             
             
         }else{
         
              JOptionPane.showConfirmDialog(null, "Please select a record to update", "Select Record", JOptionPane.ERROR_MESSAGE);
         }
    }
    
     @FXML
    public void clear(){
        nameField.setText(null);
        phoneField.setText(null);
        postalCodeField.setText(null);
        addressField.setText(null);
    }

    
}
