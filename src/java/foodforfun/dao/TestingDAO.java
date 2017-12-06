/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.dao;

import foodforfun.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class TestingDAO {
    
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public TestingDAO() {
    }

    private void closeConn() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getPostDetails(String title) {
        String result = "fail";
        try {
            conn = DBConnection.getConnection();
            String sql = "Select Detail from Post where Title=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, title);
            rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getString("Detail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }
}
