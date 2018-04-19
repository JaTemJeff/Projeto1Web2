package edu.utfpr;

import edu.utfpr.bancodeados.ConexaoBD;
import edu.utfpr.bancodeados.VideoBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "BuscarVideo", urlPatterns = {"/buscarvideo"})
public class BuscarVideo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("logado") != null){
            writer.println("<!DOCTYPE HTML>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
            writer.println("        <title>Buscar Video</title>");
            writer.println("        <link rel=\"stylesheet\" href=\"styles.css\">");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>Buscar video:</h1>");
            writer.println("        <form action=\"buscarvideo\" method=\"POST\">");
            writer.println("            <input type=\"text\" name=\"arquivo\" value=\"\" required>");
            writer.println("            <input type=\"submit\" name=\"enviar\" value=\"submit\" />");
            writer.println("        </form>");
            if (request.getParameter("nome") != null && 
                        request.getParameter("nome").equals("naoencontrado")) {
                response.getWriter().println("<script>alert(\"Video nao encontrado!\");</script>");
            } else if (request.getParameter("nome") != null){
            String video = request.getParameter("nome").toString();
                writer.println("<ul>");
                writer.println("<li>"+
                "<video width=\"640\" height=\"560\" controls>"+
                    "<source src=\"uploads/" + video + ".mp4\" type=\"video/mp4\">"+
                "</video></li>");
                writer.println("</ul>");
            }
            writer.println("        <form style=\"text-align:center;\"action=\"listavideos\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Listar videos\">");
            writer.println("        </form>");
            writer.println("        <form style=\"text-align:center;\" action=\"uploadvideo\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Upload de videos\">");
            writer.println("        </form>");
            writer.println("    </body>");
            writer.println("</html>");
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome_video = request.getParameter("arquivo").toString();
        VideoBD bd = new VideoBD();
        boolean salvo = bd.buscarVideo(nome_video);
        if(salvo){
            response.sendRedirect("buscarvideo?nome=" + nome_video);
        } else {
            response.sendRedirect("buscarvideo?nome=naoencontrado");
            response.getWriter().println("<script>alert(\"Video nao encontrado\");</script>");
        }
    }


}
