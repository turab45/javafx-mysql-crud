package dao;

import models.Contact;

import java.util.List;
import javafx.collections.ObservableList;

public interface ContactDao {
    public Integer addContact(Contact contact);
    public Integer updateContact(Contact contact);
    public Integer deleteContact(int contactId);
    public Contact getContactById(int contactId);
    public Integer getContactIdByName(String contact);
    public ObservableList<Contact> getAllContact();
}
