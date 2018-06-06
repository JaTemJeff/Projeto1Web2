package edu.utfpr;

import edu.utfpr.bancodeados.ConexaoBD;
import edu.utfpr.bancodeados.VideoBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        VideoBD bd = new VideoBD();
        ArrayList<String> listVideos = (ArrayList<String>) bd.listarVideos();
        
        
        request.setAttribute("videos", listVideos);
        request.getRequestDispatcher("WEB-INF/view/listarvideo.jsp").include(request, response);
    }
}
