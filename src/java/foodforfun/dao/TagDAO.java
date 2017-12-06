package foodforfun.dao;

import foodforfun.dto.TagDTO;
import foodforfun.dto.TagManageDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import foodforfun.db.DBConnection;

public class TagDAO {

  Connection conn = null;
  PreparedStatement stm = null;
  ResultSet rs = null;

  public TagDAO() {
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

  public int updateTag(int id, String name, String des) {
    int check = 0;
    String sql = "update Tag set TagName=?,Description=? where TagID=?";
    try {
      conn = DBConnection.getConnection();
      stm = conn.prepareStatement(sql);
      stm.setString(1, name);
      stm.setString(2, des);
      stm.setInt(3, id);
      check = stm.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return check;
  }

  public int addTag(String name, String creater, Timestamp date, String des) {
    int check = 0;
    String sql = "insert into Tag(TagName,CreaterID,CreateDate,Description) values (?,?,?,?)";
    try {
      conn = DBConnection.getConnection();
      stm = conn.prepareStatement(sql);
      stm.setString(1, name);
      stm.setString(2, creater);
      stm.setTimestamp(3, date);
      stm.setString(4, des);
      check = stm.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return check;
  }

  public boolean deleteTag(int tagId) {
    try {
      conn = DBConnection.getConnection();
      String sql = "delete TagManage where TagID=?; delete from Tag where TagID=?";
      stm = conn.prepareStatement(sql);
      stm.setInt(1, tagId);
      stm.setInt(2, tagId);
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
  
  public boolean deleteTagByCreaterID(String createrID) {
    try {
      conn = DBConnection.getConnection();
      String sql = "delete TagManage where TagID in (select TagID from Tag where CreaterID=?)";
      stm = conn.prepareStatement(sql);
      stm.setString(1, createrID);
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

  //của thành viên
  public int AddNewTagManage(int tagID, int postID) {
    int result = 0;
    try {
      conn = DBConnection.getConnection();
      String sql = "insert into TagManage(TagID,PostID) values (?,?)";
      stm = conn.prepareStatement(sql);
      stm.setInt(1, tagID);
      stm.setInt(2, postID);
      result = stm.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public List<TagDTO> getAllTagName() {
    List<TagDTO> result = new ArrayList<TagDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "Select TagID,TagName from Tag";
      stm = conn.prepareStatement(sql);
      rs = stm.executeQuery();
      while (rs.next()) {
        TagDTO dto = new TagDTO();
        dto.setId(rs.getString("TagID"));
        dto.setName(rs.getString("TagName"));
        dto.setChecked(false);
        result.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }

public List<TagDTO> getAllTag(int page) {
    List<TagDTO> result = new ArrayList<TagDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "Select t1.TagID, t1.TagName, t1.[Description], t2.[Name] " +
"	from Tag t1, Account t2 where t1.CreaterID = t2.AccountID " +
"	ORDER BY t1.TagID ASC " +
"       OFFSET (?-1)*5 rows " +
"       FETCH NEXT 5 ROWS only";
      stm = conn.prepareStatement(sql);
      stm.setInt(1, page);
      rs = stm.executeQuery();
      while (rs.next()) {
        TagDTO dto = new TagDTO();
        dto.setId(rs.getString("TagID"));
        dto.setName(rs.getString("TagName"));
        dto.setCreaterID(rs.getString("Name"));
        dto.setDes(rs.getString("Description"));
        dto.setChecked(false);
        result.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public List<TagDTO> getAllTagAndCount(int page) {
    List<TagDTO> result = new ArrayList<TagDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "Select t1.TagID, t2.TagName, t2.[Description], t3.[Name], t1.Count " +
"       from (select TagID, count(TagID) as [Count] from TagManage group by TagID) t1, Tag t2, Account t3 " +
"       where t1.TagID = t2.TagID and t2.CreaterID = t3.AccountID " +
"	ORDER BY t1.TagID ASC " +
"       OFFSET (?-1)*5 rows " +
"       FETCH NEXT 5 ROWS only";
      stm = conn.prepareStatement(sql);
      stm.setInt(1, page);
      rs = stm.executeQuery();
      while (rs.next()) {
        TagDTO dto = new TagDTO();
        dto.setId(rs.getString("TagID"));
        dto.setName(rs.getString("TagName"));
        dto.setDes(rs.getString("Description"));
        dto.setCreaterID(rs.getString("Name"));
        dto.setCount(rs.getInt("Count"));//Number of post use this tag
        result.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConn();
    }
    return result;
  }
  
  public int getMaxIdOfTag() {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "Select top 1 TagID from Tag order by TagID DESC";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getInt("TagID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }
  
  public List<TagDTO> getTopTagUsed() {
        List<TagDTO> result = new ArrayList<TagDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select top 5 t1.TagID, t1.TagName, count(t2.PostID) as TagNum " +
"from Tag t1, TagManage t2, Post t3 " +
"where t1.TagID=t2.TagID and t2.PostID=t3.PostID and t3.State='Active' " +
"group by t1.TagID, t1.TagName " +
"order by TagNum desc";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                TagDTO dto = new TagDTO();
                dto.setId(rs.getInt("TagID") + "");
                dto.setName(rs.getString("TagName"));
                dto.setCount(rs.getInt("TagNum"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }
  
  public List<TagDTO> getTagOfPost(int postID) {
      List<TagDTO> result = new ArrayList<TagDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select t2.TagID, t2.TagName from TagManage t1, Tag t2 where PostID=? and t1.TagID = t2.TagID";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, postID);
            rs = stm.executeQuery();
            while (rs.next()) {
                TagDTO dto = new TagDTO();
                dto.setId(rs.getInt("TagID") + "");
                dto.setName(rs.getString("TagName"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
  }
  
  public boolean deleteTagManage(int postID) {
        try {
            conn = DBConnection.getConnection();
            String sql = "delete TagManage where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, postID);
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
  
  public List<TagDTO> getTagByPost(String postID) {
    List<TagDTO> result = new ArrayList<TagDTO>();
    try {
      conn = DBConnection.getConnection();
      String sql = "select t.TagID, t.TagName from TagManage tm, Tag t where PostID=? and t.TagID=tm.TagID";
      stm = conn.prepareStatement(sql);
      stm.setString(1, postID);
      rs = stm.executeQuery();
      while (rs.next()) {
        TagDTO dto = new TagDTO();
        dto.setId(rs.getString("TagID"));
        dto.setName(rs.getString("TagName"));
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