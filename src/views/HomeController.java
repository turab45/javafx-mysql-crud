package views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController {

    static Stage getStage = null;
    public void user(javafx.event.ActionEvent event) {
        try {
            Stage stage = new Stage();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("user.fxml"));
            Scene scene = new Scene(pane,994,713);
            stage.setScene(scene);
            getStage = stage;
            stage.show();
            Controller.homeStage.close();
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void customer(javafx.event.ActionEvent event) {
        try {
            Stage stage = new Stage();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("customer.fxml"));
            Scene scene = new Scene(pane,994,713);
            stage.setScene(scene);
            getStage = stage;
            stage.show();
            Controller.homeStage.close();
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void appointment(javafx.event.ActionEvent event) {
        try {
            Stage stage = new Stage();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("appointment.fxml"));
            Scene scene = new Scene(pane,994,713);
            stage.setScene(scene);
            getStage = stage;
            stage.show();
            Controller.homeStage.close();
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    
}
