//package daoimpl;
//
//import dao.CountryDao;
//import database.Database;
//import models.Country;
//import models.User;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//public class CountryDaoImpl implements CountryDao {
//
//    Connection conn = Database.getConnection();
//
//    @Override
//    public Integer addCountry(Country country) {
//        Integer row = null;
//
//        try {
//            String query = "INSERT INTO countries(Country, Create_Date, Created_By) VALUES(?,?,?)";
//
//            Date createDate = new Date(country.getCreateDate().getTime());
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, country.getCountryName());
//            pstmt.setDate(2, createDate);
//            pstmt.setInt(3, country.getCreatedBy().getUserId());
//
//            row = pstmt.executeUpdate();
//
//        }catch (Exception ex){
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return row;
//    }
//
//    @Override
//    public Integer updateCountry(Country country) {
//        Integer row = null;
//
//        try {
//            String query = "UPDATE countries SET Country=?, Last_Update=?, Last_Updated_By WHERE Country_ID=?";
//
//            Date UpdateDate = new Date(country.getUpdatedDate().getTime());
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, country.getCountryName());
//            pstmt.setDate(2, UpdateDate);
//            pstmt.setInt(3, country.getCreatedBy().getUserId());
//            pstmt.setInt(4, country.getCountryId());
//
//            row = pstmt.executeUpdate();
//
//        }catch (Exception ex){
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return row;
//    }
//
//    @Override
//    public Integer deleteCountry(int countryId) {
//        Integer row = null;
//
//        try {
//            String query = "DELETE FROM countries WHERE Country_ID=?";
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//            pstmt.setInt(3, countryId);
//
//            row = pstmt.executeUpdate();
//
//        }catch (Exception ex){
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return row;
//    }
//
//    @Override
//    public Country getCountryById(int countryId) {
//        Country country = null;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT * FROM countries WHERE Country_ID=?";
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//            pstmt.setInt(1, countryId);
//
//
//            rs = pstmt.executeQuery();
//
//            if (rs.next()){
//                country = new Country();
//
//                User createdBy = new User();
//                createdBy.setUserId(rs.getInt("Created_By"));
//
//                User updatedBy = new User();
//                updatedBy.setUserId(rs.getInt("Last_Updated_By"));
//
//                country.setCountryId(rs.getInt("Country_ID"));
//                country.setCountryName(rs.getString("Country"));
//                country.setCreateDate(rs.getDate("Create_Date"));
//                country.setCreatedBy(createdBy);
//                country.setUpdatedBy(updatedBy);
//                country.setUpdatedDate(rs.getDate("Last_Updated_By"));
//            }
//
//        }catch (Exception ex){
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return country;
//    }
//
//    @Override
//    public Integer getCountrytIdByName(String country) {
//        Integer id = null;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT Country_ID FROM countries WHERE Country=?";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, country);
//
//            rs = pstmt.executeQuery();
//
//            if (rs.next()){
//                id = rs.getInt("Country_ID");
//            }
//
//        }catch (Exception ex){
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return id;
//    }
//
//    @Override
//    public List<Country> getAllCountry() {
//        List<Country> allCountry = null;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT * FROM countries";
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//
//            rs = pstmt.executeQuery();
//
//            while (rs.next()){
//                Country country = new Country();
//
//                User createdBy = new User();
//                createdBy.setUserId(rs.getInt("Created_By"));
//
//                User updatedBy = new User();
//                updatedBy.setUserId(rs.getInt("Last_Updated_By"));
//
//                country.setCountryId(rs.getInt("Country_ID"));
//                country.setCountryName(rs.getString("Country"));
//                country.setCreateDate(rs.getDate("Create_Date"));
//                country.setCreatedBy(createdBy);
//                country.setUpdatedBy(updatedBy);
//                country.setUpdatedDate(rs.getDate("Last_Updated_By"));
//
//                allCountry.add(country);
//            }
//
//        }catch (Exception ex){
//            System.out.println("ERROR: "+ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return allCountry;
//    }
//}
