package foodforfun.dao;

import foodforfun.dto.CommentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import foodforfun.db.DBConnection;

public class CommentDAO {

  Connection conn = null;
  PreparedStatement stm = null;
  ResultSet rs = null;

  public CommentDAO() {
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
  
  //Count total comt for Editor
  public int countTotalOfComt() {
    int result = 0;
    try {
      conn = DBConnection.getConnection();
      String sql = "select COUNT(CommentID) as TotalComt from Comment";
      stm = conn.prepareStatement(sql);
      rs = stm.executeQuery();
      if (rs.next()) {
        result = rs.getInt("TotalComt");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }

  //Count Total comt of Post Creater by Moderator
  public int countTotalOfComtByCreaterId(String createrId) {
    int result = 0;
    try {
      conn = DBConnection.getConnection();
      String sql = "select COUNT(CommentID) as TotalComt from Comment " +
"where PostID in (select PostID from Post where CreaterID=?)";
      stm = conn.prepareStatement(sql);
      stm.setString(1, createrId);
      rs = stm.executeQuery();
      if (rs.next()) {
        result = rs.getInt("TotalComt");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public int countTotalCmtByMember(String createrId) {
    int result = 0;
    try {
      conn = DBConnection.getConnection();
      String sql = "select COUNT(CommentID) as TotalComt from Comment where CreaterID=?";
      stm = conn.prepareStatement(sql);
      stm.setString(1, createrId);
      rs = stm.executeQuery();
      if (rs.next()) {
        result = rs.getInt("TotalComt");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public boolean deleteComment(String commentId) {
    try {
      conn = DBConnection.getConnection();
      String sql = "delete from Comment where CommentID=?";
      stm = conn.prepareStatement(sql);
      stm.setString(1, commentId);
      int n = stm.executeUpdate();
      if (n > 0) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return false;
  }

  public int AddComment(CommentDTO newComment) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Comment values (?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, newComment.getCreaterID());
            stm.setString(2, newComment.getDetail());
            stm.setTimestamp(3, newComment.getDateCreated());
            stm.setString(4, newComment.getPostID());
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

  public int UpdateComment(CommentDTO comment) {
    int result = 0;
    try {
      conn = DBConnection.getConnection();
      String sql = "update Comment set Detail=?, DateCreated=? where CommentID=?";
      stm = conn.prepareStatement(sql);
      stm.setString(1, comment.getDetail());
      stm.setTimestamp(2, comment.getDateCreated());
      stm.setString(3, comment.getCommentID());
      result = stm.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }

  public List<CommentDTO> get5RecentlyCommentForDashboard() {
    List<CommentDTO> result = new ArrayList<CommentDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "SELECT TOP 5 t2.Name, t1.Detail, t3.Title, t1.PostID "
              + "FROM Comment t1,Account t2, Post t3 "
              + "WHERE t1.CreaterID = t2.AccountID and t1.PostID = t3.PostID and t3.[State]='Active' "
              + "ORDER BY DateCreated DESC;";
      stm = conn.prepareStatement(sql);
      rs = stm.executeQuery();
      while (rs.next()) {
        CommentDTO dto = new CommentDTO();
        dto.setNameOfCreater(rs.getString("Name"));//Ten cua user da post cai comment nay
        dto.setDetail(rs.getString("Detail"));//Chi tiet cua comment
        dto.setNamePost(rs.getString("Title"));//Title cua post chua comment nay
        dto.setPostID(rs.getInt("PostID") + "");//ID cua post chua comment nay
        result.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public List<CommentDTO> get5RecentlyCommentForDashboardByAccountId(String accountId) {
    List<CommentDTO> result = new ArrayList<CommentDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "SELECT TOP 5 t2.Name, t1.Detail, t3.Title, t1.PostID " +
"       FROM Comment t1,Account t2, Post t3 " +
"       WHERE t1.CreaterID = t2.AccountID and t1.PostID = t3.PostID and t3.[State]='Active' " +
"       and t1.PostID in (select PostID from Post where CreaterID=?) " +
"       ORDER BY DateCreated DESC;";
      stm = conn.prepareStatement(sql);
      stm.setString(1, accountId);
      rs = stm.executeQuery();
      while (rs.next()) {
        CommentDTO dto = new CommentDTO();
        dto.setNameOfCreater(rs.getString("Name"));//Ten cua user da post cai comment nay
        dto.setDetail(rs.getString("Detail"));//Chi tiet cua comment
        dto.setNamePost(rs.getString("Title"));//Title cua post chua comment nay
        dto.setPostID(rs.getInt("PostID") + "");//ID cua post chua comment nay
        result.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public List<CommentDTO> get5RecentlyCommentForDashboardByMember(String accountId) {
    List<CommentDTO> result = new ArrayList<CommentDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "SELECT TOP 5 t2.Name, t1.Detail, t3.Title, t1.PostID, t1.DateCreated " +
"       FROM Comment t1,Account t2, Post t3 " +
"       WHERE t1.CreaterID = t2.AccountID and t1.PostID = t3.PostID and t1.CreaterID=? " +
"       ORDER BY DateCreated DESC;";
      stm = conn.prepareStatement(sql);
      stm.setString(1, accountId);
      rs = stm.executeQuery();
      while (rs.next()) {
        CommentDTO dto = new CommentDTO();
        dto.setNameOfCreater(rs.getString("Name"));//Ten cua user da post cai comment nay
        dto.setDetail(rs.getString("Detail"));//Chi tiet cua comment
        dto.setNamePost(rs.getString("Title"));//Title cua post chua comment nay
        dto.setPostID(rs.getInt("PostID") + "");//ID cua post chua comment nay
        result.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
}
