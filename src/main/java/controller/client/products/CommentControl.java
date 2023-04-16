package controller.client.products;

import com.google.gson.JsonArray;
import dao.client.AuthDAO;
import dao.client.ProductDAO;
import entity.Account;
import entity.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
@WebServlet("/Commentcontrol")
public class CommentControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            Account account =(Account) session.getAttribute("acc");
            String pid = request.getParameter("pid");
            String text = request.getParameter("content");
            Comment cmt=   ProductDAO.commentproduct(text,account.getId(),Integer.parseInt(pid),account.getFullName());
//            cmt.setCreateAt(String.valueOf(Comment.getTimeSinceCreated()));
            String createAtcmt = cmt.getCreateAt();
            System.out.println(String.valueOf(Comment.getTimeSinceCreatedInSeconds()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime first = LocalDateTime.parse(createAtcmt, formatter);
            System.out.println(first);
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(first, now);
           


            Gson gson = new Gson();
            JsonObject jsonobj = new JsonObject();
            jsonobj.addProperty("comment_user",gson.toJson(cmt));

            response.getWriter().println(gson.toJson(jsonobj));

        }catch (Exception e ){
            response.getWriter().println(e.getMessage());

        }

    }
}
