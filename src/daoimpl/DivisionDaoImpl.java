package daoimpl;

import dao.DivisionDao;
import database.Database;
import javafx.beans.property.SimpleIntegerProperty;
import models.Division;
import models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DivisionDaoImpl implements DivisionDao {

    Connection conn = Database.getConnection();


    @Override
    public Integer addDivision(Division division) {
        Integer row = null;
        try {
            String query = "INSERT INTO first_level_divisions(Division, COUNTRY_ID, Create_Date, Created_By) VALUES(?,?,?,?)";

            Date createdDate = new Date(division.getCreateDate().getTime());

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, division.getDivisionName());
            pstmt.setInt(2, division.getCountry().getCountryId());
            pstmt.setDate(3, createdDate);
//            pstmt.setInt(4, division.getCreatedBy().getUserId().get());


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateDivision(Division division) {
        Integer row = null;
        try {
            String query = "UPDATE first_level_divisions SET Division=?, COUNTRY_ID=?, Last_Update, Last_Updated_By WHERE Division_ID=?";

            Date updateDate = new Date(division.getUpdatedDate().getTime());

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, division.getDivisionName());
            pstmt.setInt(2, division.getCountry().getCountryId());
            pstmt.setDate(3, updateDate);
            //pstmt.setInt(4, d);
            pstmt.setInt(5, division.getDivisionId());


            row = pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteDivision(int divisionId) {
//        Integer row = null;
//        try {
//            String query = "DELETE FROM first_level_divisions WHERE Division_ID=?";
//
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//
//            pstmt.setInt(5, divisionId);
//
//
//            row = pstmt.executeUpdate();
//
//        } catch (Exception ex) {
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//        return row;
return null;
    }

    @Override
    public Division getDivisionById(int divisionId) {
        Division division = null;
        ResultSet rs = null;

        try {

            String query = "SELECT * FROM first_level_divisions WHERE Division_ID=?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, divisionId);
            rs = preparedStatement.executeQuery();

            if (rs.next()){
                division = new Division("");

                division.setDivisionId(rs.getInt("Division_ID"));
                division.setDivisionName(rs.getString("Division"));
                division.setCreateDate(rs.getDate("Create_Date"));
                division.setCreatedBy(rs.getString("Created_By"));
                division.setUpdatedDate(rs.getDate("Last_Update"));
                division.setUpdatedBy(rs.getString("Last_Updated_By"));
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return division;
    }

    @Override
    public Integer getDivisionIdByName(String division) {
        Integer row = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            String query = "SELECT Division_ID FROM first_level_divisions WHERE Division=?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, division);

            rs = pstmt.executeQuery();

            if (rs.next())
                row = rs.getInt("Division_ID");

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return row;
    }

    @Override
    public ObservableList<Division> getAllDivision() {
        ObservableList<Division> allDivision = FXCollections.observableArrayList();
        ResultSet rs = null;

        try {

            String query = "SELECT * FROM first_level_divisions";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                Division division = new Division("");

             
                division.setDivisionId(rs.getInt("Division_ID"));
                division.setDivisionName(rs.getString("Division"));
                division.setCreateDate(rs.getDate("Create_Date"));
                division.setCreatedBy(rs.getString("Created_By"));
                division.setUpdatedDate(rs.getDate("Last_Update"));
                division.setUpdatedBy(rs.getString("Last_Updated_By"));

                allDivision.add(division);
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }

        return allDivision;
    }
}
