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

/**
 *
 * @author Aluno
 */
@WebServlet(name = "Cadastro", urlPatterns = {"/cadastro"})
public class Cadastro extends HttpServlet {

     public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        Usuario u = new Usuario();
        UsuarioDAO uDAO = new UsuarioDAO();
        u.setNome(req.getParameter("usuario"));
        u.setSenha(req.getParameter("senha"));
        try {
            uDAO.salvaUsuario(u);            
            req.getSession().setAttribute("cadastro", new Boolean(true));
            req.getSession().setAttribute("mensagem", "Cadastrado com sucesso");
            res.sendRedirect("login");
        } catch (Exception ex) {
            res.getWriter().println("<script>alert(\"Usuario ja cadastrado, tente outro!\");</script>");
        }
    }

}
