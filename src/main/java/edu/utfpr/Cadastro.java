/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        writer.println("        <title>Cadastro</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Cadastro</h1>");
        writer.println("        <form action=\"login\" method=\"POST\">");
        writer.println("            <input type=\"text\" name=\"usuario\" value=\"\">");
        writer.println("            <input type=\"password\" name=\"senha\" value=\"\">");
        writer.println("            <input type=\"submit\" value=\"Cadastrar\">");
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
            uDAO.salvaUsuario(u.getNome(), u.getSenha());
            res.sendRedirect("login");
        } catch (Exception ex) {
            res.getWriter().println("<script>alert(\"Usuario ja cadastrado, tente outro!\");</script>");
        }
    }

}
