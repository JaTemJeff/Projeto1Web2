package edu.utfpr.controller;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import edu.utfpr.model.entidades.Usuario;
import edu.utfpr.model.bancodedados.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;

@WebServlet(name = "Cadastro", urlPatterns = {"/cadastro"})
public class Cadastro extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        request.getRequestDispatcher("WEB-INF/view/cadastro.jsp").include(request, response);
    }
    
    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException, ServletException {
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
        ResourceBundle bundle = (ResourceBundle) req.getAttribute("bundle");
        
        String email = req.getParameter("usuario");
        String senha = req.getParameter("senha");
        
        if(email == null || email == "" || senha == null || senha == ""){
            req.setAttribute("campos_vazios", bundle.getString("campos_vazios"));
            req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").include(req, res);
        }
        try {
            u.setEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            u.setSenha(senha);
        } catch (Exception ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            uDAO.salvaUsuario(u);
            res.sendRedirect("login");
        } catch (PSQLException ex) {
            req.setAttribute("existente", bundle.getString("usuario_existente"));
            req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").include(req, res);
        } catch (Exception e) {
            req.setAttribute("inesperado", bundle.getString("mensagem_inesperado"));
            req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").include(req, res);
        }
    }

}
