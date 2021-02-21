package views;


import daoimpl.UserDaoImpl;
import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.User;
import views.Crud;

public class Controller {
    @FXML
    TextField userNameField;
    @FXML
    PasswordField passwordField;


    UserDao userDaoImpl = new UserDaoImpl();
    User user = null;

    static Stage homeStage = null;
    static User loginUser = null;

    @FXML
    public void login(ActionEvent e) throws Exception{
        String userName = userNameField.getText();
        String password = passwordField.getText();
        Integer userId = userDaoImpl.getUserIdByName(userName);

        if (!userName.equals("") || !password.equals("")) {
        if (userId != null){
            user = userDaoImpl.getUserById(userId);
            loginUser = user;
            if (userNameField.getText().equalsIgnoreCase(user.nameProperty().get()) && passwordField.getText().equals(user.passwordProperty().get())){
                Stage stage = new Stage();
                AnchorPane pane = FXMLLoader.load(getClass().getResource("home.fxml"));
                Scene scene = new Scene(pane,994,713);
                stage.setScene(scene);
                homeStage = stage;
                stage.show();
                Crud.loginStage.close();
                
            }else {
                JOptionPane.showMessageDialog(null, "Please enter correct password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
                JOptionPane.showMessageDialog(null, "Credentials does not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }else{
    JOptionPane.showMessageDialog(null, "Please enter username & password", "Error", JOptionPane.ERROR_MESSAGE);
}
    }
}



