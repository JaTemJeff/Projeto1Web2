package edu.utfpr;

import edu.utfpr.bancodeados.ConexaoBD;
import edu.utfpr.bancodeados.VideoBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListarVideo", urlPatterns = {"/listavideos"})
public class ListarVideo extends HttpServlet {

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
            writer.println("        <title>Lista de videos</title>");
            writer.println("        <link rel=\"stylesheet\" href=\"styles.css\">");
            writer.println("    </head>");
            writer.println("        <h1>Lista de Videos:</h1>");
            writer.println("<ul>");
            VideoBD bd = new VideoBD();
            List<String> videos = bd.listarVideos();
            if(!videos.isEmpty()){
                for (String nome : videos) {
                    writer.println( "<li>"+
                                        "<video width=\"160\" height=\"120\" controls>"+
                                            "<source src=\"uploads/" + nome + ".mp4\" type=\"video/mp4\">"+
                                        "</video>" +
                                        "<h4>" + nome + "</h4>"+
                                    "</li>");
                }
            } else {
                writer.println("<h3>Video encontrado</h3>");
            }
            writer.println("        <form action=\"buscarvideo\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Buscar videos\">");
            writer.println("        </form>");
            writer.println("        <form action=\"uploadvideo\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Upload de videos\">");
            writer.println("        </form>");
            writer.println("</ul>");
            writer.println("    </body>");
            writer.println("</html>");
        } else {
            response.sendRedirect("login");
        }
    }
}
