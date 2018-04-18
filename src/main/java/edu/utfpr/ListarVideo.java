/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "ListarVideo", urlPatterns = {"/listavideos"})
public class ListarVideo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
            writer.println("<!DOCTYPE HTML>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
            writer.println("        <title>Lista de videos</title>");
            writer.println("    </head>");
            writer.println("        <h1>Lista de Vídeos:</h1>");
            writer.println("<ul>");
            ConexaoBD bd = new ConexaoBD();
            List<String> videos = bd.listarVideos();
            if(videos != null){
                for (String nome : videos) {
                    writer.println("<li>"+
                    "<video width=\"160\" height=\"120\" controls>"+
                        "<source src=\"uploads/" + nome + ".mp4\" type=\"video/mp4\">"+
                    "</video></li>");
                }
            }
            writer.println("</ul>");
            writer.println("    </body>");
            writer.println("</html>");
    }
}
