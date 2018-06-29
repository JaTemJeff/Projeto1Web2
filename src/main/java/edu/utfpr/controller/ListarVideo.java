package edu.utfpr.controller;

import edu.utfpr.model.bancodedados.VideoModel;
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
        
        /*VideoModel bd = new VideoModel();
        //ArrayList<String> listVideos = (ArrayList<String>) bd.listarVideos();
        
        
        request.setAttribute("videos", listVideos);
        request.getRequestDispatcher("WEB-INF/view/listarvideo.jsp").include(request, response);
    */
    }
}
