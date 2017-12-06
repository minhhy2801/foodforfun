package foodforfun.dao;

import foodforfun.dto.AccountDTO;
import foodforfun.dto.AllPostDTO;
import foodforfun.dto.CateManageDTO;
import foodforfun.dto.CategoryDTO;
import foodforfun.dto.CommentDTO;
import foodforfun.dto.ImageDTO;
import foodforfun.dto.PlaceDTO;
import foodforfun.dto.PostDTO;
import foodforfun.dto.PostModifyDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import foodforfun.db.DBConnection;

public class PostDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public PostDAO() {
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

    //Của trưởng nhóm
    public List<AllPostDTO> viewAllPost_AdminPage(int page) {
        List<AllPostDTO> result = new ArrayList<>();
        try {
            List<PostDTO> post = showAllPost(page);
            for (PostDTO postDTO : post) {
                AllPostDTO allpost = new AllPostDTO();
                //title
                allpost.setTitle(postDTO.getTitle());
                //status
                allpost.setStatus(postDTO.getState());
                //date
                allpost.setDateAccepted(postDTO.getDateAccepted());
                //PostId
                allpost.setPostId(postDTO.getId());
                //PostModifyDTO postMod = new PostModifyDTO();
                AccountDTO account = new AccountDTO();
                account = getAccountByID(postDTO.getCreaterID());
                //postMod = getModifyByPostID(postDTO.getId());
                //postModDTO.getModifierID() [1]
                //account = getAccountByID(postMod.getModifierID());

                //editor
                //allpost.setEditor(account.getName());
                //author
                allpost.setAuthor(account.getName());
                ///list
                //HA noi, ho chi minh, hai phong, vt
                String allCategory = " ";
                List<CateManageDTO> cateManage = new ArrayList<>();
                cateManage = getListCateManageByPostID(postDTO.getId());
                for (CateManageDTO cateManageDTO : cateManage) {
                    CategoryDTO cate = new CategoryDTO();
                    cate = getCategoryByID(cateManageDTO.getCateID());
                    allCategory = allCategory + " | " + cate.getName();//
                }
                //category
                allpost.setCategories(allCategory);
                int totalOfComt = countComment(postDTO.getId());
                allpost.setComments(totalOfComt);
                result.add(allpost);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            closeConn();
        }
        return result;
    }
    //Xem chi tiết 1 bài post truyền ID

    public PostDTO ViewDetailPost_ArticleDetail(String id) {
        PostDTO dto = null;
        String sql = "select PostID, Title, DateAccepted, Detail, a.Name, a.AccountID, p.ImageMain, p.State  from Post p, Account a where PostID=? and p.CreaterID=a.AccountID";
        String title = "", detail = "", aName = "", aID = "", imageMain = "", state = "";
        Timestamp dateAccepted = null;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                title = rs.getString("Title");
                dateAccepted = rs.getTimestamp("DateAccepted");
                detail = rs.getString("Detail");
                aName = rs.getString("Name");
                aID = rs.getString("AccountID");
                imageMain = rs.getString("ImageMain");
                state = rs.getString("State");
                dto = new PostDTO(id, title, dateAccepted, aID, detail, imageMain, state, aName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return dto;
    }

    public List<AllPostDTO> viewAcceptedPost_AdminPage(int page) {
        List<AllPostDTO> result = new ArrayList<>();
        try {
            List<PostModifyDTO> post = showAllPostModify(page);
            for (PostModifyDTO modifyDTO : post) {
                AllPostDTO allpost = new AllPostDTO();
                //title
                PostDTO postDTO = getPostByPostID(modifyDTO.getPostID());
                allpost.setTitle(postDTO.getTitle());
                //status
                allpost.setStatus(postDTO.getState());
                //date
                allpost.setDateAccepted(postDTO.getDateAccepted());
                allpost.setPostId(postDTO.getId());

//        PostModifyDTO postMod = new PostModifyDTO();
                AccountDTO account = new AccountDTO();
                modifyDTO = getModifyByPostID(Integer.parseInt(postDTO.getId()));
                //postModDTO.getModifierID() [1]
                account = getAccountByID(modifyDTO.getModifierID());
                //editor
                allpost.setEditor(account.getName());
                account = getAccountByID(postDTO.getCreaterID());
                //author
                allpost.setAuthor(account.getName());
                ///list
                //HA noi, ho chi minh, hai phong, vt
                String allCategory = " ";
                List<CateManageDTO> cateManage = new ArrayList<>();
                cateManage = getListCateManageByPostID(postDTO.getId());
                for (CateManageDTO cateManageDTO : cateManage) {
                    CategoryDTO cate = new CategoryDTO();
                    cate = getCategoryByID(cateManageDTO.getCateID());
                    allCategory = allCategory + " | " + cate.getName();//
                }
                //category
                allpost.setCategories(allCategory);
                int totalOfComt = countComment(postDTO.getId());
                allpost.setComments(totalOfComt);
                result.add(allpost);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            closeConn();
        }
        return result;
    }
//hiện hết list post

    public List<AllPostDTO> viewPendingPost_AdminPade(int page) {
        List<AllPostDTO> result = new ArrayList<>();
        try {
            String status = "pending";
            List<PostDTO> post = findByStatusPost(status, page);
            for (PostDTO postDTO : post) {
                AllPostDTO allpost = new AllPostDTO();
                //title
                allpost.setTitle(postDTO.getTitle());
                //status
                allpost.setStatus(postDTO.getState());
                allpost.setPostId(postDTO.getId());
                //date
                allpost.setDateAccepted(postDTO.getDateAccepted());
                AccountDTO account = new AccountDTO();
                account = getAccountByID(postDTO.getCreaterID());
                //author
                allpost.setAuthor(account.getName());
                ///list
                String allCategory = " ";
                List<CateManageDTO> cateManage = new ArrayList<>();
                cateManage = getListCateManageByPostID(postDTO.getId());
                for (CateManageDTO cateManageDTO : cateManage) {
                    CategoryDTO cate = new CategoryDTO();
                    cate = getCategoryByID(cateManageDTO.getCateID());
                    allCategory = allCategory + " | " + cate.getName();//
                }
                //category
                allpost.setCategories(allCategory);
                int totalOfComt = countComment(postDTO.getId());
                allpost.setComments(totalOfComt);
                result.add(allpost);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            closeConn();
        }
        return result;
    }

    //Lấy account truyền ID
    public AccountDTO getAccountByID(String id) {
        AccountDTO dto = null;
        String sql = "Select AccountID, Role, Name,"
                + "Sex, Email, ExpYears,"
                + "Phone, Address, Birthday,WorkedPlace from Account "
                + "where AccountID = ?";
        String aId = "", role = "", name = "", email = "", phone = "", address = "",
                place = "";
        int exp = 0;
        boolean sex;
        Date yob = null;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                aId = rs.getString("AccountID");
                role = rs.getString("Role");
                name = rs.getString("Name");
                sex = rs.getBoolean("Sex");
                email = rs.getString("Email");
                exp = rs.getInt("ExpYears");
                phone = rs.getString("Phone");
                address = rs.getString("Address");
                yob = rs.getDate("Birthday");
                place = rs.getString("WorkedPlace");
                dto = new AccountDTO(aId, role, name, sex, email, phone, address, yob, exp, place);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return dto;
    }

    //hiện list các sếp (editor, modetrator)
    public List<AccountDTO> showAllUser_AdminPage(int page) {
        List<AccountDTO> result = new ArrayList<>();
        String sql = "Select AccountID, [Role], [Name] "
                + "       from Account where [Role]='Editor' or [Role]='Moderator' or [Role]='Banner' "
                + "	ORDER BY AccountID DESC "
                + "	OFFSET (?-1)*5 rows "
                + "       FETCH NEXT 5 ROWS only";
        String aId = "", role = "", name = "";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                AccountDTO dto = new AccountDTO();
                aId = rs.getString("AccountID");
                role = rs.getString("Role");
                name = rs.getString("Name");
                dto = new AccountDTO(aId, role, name);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

//hiện list bài post theo trạng thái
    public List<PostDTO> findByStatusPost(String status, int page) {
        List<PostDTO> result = new ArrayList<>();
        String sql = "Select PostID, Title, DateAccepted, CreaterID, Detail, ImageMain, [State], PlaceID "
                + "	from Post where [State]=? "
                + "	ORDER BY DateAccepted DESC "
                + "	OFFSET (?-1)*5 rows "
                + "       FETCH NEXT 5 ROWS only";
        String id = "", title = "", creater = "", detail = "", img = "", state = "";
        int place = 0;
        Timestamp dateAccepted = null;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                id = rs.getString("PostID");
                title = rs.getString("Title");
                creater = rs.getString("CreaterID");
                detail = rs.getString("Detail");
                img = rs.getString("ImageMain");
                state = rs.getString("State");
                place = rs.getInt("PlaceID");
                dateAccepted = rs.getTimestamp("DateAccepted");
                dto.setCreaterID(creater);
                dto.setDateAccepted(dateAccepted);
                dto.setDetail(detail);
                dto.setId(id);
                dto.setImgMain(img);
                dto.setPlaceID(place);
                dto.setState(state);
                dto.setTitle(title);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    //Hàm phụ thôi
    public CategoryDTO getCategoryByID(String id) {
        CategoryDTO dto = null;
        String sql = "select CateID, CateName, CreaterID, CreateDate from"
                + " Category where CateID=?";
        String cID = "", name = "", creater = "";
        Timestamp date = null;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                cID = rs.getString("CateID");
                name = rs.getString("CateName");
                creater = rs.getString("CreaterID");
                date = rs.getTimestamp("CreateDate");
                dto = new CategoryDTO(cID, name, creater, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return dto;
    }

    public List<PostModifyDTO> showAllPostModify(int page) {
        List<PostModifyDTO> result = new ArrayList<>();
        String sql = "select ModifyID, PostID, ModifyTime, ModifierID,[State],PostContent "
                + "	from PostModify "
                + "	ORDER BY ModifyTime DESC "
                + "	OFFSET (?-1)*5 rows "
                + "       FETCH NEXT 5 ROWS only";
        int modifyId = 0, postID = 0;
        Timestamp time = null;
        String editor = "", state = "", content = "";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostModifyDTO dto = new PostModifyDTO();
                modifyId = rs.getInt("ModifyID");
                postID = rs.getInt("PostID");
                time = rs.getTimestamp("ModifyTime");
                editor = rs.getString("ModifierID");
                state = rs.getString("State");
                content = rs.getString("PostContent");
                dto.setModifierID(editor);
                dto.setModifyID(modifyId);
                dto.setModifyTime(time);
                dto.setPostContent(content);
                dto.setPostID(postID);
                dto.setState(state);
                dto = new PostModifyDTO(modifyId, postID, editor, state, content, time);
                result.add(dto);
            }
        } catch (Exception e) {

        } finally {
            closeConn();
        }
        return result;
    }

    public List<PostDTO> showAllPost(int page) {
        List<PostDTO> result = new ArrayList<>();
        String sql = "Select PostID, Title, DateAccepted, CreaterID, Detail, ImageMain,"
                + "            State, PlaceID from Post"
                + "			ORDER BY DateAccepted DESC"
                + "			OFFSET (?-1)*5 rows "
                + "                   FETCH NEXT 5 ROWS only";
        String id = "", title = "", creater = "", detail = "", img = "", state = "";
        int place = 0;
        Timestamp dateAccepted = null;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, page);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                id = rs.getString("PostID");
                title = rs.getString("Title");
                creater = rs.getString("CreaterID");
                detail = rs.getString("Detail");
                img = rs.getString("ImageMain");
                state = rs.getString("State");
                place = rs.getInt("PlaceID");
                dateAccepted = rs.getTimestamp("DateAccepted");
                dto.setCreaterID(creater);
                dto.setDateAccepted(dateAccepted);
                dto.setDetail(detail);
                dto.setId(id);
                dto.setImgMain(img);
                dto.setPlaceID(place);
                dto.setState(state);
                dto.setTitle(title);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<CateManageDTO> getListCateManageByPostID(String id) {
        List<CateManageDTO> result = new ArrayList<>();
        String sql = "select CateID, CateManageID, PostID from CateManage "
                + "where PostID =?";
        String cID = "", cmID = "", pID = "";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                CateManageDTO dto = new CateManageDTO();
                cID = rs.getString("CateID");
                cmID = rs.getString("CateManageID");
                pID = rs.getString("PostID");
                dto.setCateID(cID);
                dto.setCateManageID(cmID);
                dto.setPostID(pID);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public PostDTO getPostByPostID(int id) {
        PostDTO dto = new PostDTO();
        String sql = "select PostID, Title, DateAccepted, Detail, ImageMain, CreaterID, DateAccepted, State, PlaceID from Post where PostID=?";
        String pID = "", title = "", author = "", state = "", img = "", detail = "", placeID = "";
        Timestamp accept = null;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                pID = rs.getString("PostID");
                title = rs.getString("Title");
                img = rs.getString("ImageMain");
                accept = rs.getTimestamp("DateAccepted");
                author = rs.getString("CreaterID");
                state = rs.getString("State");
                detail = rs.getString("Detail");
                placeID = rs.getString("PlaceID");
                dto = new PostDTO(pID, title, accept, author, state);
                dto.setImgMain(img);
                dto.setDetail(detail);
                dto.setPlaceID(Integer.parseInt(placeID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return dto;
    }

    public PostModifyDTO getModifyByPostID(int id) {
        PostModifyDTO dto = null;
        String sql = "Select ModifyID, PostID, ModifyTime, ModifierID, State, PostContent "
                + "from PostModify where PostID = ?";
        String editor = "", state = "", content = "";
        Timestamp time = null;
        int mID, pID;
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                mID = rs.getInt("ModifyID");
                pID = rs.getInt("PostID");
                editor = rs.getString("ModifierID");
                state = rs.getString("State");
                content = rs.getString("PostContent");
                time = rs.getTimestamp("ModifyTime");
                dto = new PostModifyDTO(mID, pID, editor, state, content, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return dto;
    }

    public int countComment(String id) {
        int comment = 0;
        String sql = "select COUNT(CommentID) as CommentNum from Comment where PostID=?";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                comment = rs.getInt("CommentNum");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return comment;
    }

    //Hết của Trưởng Nhóm
    //của phó nhóm
    public boolean deletePost(String postID) {
        try {
            conn = DBConnection.getConnection();
            String sql = "update Post set State='Ban' where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postID);
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

    public boolean deletePostByUserID(String accID) {
        try {
            conn = DBConnection.getConnection();
            String sql = "update Post set State='Ban' where CreaterID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accID);
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

    public boolean likePost(String postId, String accountId, Timestamp today) {
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into LikeManage values(?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postId);
            stm.setString(2, accountId);
            stm.setTimestamp(3, today);
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

    public boolean unlikePost(String postId, String accountId) {
        try {
            conn = DBConnection.getConnection();
            String sql = "delete LikeManage where PostID=? and AccountID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postId);
            stm.setString(2, accountId);
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

    public int countLikeOfPost(String postId) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select COUNT(AccountID) as LikeNum from LikeManage where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postId);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("LikeNum");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }
//for role = Editor

    public int countTotalOfPost() {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select COUNT(PostID) as TotalPost from Post";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TotalPost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    //For role = Moderator, count total of post of moderator
    public int countTotalOfPostOfAccount(String accountId) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select COUNT(PostID) as TotalPost from Post where CreaterID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TotalPost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    //Count Total of like of post by createeId
    public int countTotalOfLikeOfAccount(String accountId) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select COUNT(AccountID) as TotalLike from LikeManage "
                    + "where PostID in (select PostID from Post where CreaterID=?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TotalLike");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int countTotalOfLikeByMember(String accountId) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select COUNT(AccountID) as TotalLike from LikeManage where AccountID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TotalLike");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    //của thành viên
    public int AddNewPost(PostDTO newPost) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Post(Title,DateAccepted,CreaterID,Detail,ImageMain,State,PlaceID) values (?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, newPost.getTitle());
            stm.setTimestamp(2, newPost.getDateAccepted());
            stm.setString(3, newPost.getCreaterID());
            stm.setString(4, newPost.getDetail());
            stm.setString(5, newPost.getImgMain());
            stm.setString(6, newPost.getState());
            stm.setInt(7, newPost.getPlaceID());
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
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

    public int AddNewPicture(ImageDTO dto) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Image(ImageID,Details,PostID) values (?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getImageID());
            stm.setString(2, dto.getDetails());
            stm.setString(3, dto.getPostID());
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int AddNewPlace(PlaceDTO dto) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Place(Address,Phone,Name,Type) values (?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getAddress());
            stm.setString(2, dto.getPhone());
            stm.setString(3, dto.getPlaceName());
            stm.setString(4, dto.getPlaceType());
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int getMaxIdOfPlace() {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "Select top 1 PlaceID from Place order by PlaceID DESC";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getInt("PlaceID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int getMaxIdOfPost() {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "Select top 1 PostID from Post order by PostID DESC";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getInt("PostID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int UpdatePost(PostDTO updatePost) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "update Post set Title=?,DateAccepted=?,CreaterID=?,Detail=?,ImageMain=?,State=? where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, updatePost.getTitle());
            stm.setTimestamp(2, updatePost.getDateAccepted());
            stm.setString(3, updatePost.getCreaterID());
            stm.setString(4, updatePost.getDetail());
            stm.setString(5, updatePost.getImgMain());
            stm.setString(6, updatePost.getState());
            stm.setInt(7, Integer.parseInt(updatePost.getId()));
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int UpdatePlace(PlaceDTO dto) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            //insert into Place(PlaceID,Address,Phone,Name,Type) values (?,?,?,?,?)
            String sql = "update Place set Address=?,Phone=?,Name=? where PlaceID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getAddress());
            stm.setString(2, dto.getPhone());
            stm.setString(3, dto.getPlaceName());
            stm.setString(4, dto.getPlaceId());
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public PlaceDTO getPlace(int id) {
        PlaceDTO result = new PlaceDTO();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from Place where PlaceID=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String address = rs.getString("Address");
                result.setAddress(address);
                result.setPlaceName(rs.getString("Name"));
                result.setPhone(rs.getString("Phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<CommentDTO> getPostComment(String PostID) {
        List<CommentDTO> result = new ArrayList<CommentDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select t2.Name,t1.Detail,t1.DateCreated,t1.CommentID, t2.AccountID  from Comment t1,Account t2 where t1.PostID=? and t1.CreaterID = t2.AccountID";
            stm = conn.prepareStatement(sql);
            stm.setString(1, PostID);
            rs = stm.executeQuery();
            while (rs.next()) {
                CommentDTO dto = new CommentDTO();
                dto.setNameOfCreater(rs.getString("Name"));
                dto.setDetail(rs.getString("Detail"));
                dto.setDateCreated(rs.getTimestamp("DateCreated"));
                dto.setCommentID(rs.getString("CommentID"));
                dto.setCreaterID(rs.getString("AccountID"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<PostDTO> get5RecentlyPostForDashboard() {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT TOP 5 PostID,Title,DateAccepted "
                    + "FROM Post where State='Active' ORDER BY DateAccepted DESC;";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setDateAccepted(rs.getTimestamp("DateAccepted"));//ngay post duoc duyet
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }
    //lay ra cac bai post duoc active cua 1 thang 

    public List<PostDTO> get5RecentlyPostForDashboardByAccountId(String accountId) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT TOP 5 PostID,Title,DateAccepted "
                    + "       FROM Post where [State]='Active' and CreaterID=? "
                    + "       ORDER BY DateAccepted DESC;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setDateAccepted(rs.getTimestamp("DateAccepted"));//ngay post duoc duyet
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<PostDTO> get5RecentlyLikeForDashboardByMember(String accountId) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT TOP 5 t1.AccountID, t1.PostID, t2.Title "
                    + "FROM LikeManage t1, Post t2 "
                    + "WHERE t1.PostID=t2.PostID and t1.AccountID=? "
                    + "ORDER BY t1.DateCreater";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setCreaterID(rs.getString("AccountID"));//ngay post duoc duyet
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<PostDTO> get3PostForArticlePage(int pageNum) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT p.PostID,Title,DateAccepted,p.Detail, a.Name, count(c.PostID) as NumCmt, p.ImageMain "
                    + "       FROM Post p "
                    + "	FULL OUTER JOIN Comment c on p.PostID=c.PostID "
                    + "	INNER JOIN Account a on a.AccountID = p.CreaterID "
                    + "	where p.[State]='Active' "
                    + "	group by p.PostID,p.Title, p.DateAccepted, p.Detail, a.Name, p.ImageMain "
                    + "	ORDER BY DateAccepted DESC "
                    + "       OFFSET (?-1)*3 rows "
                    + "       FETCH NEXT 3 ROWS only";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, pageNum);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setDateAccepted(rs.getTimestamp("DateAccepted"));//ngay post duoc duyet
                dto.setDetail(rs.getString("Detail"));//Detail cua post
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                dto.setCreaterName(rs.getString("Name"));
                dto.setCommentNum(rs.getInt("NumCmt"));
                dto.setImgMain(rs.getString("ImageMain"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<PostDTO> get3PostForArticlePageByCategory(int pageNum, String categoryID) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {

            conn = DBConnection.getConnection();
            String sql = " SELECT p.PostID,Title,DateAccepted,p.Detail, a.Name, count(c.PostID) as NumCmt, p.ImageMain "
                    + "	FROM Post p "
                    + "	FULL OUTER JOIN Comment c on p.PostID=c.PostID "
                    + "	INNER JOIN Account a on a.AccountID = p.CreaterID "
                    + "	INNER JOIN CateManage d on p.PostID=d.PostID "
                    + "	where d.CateID=? and p.[State]='Active' "
                    + "       group by p.PostID, p.Title, p.DateAccepted, p.Detail, a.Name, p.ImageMain "
                    + "       ORDER BY DateAccepted DESC "
                    + "       OFFSET (?-1)*3 rows "
                    + "       FETCH NEXT 3 ROWS only";
            stm = conn.prepareStatement(sql);
            stm.setString(1, categoryID);
            stm.setInt(2, pageNum);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setDateAccepted(rs.getTimestamp("DateAccepted"));//ngay post duoc duyet
                dto.setDetail(rs.getString("Detail"));//Detail cua post
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                dto.setCreaterName(rs.getString("Name"));
                dto.setCommentNum(rs.getInt("NumCmt"));
                dto.setImgMain(rs.getString("ImageMain"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public List<PostDTO> get3PostForArticlePageByTag(int pageNum, String tagID) {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {

            conn = DBConnection.getConnection();
            String sql = "SELECT p.PostID,Title,DateAccepted,p.Detail, a.Name, count(c.PostID) as NumCmt, p.ImageMain "
                    + "	FROM Post p "
                    + "	FULL OUTER JOIN Comment c on p.PostID=c.PostID "
                    + "	INNER JOIN Account a on a.AccountID = p.CreaterID "
                    + "	INNER JOIN TagManage d on p.PostID=d.PostID "
                    + "	where d.TagID=? and p.[State]='Active' "
                    + "       group by p.PostID, p.Title, p.DateAccepted, p.Detail, a.Name, p.ImageMain "
                    + "       ORDER BY DateAccepted DESC "
                    + "       OFFSET (?-1)*3 rows "
                    + "       FETCH NEXT 3 ROWS only";
            stm = conn.prepareStatement(sql);
            stm.setString(1, tagID);
            stm.setInt(2, pageNum);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setDateAccepted(rs.getTimestamp("DateAccepted"));//ngay post duoc duyet
                dto.setDetail(rs.getString("Detail"));//Detail cua post
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                dto.setCreaterName(rs.getString("Name"));
                dto.setCommentNum(rs.getInt("NumCmt"));
                dto.setImgMain(rs.getString("ImageMain"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int DeleteAllCateManageForThisPost(String postID) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "delete from CateManage where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postID);
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int DeleteAllTagManageForThisPost(String postID) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "delete from TagManage where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postID);
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int DeleteAllPictureForThisPost(String postID) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "delete from Image where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postID);
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    //Top post count by number of like
    public List<PostDTO> topPostCountByLike() {
        List<PostDTO> result = new ArrayList<PostDTO>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select top 5 t1.PostID, t1.Title, t1.DateAccepted, count(t2.AccountID) as LikeNum, a.Name "
                    + "           from Post t1, LikeManage t2, Account a where t1.PostID = t2.PostID and a.AccountID=t1.CreaterID and t1.State='Active' "
                    + "              group by t1.PostID, t1.Title, t1.DateAccepted, a.Name "
                    + "             order by LikeNum desc";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setDateAccepted(rs.getTimestamp("DateAccepted"));//ngay post duoc duyet
                dto.setTitle(rs.getString("Title"));//Title cua post
                dto.setId(rs.getInt("PostID") + "");//ID cua post
                dto.setLikeNum(rs.getInt("LikeNum"));
                dto.setCreaterName(rs.getString("Name"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public String getPlaceIdOfPost(int postID) {
        String result = "fail";
        try {
            conn = DBConnection.getConnection();
            String sql = "Select PlaceID from Post where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, postID);
            rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getString("PlaceID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public boolean getIsLike(String accountId, String postId) {
        try {
            conn = DBConnection.getConnection();
            String sql = "select PostId, AccountID from LikeManage where PostID=? and AccountID=? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, postId);
            stm.setString(2, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return false;
    }

    public int changeStatusOfPost(int postID, String status) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "update Post set State=? where PostID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, postID);
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }
}
