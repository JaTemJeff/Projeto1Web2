package edu.utfpr;

import edu.utfpr.entidades.Usuario;
import edu.utfpr.bancodeados.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class login extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        request.getRequestDispatcher("WEB-INF/view/login.jsp").include(request, response);
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
       
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        Usuario u = null;
        try {
            UsuarioDAO uDAO = new UsuarioDAO();
            u = uDAO.buscarUsuarioEmailSenha(u.getEmail(), u.getSenha());
            request.getSession().setAttribute("logado", true);
            request.getSession().setAttribute("usuario", u.getEmail());

            response.sendRedirect("uploadvideo");
        } catch (Exception ex) {
            response.sendRedirect("login");            
        }
    }
}
