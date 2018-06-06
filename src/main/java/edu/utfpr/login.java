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
       
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
        u.setEmail(request.getParameter("usuario"));
        u.setSenha(request.getParameter("senha"));
        try {
            uDAO.buscarUsuarioEmailSenha(u.getEmail(), u.getSenha());
            request.getSession().setAttribute("logado", new Boolean(true));
            request.getSession().setAttribute("usuario", u.getEmail());

            response.sendRedirect("uploadvideo");
        } catch (Exception ex) {
            request.setAttribute("errologin", "Usuário ou senha errados!");
            response.sendRedirect("login");            
        }
    }
}
