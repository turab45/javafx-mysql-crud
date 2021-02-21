package daoimpl;

import dao.UserDao;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    Connection conn = Database.getConnection();

    @Override
    public Integer addUser(User user) {
        Integer row = null;
        try {
            String query = "INSERT INTO users(User_Name, Password) VALUES(?,?)";

           

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.nameProperty().get());
            pstmt.setString(2, user.passwordProperty().get());
           


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateUser(User user) {
        Integer row = null;
        try {
            String query = "UPDATE  users SET User_Name=?, Password=? WHERE User_ID = ?";

            

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.passwordProperty().get());
            pstmt.setInt(3, user.idProperty().get());


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
       
    }

    @Override
    public Integer deleteUser(int userId) {
        Integer row = null;
        try {
            String query = "DELETE FROM users WHERE User_ID=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);

            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM users WHERE User_ID=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);

            rs = pstmt.executeQuery();

            if(rs.next()){
                user  = new User(0, "", "");

                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));

            }

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer getUserIdByName(String userName) {
        Integer id = null;
        ResultSet rs = null;
        try {

            String query = "SELECT * FROM users WHERE User_Name=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);

            rs = pstmt.executeQuery();
            if(rs.next()){
                id = rs.getInt("User_ID");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public ObservableList<User> getAllUser() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM users";
            PreparedStatement pstmt = conn.prepareStatement(query);


            rs = pstmt.executeQuery();

            while(rs.next()){
                User user = new User(0, null, null);

                user  = new User(0, null, null);

                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
//                user.setCreateDate(rs.getDate("Create_Date"));
//                user.setCreatedBy(rs.getString("Created_By"));
//                user.setUpdateDate(rs.getDate("Last_Update"));
//                user.setUpdatedBy(rs.getString("Last_Updated_By"));

                allUsers.add(user);
            }

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allUsers;
    }
}
