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
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {        
        PrintWriter writer = res.getWriter();   
        if (req.getParameter("save") != null) {
            if (req.getParameter("save").equals("false")) {
                res.getWriter().println("<script>alert(\"Usuario ja cadastrado, tente outro!\");</script>");
            }
            else if (req.getParameter("save").equals("email")) {
                res.getWriter().println("<script>alert(\"Email invalido\");</script>");
            }
        }
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\"");
        writer.println("              content=\"text/html; charset=utf-8\"/>");
        writer.println("        <title>Cadastro</title>");
        writer.println("        <link rel=\"stylesheet\" href=\"styles.css\">");
        writer.println("    </head>");
        writer.println("    <body>");        
        writer.println("        <h1>Cadastro</h1>");
        writer.println("        <form action=\"cadastro\" method=\"POST\">");
        writer.println("            <label for=\"usuario\">Email:</label>");
        writer.println("            <input type=\"email\" name=\"usuario\" value=\"\" required>");
        writer.println("            <label for=\"senha\">Senha:</label>");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\" required>");
        writer.println("            <input type=\"submit\" value=\"cadastro\">");
        writer.println("        </form>");
        writer.println("        <form action=\"login\" method=\"GET\">");
        writer.println("            <input type=\"submit\" value=\"Voltar\">");
        writer.println("        </form>");
        writer.println("    </body>");
        writer.println("</html>");
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
            res.sendRedirect("login?save=true");
        } catch (PSQLException ex) {
            res.sendRedirect("cadastro?save=false");
        }
    }

}
