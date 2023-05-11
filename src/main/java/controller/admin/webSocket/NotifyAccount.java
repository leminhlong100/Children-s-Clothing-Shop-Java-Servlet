package controller.admin.webSocket;

import dao.client.AccessDAO;
import dao.client.UtilDAO;
import entity.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/client/NotifyAccount")
public class NotifyAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           int idUser = Integer.parseInt(request.getParameter("idUser"));
           response.getWriter().println(idUser);
           request.getSession().invalidate();
       }catch (Exception e){
           e.printStackTrace();
       }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
