package dao;

import javafx.collections.ObservableList;
import models.User;

import java.util.List;

public interface UserDao {
    public Integer addUser(User user);
    public Integer updateUser(User user);
    public Integer deleteUser(int userId);
    public User getUserById(int userId);
    public Integer getUserIdByName(String userName);
    ObservableList<User> getAllUser();
}
