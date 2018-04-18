/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr;

import edu.utfpr.entidadesedao.Usuario;
import edu.utfpr.entidadesedao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "Cadastro", urlPatterns = {"/cadastro"})
public class Cadastro extends HttpServlet {
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();        
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\"");
        writer.println("              content=\"text/html; charset=utf-8\"/>");
        writer.println("        <title>Login</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        if (req.getParameter("save").equals("false")) {
            res.getWriter().println("<script>alert(\"Usuario ja cadastrado, tente outro!\");</script>");
        }
        writer.println("        <h1>Cadastro</h1>");
        writer.println("        <form action=\"cadastro\" method=\"POST\">");
        writer.println("            <input type=\"text\" name=\"usuario\" value=\"\" required>");
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
        u.setNome(req.getParameter("usuario"));
        u.setSenha(req.getParameter("senha"));
        try {
            uDAO.salvaUsuario(u);
            req.getSession().setAttribute("mensagem", "Usuario "+u.getNome()+" cadastrado!");
            res.sendRedirect("login");
        } catch (PSQLException ex) {
            res.sendRedirect("cadastro?save=false");
            res.getWriter().println("<script>alert(\"Usuario ja cadastrado, tente outro!\");</script>");
        }
    }

}
