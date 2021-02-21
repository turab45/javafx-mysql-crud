package dao;

import models.Division;

import java.util.List;
import javafx.collections.ObservableList;

public interface DivisionDao {
    public Integer addDivision(Division division);
    public Integer updateDivision(Division division);
    public Integer deleteDivision(int divisionId);
    public Division getDivisionById(int divisionId);
    public Integer getDivisionIdByName(String division);
    public ObservableList<Division> getAllDivision();
}
