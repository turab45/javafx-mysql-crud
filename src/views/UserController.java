package views;


import daoimpl.UserDaoImpl;
import dao.UserDao;
import java.awt.event.MouseEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UserController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> userColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableView<User> tableView;

    UserDao userDaoImpl = new UserDaoImpl();
    
    Integer selectedIndex = null;
    User selectedUser = null;

    @FXML
    public void add(javafx.event.ActionEvent event) {
        String userName = nameField.getText();
        String password = passwordField.getText();

        if (!userName.equals("") && !password.equals("")){
            User user = new User(0, "", "");
            user.setName(userName);
            user.setPassword(password);

            int result = userDaoImpl.addUser(user);

            if (result > 0)
                JOptionPane.showMessageDialog(null, "User added successfully!", "Successfully Added", JOptionPane.INFORMATION_MESSAGE);
                showUser();

        }else{
            JOptionPane.showMessageDialog(null, "Please fill all the fields", "Fields can not be empty", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
 
    @FXML
    public void getSelected(){
        tableView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent t){
                selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                selectedUser = tableView.getSelectionModel().getSelectedItem();
        
        
        if (selectedIndex <= -1) {
            JOptionPane.showConfirmDialog(null, "Please select record to update", "Please select", JOptionPane.INFORMATION_MESSAGE);
       
        }
            String userName = userColumn.getCellData(selectedIndex).toString();
            String password = passwordColumn.getCellData(selectedIndex).toString();
            nameField.setText(userName);
            passwordField.setText(password);
            }
        
            
        });
    }
    
    
    

    @FXML
    public void update(javafx.event.ActionEvent event) {
        getSelected();
        
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(null, "Please select a record to update.");
        }else{
            
            User user = userDaoImpl.getUserById(selectedUser.getId());
        
            user.setName(nameField.getText());
            user.setPassword(passwordField.getText());
            
            int result = userDaoImpl.updateUser(user);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully.");
                showUser();
                clear(event);
            }
            
        }
        
    }

    @FXML
    public void delete(javafx.event.ActionEvent event) {
        getSelected();
        
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(null, "Please select a record to delete.");
        }else{
            
            
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete this user?");
            
            if (choice == 0) {
                int result = userDaoImpl.deleteUser(selectedUser.getId());
            
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "User deleted successfully.");
                showUser();
                clear(event);
            }
            }
            
        }
    }

    @FXML
    public void clear(javafx.event.ActionEvent event) {
        nameField.setText("");
        passwordField.setText("");
    }


    public void showUser() {
        ObservableList<User> list = userDaoImpl.getAllUser();

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        userColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        tableView.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showUser();
        getSelected();
    }
    
    @FXML
    public void goBack(ActionEvent e){
    
        Controller.homeStage.show();
        HomeController.getStage.close();
        
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
    }
}
