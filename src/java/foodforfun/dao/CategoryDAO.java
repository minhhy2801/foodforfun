package foodforfun.dao;

import foodforfun.dto.CateManageDTO;
import foodforfun.dto.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import foodforfun.db.DBConnection;
import foodforfun.dto.TagDTO;

public class CategoryDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public CategoryDAO() {
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

    public int updateCategory(int id, String name, String des) {
        int check = 0;
        String sql = "update Category set CateName=?,Description=? where CateID=?";
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

    public int addCategory(String name, String creater, String des, Timestamp date) {
        int check = 0;
        String sql = "insert into Category values (?,?,?,?)";
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

    public int getMaxIdOfCate() {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "Select top 1 CateID from Category order by CateID DESC";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getInt("CateID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<CategoryDTO> getAllCateName() {
        List<CategoryDTO> result = new ArrayList<CategoryDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "Select CateID,CateName from Category";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(rs.getString("CateID"));
                dto.setName(rs.getString("CateName"));
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

    public List<CategoryDTO> getAllCate(int page) {
        List<CategoryDTO> result = new ArrayList<CategoryDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "Select t1.CateID, t1.CateName, t1.[Description], t2.[Name] "
                    + "	from Category t1, Account t2 where t1.CreaterID = t2.AccountID "
                    + "	ORDER BY t1.CateID ASC "
                    + "       OFFSET (?-1)*5 rows "
                    + "       FETCH NEXT 5 ROWS only";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(rs.getString("CateID"));
                dto.setName(rs.getString("CateName"));
                dto.setCreaterName(rs.getString("Name"));
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

    public List<CategoryDTO> getAllCategoryAndCount(int page) {
        List<CategoryDTO> result = new ArrayList<CategoryDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "	Select t1.CateID, t2.CateName, t2.[Description], t3.[Name], t1.Count "
                    + "       from (select CateID, count(CateID) as [Count] from CateManage group by CateID) t1, Category t2, Account t3 "
                    + "       where t1.CateID = t2.CateID and t2.CreaterID = t3.AccountID "
                    + "	ORDER BY t1.CateID ASC "
                    + "       OFFSET (?-1)*5 rows "
                    + "       FETCH NEXT 5 ROWS only";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(rs.getString("CateID"));
                dto.setName(rs.getString("CateName"));
                dto.setDes(rs.getString("Description"));
                dto.setCreaterName(rs.getString("Name"));
                dto.setCount(rs.getInt("Count"));

                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public boolean deleteCategory(int categoryId) {
        try {
            conn = DBConnection.getConnection();
            String sql = "delete CateManage where CateID=?; delete from Category where CateID=?;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, categoryId);
            stm.setInt(2, categoryId);
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

    public boolean deleteCateByCreaterID(String createrID) {
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

    public boolean deleteCategoryManage(int postID) {
        try {
            conn = DBConnection.getConnection();
            String sql = "delete CateManage where PostID=?";
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

    public int AddNewCateroriesManage(int cateID, int postID) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into CateManage(CateID,PostID) values (?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cateID);
            stm.setInt(2, postID);
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int addCate(String name, String creater, Timestamp date) {
        int check = 0;
        String sql = "insert into Category(CateName,CreaterID,CreateDate) values (?,?,?)";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, creater);
            stm.setTimestamp(3, date);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return check;
    }

    public List<CategoryDTO> getTopCategoryUsed() {
        List<CategoryDTO> result = new ArrayList<CategoryDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select top 5 t1.CateID, t1.CateName, count(t2.PostID) as CateNum " +
"from Category t1, CateManage t2, Post t3 " +
"where t1.CateID=t2.CateID and t2.PostID=t3.PostID and t3.State='Active' " +
"group by t1.CateID, t1.CateName " +
"order by CateNum desc";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(rs.getInt("CateID") + "");
                dto.setName(rs.getString("CateName"));
                dto.setCount(rs.getInt("CateNum"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<CategoryDTO> getCateOfPost(int postID) {
        List<CategoryDTO> result = new ArrayList<CategoryDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select t2.CateID, t2.CateName from CateManage t1, Category t2 where PostID=? and t1.CateID = t2.CateID";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, postID);
            rs = stm.executeQuery();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(rs.getInt("CateID") + "");
                dto.setName(rs.getString("CateName"));
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
