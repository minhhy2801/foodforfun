/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodforfun.controller;

import foodforfun.dao.CategoryDAO;
import foodforfun.dao.PostDAO;
import foodforfun.dao.TagDAO;
import foodforfun.dto.CategoryDTO;
import foodforfun.dto.PlaceDTO;
import foodforfun.dto.PostDTO;
import foodforfun.dto.TagDTO;
import foodforfun.errorobj.PlaceError;
import foodforfun.errorobj.PostError;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Admin
 */
public class AddpostController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            PostError errPost = new PostError();
            PlaceError errPlace = new PlaceError();
            PostDTO postDTO = new PostDTO();
            PlaceDTO placeDTO = new PlaceDTO();
            boolean isCorrect = true;
            HttpSession session = request.getSession();

            //xu li main image
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException ex) {
                    ex.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                String realPath = null;
                FileItem itemImg = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            if (fileName == null) {
                                fileName = itemName;
                                System.out.println("Path " + fileName);
                                realPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                                System.out.println("Realpath " + realPath);
                                File savedFile = new File(realPath);
                                item.write(savedFile);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                String title = (String) params.get("txtTitle");
                String content = (String) params.get("content");
                String address = (String) params.get("txtAddress");
                String phone = (String) params.get("txtPhone");
                String name = (String) params.get("txtName");
                String state = (String) params.get("statePost");
                String newTag = (String) params.get("txtNewTag");
                String newCate = (String) params.get("txtNewCate");

                if (title.isEmpty()) {
                    errPost.setTitle("Please input title of post!");
                    isCorrect = false;
                } else if (title.length() > 50) {
                    errPost.setTitle("Title must not contain more than 50 character!");
                    isCorrect = false;
                } else {
                    postDTO.setTitle(title);
                }

                if (address.isEmpty()) {
                    errPlace.setAddress("Please input address of this place!");
                    isCorrect = false;
                } else if (address.length() > 50) {
                    errPlace.setAddress("Address must not contain more than 50 character!");
                    isCorrect = false;
                } else {
                    placeDTO.setAddress(address);
                }

                if (phone.isEmpty()) {
                    errPlace.setPhone("Please input phone of this place!");
                    isCorrect = false;
                } else if (phone.length() > 12) {
                    errPlace.setPhone("Phone must not contain more than 12 number character!");
                    isCorrect = false;
                } else {
                    placeDTO.setPhone(phone);
                }

                if (name.isEmpty()) {
                    errPlace.setPlaceName("Please input name of this place!");
                    isCorrect = false;
                } else if (name.length() > 20) {
                    errPlace.setPlaceName("Place name must not contain more than 12 character!");
                    isCorrect = false;
                } else {
                    placeDTO.setPlaceName(name);
                }
                //xu li du lieu trong content
                content = content.replace('\"', '\'');
                postDTO.setDetail(content);
                //state
                postDTO.setState(state);
                //date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date();
                String time = dateFormat.format(date);
                String[] arr = time.split(" ");
                String[] arr1 = arr[0].split("-");
                String[] arr2 = arr[1].split(":");
                Timestamp timet = new Timestamp(Integer.parseInt(arr1[0]) - 1900, Integer.parseInt(arr1[1]) - 1, Integer.parseInt(arr1[2]), Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]), Integer.parseInt(arr2[2]), 0);
                postDTO.setDateAccepted(timet);

                CategoryDAO cateDAO = new CategoryDAO();
                int maxCateID = cateDAO.getMaxIdOfCate();
                String cate;
                int[] listOfPostCate = new int[maxCateID + 1];
                for (int i = 1; i <= maxCateID; i++) {
                    try {
                        cate = (String) params.get("Cate" + i);
                        if (cate != null) {
                            listOfPostCate[i] = i;
                        } else {
                            listOfPostCate[i] = 0;
                        }
                    } catch (Exception ex) {

                    }
                }
                boolean checkCate = false;
                for (int i = 1; i < listOfPostCate.length; i++) {
                    if (listOfPostCate[i] != 0) {
                        checkCate = true;
                        break;
                    }
                }
                if (!checkCate && newCate.isEmpty()) {
                    isCorrect = false;
                    request.getSession().setAttribute("CATEEMPTY", "Please choose atleast 1 category or fill 'New category' textfield!!");
                } else if (newCate.length() > 20) {
                    request.getSession().setAttribute("CATEEMPTY", "New tag must not contain more than 20 characters!");
                }

                TagDAO tagDAO = new TagDAO();
                int maxTagID = tagDAO.getMaxIdOfTag();
                String tag;
                int[] listOfPostTag = new int[maxTagID + 1];
                for (int i = 1; i <= maxTagID; i++) {
                    try {
                        tag = (String) params.get("Tag" + i);
                        if (tag != null) {
                            listOfPostTag[i] = i;
                        } else {
                            listOfPostTag[i] = 0;
                        }
                    } catch (Exception ex) {

                    }
                }
                boolean checkTag = false;
                for (int i = 1; i < listOfPostTag.length; i++) {
                    if (listOfPostTag[i] != 0) {
                        checkTag = true;
                        break;
                    }
                }
                if (!checkTag && newTag.isEmpty()) {
                    isCorrect = false;
                    request.getSession().setAttribute("TAGEMPTY", "Please choose atleast 1 tag or fill 'New tag' textfield!");
                } else if (newTag.length() > 20) {
                    request.getSession().setAttribute("TAGEMPTY", "New tag must not contain more than 20 characters!");
                }

                List<CategoryDTO> listCate = cateDAO.getAllCateName();
                for (int i = 0; i < listCate.size(); i++) {
                    for (int j = 1; j < listOfPostCate.length; j++) {
                        if (listCate.get(i).getId().equals(listOfPostCate[j] + "")) {
                            listCate.get(i).setChecked(true);
                            break;
                        }
                        listCate.get(i).setChecked(false);
                    }
                }

                List<TagDTO> listTag = tagDAO.getAllTagName();
                for (int i = 0; i < listTag.size(); i++) {
                    for (int j = 1; j < listOfPostTag.length; j++) {
                        if (listTag.get(i).getId().equals(listOfPostTag[j] + "")) {
                            listTag.get(i).setChecked(true);
                            break;
                        }
                        listTag.get(i).setChecked(false);
                    }
                }
                if (fileName.isEmpty()) {
                    isCorrect = false;
                    request.setAttribute("EMPTYIMG", "Please choose atleast 1 main image!");
                }

                if (!isCorrect) {
                    DateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
                    String dateT = dateFormate.format(date);
                    request.getSession().setAttribute("DATE", dateT);
                    TagDTO tagDTO = new TagDTO();
                    CategoryDTO cateDTO = new CategoryDTO();

                    if (!newCate.isEmpty()) {
                        request.getSession().setAttribute("INPUTCATE", newCate);
                    }
                    
                    if (!newTag.isEmpty()) {
                        request.getSession().setAttribute("INPUTTAG", newTag);
                    }

                    request.getSession().setAttribute("tagList", listTag);
                    request.getSession().setAttribute("cateList", listCate);
                    request.getSession().setAttribute("errPost", errPost);
                    request.getSession().setAttribute("errPlace", errPlace);
                    request.getSession().setAttribute("postDTO", postDTO);
                    request.getSession().setAttribute("placeDTO", placeDTO);
                    request.getRequestDispatcher("AddPost.jsp").forward(request, response);
                } else {
                    PostDAO dao = new PostDAO();
                    String creator = (String) request.getSession().getAttribute("ID");
                    placeDTO.setPlaceType("Food shop");
                    dao.AddNewPlace(placeDTO);
                    int addedPlace = dao.getMaxIdOfPlace();
                    postDTO.setPlaceID(addedPlace);
                    postDTO.setCreaterID(creator);
                    postDTO.setState("Pending");
                    postDTO.setImgMain(fileName);
                    int result = dao.AddNewPost(postDTO);
                    if (result == 1) {
                        System.out.println("Add duoc post roi ne");
                    } else {
                        System.out.println("Add post sai roi ne");
                    }
                    int newPostID = dao.getMaxIdOfPost();
                    if (!newTag.isEmpty()) {
                        int tagAddResult = tagDAO.addTag(newTag, creator, timet, "");
                        int newTagID = tagDAO.getMaxIdOfTag();
                        tagDAO.AddNewTagManage(newTagID, newPostID);
                    }
                    if (!newCate.isEmpty()) {
                        int cateAddResult = cateDAO.addCate(newCate, creator, timet);
                        int newCateID = cateDAO.getMaxIdOfCate();
                        cateDAO.AddNewCateroriesManage(newCateID, newPostID);
                    }
                    for (int j = 1; j < listOfPostCate.length; j++) {
                        if (listOfPostCate[j] != 0) {
                            cateDAO.AddNewCateroriesManage(j, newPostID);
                        }
                    }
                    for (int j = 1; j < listOfPostTag.length; j++) {
                        if (listOfPostTag[j] != 0) {
                            tagDAO.AddNewTagManage(j, newPostID);
                        }
                    }
                    String details = dao.getPostDetails(title);
                    postDTO.setDetail(details);
                    request.getSession().setAttribute("ADDSTATUS", "Add success!");
                    request.getRequestDispatcher("AddPost.jsp").forward(request, response);

                    //chay dong for add them tagManage va cateManage da duoc check
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
