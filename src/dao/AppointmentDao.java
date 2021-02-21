package dao;

import models.Appointment;

import java.util.List;
import javafx.collections.ObservableList;

public interface AppointmentDao {
    public Integer addAppointment(Appointment cppointment);
    public Integer updateAppointment(Appointment cppointment);
    public Integer deleteAppointment(int cppointmentId);
    public Appointment getAppointmentById(int cppointmentId);
    public Integer getAppointmentIdByName(String cppointment);
    public ObservableList<Appointment> getAllAppointment();
    public List<Appointment> getAppointmentsOf(int userId);
}
