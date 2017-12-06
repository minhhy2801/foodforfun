package foodforfun.dao;

import foodforfun.dto.AccountDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import foodforfun.db.DBConnection;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AccountDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public AccountDAO() {
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

    //Của Trưởng Nhóm
    public int addUser(String id, String name, String pass, String role) {
        int check = 0;
        String sql = "insert into Account (AccountID,Name,Password,Role) values(?,?,?,?)";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, pass);
            stm.setString(4, role);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return check;
    }

    public int countTotalOfAcount() {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select COUNT(AccountID) as TotalAccount from Account";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TotalAccount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    public int updateProfileMod(String id, String name, String address,
            String phone, String email, String place, int years,
            boolean gender, Date yob) {
        int check = 0;
        String sql = "update Account set Name = ?, Address=?,"
                + "Phone=?,Email=?,WorkedPlace=?,ExpYears=?,Sex=?,Birthday=?"
                + " where AccountID=?";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, address);
            stm.setString(3, phone);
            stm.setString(4, email);
            stm.setString(5, place);
            stm.setInt(6, years);
            stm.setBoolean(7, gender);
            stm.setDate(8, yob);
            stm.setString(9, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return check;
    }

    public int changePassword(String id, String newPass) {
        int check = 0;
        String sql = "update Account set Password=? where AccountID=?";
        try {
            conn = DBConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, newPass);
            stm.setString(2, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return check;
    }
    //Hết của trưởng nhóm

    //Của phó nhóm
    public String checkLogin(String username, String password) {
        String roleID = "fail";
        try {
            conn = DBConnection.getConnection();
            String sql = "select [Role] from Account where AccountID=? and Password=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                roleID = rs.getString("Role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return roleID;
    }

    public String getEmail(String username) {
        String email = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select Email from Account where AccountID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return email;
    }

    public boolean banUser(String accountId) {
        try {
            conn = DBConnection.getConnection();
            String sql = "update Account set Role='Banner' where AccountID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
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

    public boolean visiblePostOfAccount(String accountId) {
        try {
            conn = DBConnection.getConnection();
            String sql = "update Post set State='Visible' where CreaterID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, accountId);
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
    //Hết của phó nhóm

    //của thành viên
    public int registNewAccount(AccountDTO newAccount) {
        int result = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Account(AccountID,Password,Role,Name,Sex,Email,ExpYears,Phone,Address,Birthday,WorkedPlace) values (?,?,?,?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, newAccount.getAccountID());
            stm.setString(2, newAccount.getPassword());
            stm.setString(3, newAccount.getRole());
            stm.setString(4, newAccount.getName());
            stm.setBoolean(5, newAccount.isSex());
            stm.setString(6, newAccount.getEmail());
            stm.setInt(7, newAccount.getExpYear());
            stm.setString(8, newAccount.getPhone());
            stm.setString(9, newAccount.getAddress());
            stm.setDate(10, newAccount.getDob());
            stm.setString(11, newAccount.getWorledPlace());
            result = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return result;
    }

    //hết của thành viên
    //Gui email
    public void sendFromGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    //Lay password cua user de tra ve trong mail!
    public String getPasswordByUsername(String username) {
        String password = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select Password from Account where AccountID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                password = rs.getString("Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return password;
    }

    public boolean checkDuplicateAccountID(String username) {
        try {
            conn = DBConnection.getConnection();
            String sql = "select AccountID from Account where AccountID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
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
}
