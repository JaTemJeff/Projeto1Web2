package edu.utfpr;

import edu.utfpr.entidades.Usuario;
import edu.utfpr.bancodeados.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;

@WebServlet(name = "Cadastro", urlPatterns = {"/cadastro"})
public class Cadastro extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        request.getRequestDispatcher("WEB-INF/cadastro.jsp").include(request, response);
    }
    
    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
        String email = req.getParameter("usuario").toString();
        u.setEmail(req.getParameter("usuario"));
        u.setSenha(req.getParameter("senha"));
        try {
            uDAO.salvaUsuario(u);
            res.sendRedirect("login");
        } catch (PSQLException ex) {
            res.sendRedirect("cadastro");
        }
    }

}
