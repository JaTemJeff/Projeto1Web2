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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();
        if (req.getSession().getAttribute("cadastro") != null){
            writer.println("        <h1>"+req.getSession().getAttribute("mensagem")+"</h1>");
        }
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\"");
        writer.println("              content=\"text/html; charset=utf-8\"/>");
        writer.println("        <title>Login</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Login</h1>");
        writer.println("        <form action=\"login\" method=\"POST\">");
        writer.println("            <input type=\"text\" name=\"usuario\" value=\"\">");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\">");
        writer.println("            <input type=\"submit\" value=\"logar\">");
        writer.println("        </form>");
        writer.println("        <h1>Cadastro</h1>");
        writer.println("        <form action=\"cadastro\" method=\"POST\">");
        writer.println("            <input type=\"text\" name=\"usuario\" value=\"\">");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\">");
        writer.println("            <input type=\"submit\" value=\"cadastrar\">");
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
            uDAO.buscarUsuarioNomeSenha(u.getNome(), u.getSenha());
            req.getSession().setAttribute("logado", new Boolean(true));
            req.getSession().setAttribute("mensagem", "Logado com sucesso");
            res.sendRedirect("uploadvideo");
        } catch (Exception ex) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().println("<h1>Login errado!!!</h1>");
            res.getWriter().println("        <form action=\"login\" method=\"GET\">");
            res.getWriter().println("            <input type=\"submit\" value=\"Voltar\">");
            res.getWriter().println("        </form>");
        }
    }
}
