package edu.utfpr;

import edu.utfpr.entidadesedao.Usuario;
import edu.utfpr.entidadesedao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();                 
        if (req.getParameter("save") != null) {
            if (req.getParameter("save").equals("true")) {
                res.getWriter().println("<script>alert(\"Usuario cadastrado\");</script>");
            }
        }
        if (req.getParameter("existe") != null) {
            if (req.getParameter("existe").equals("false")) {
                res.getWriter().println("<script>alert(\"Usuario nao encontrado\");</script>");
            }
        }
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\"");
        writer.println("              content=\"text/html; charset=utf-8\"/>");
        writer.println("        <title>Login</title>");
        writer.println("    </head>");
        writer.println("    <body bgcolor=\"#424242\">");     
        writer.println("    <font color=\"#FAFAFA\">");
        writer.println("        <h1 style=\"text-align:center;\">Login</h1>");
        writer.println("        <form style=\"text-align:center;\" action=\"login\" method=\"POST\">");
        writer.println("            <label for=\"usuario\">Usuario:</label>");
        writer.println("            <input type=\"text\" name=\"usuario\" value=\"\" required>");
        writer.println("<br/>");
        writer.println("            <label for=\"senha\">Senha:</label>");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\" required>");
        writer.println("<br/>");
        writer.println("            <input type=\"submit\" value=\"logar\">");
        writer.println("        </form>");
        writer.println("<br/>");
        writer.println("<br/>");
        writer.println("        <form style=\"text-align:center;\" action=\"cadastro\" method=\"GET\">");
        writer.println("            <input type=\"submit\" value=\"Cadastro\">");
        writer.println("        </form>");
        writer.println("    </font>");
        writer.println("    </body>");
        writer.println("</html>");
    }

    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
        u.setNome(req.getParameter("usuario"));
        u.setSenha(req.getParameter("senha"));
        try {
            uDAO.buscarUsuarioNomeSenha(u.getNome(), u.getSenha());
            req.getSession().setAttribute("logado", new Boolean(true));
            req.getSession().setAttribute("mensagem", "Logado com sucesso");
            res.sendRedirect("uploadvideo");
        } catch (Exception ex) {
            res.sendRedirect("login?existe=false");            
        }
    }
}
