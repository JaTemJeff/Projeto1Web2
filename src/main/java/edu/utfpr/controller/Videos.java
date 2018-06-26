package edu.utfpr.controller;

import com.google.gson.Gson;
import edu.utfpr.model.bancodedados.VideoModel;
import edu.utfpr.model.entidades.Video;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/videos")
public class Videos extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VideoModel model = new VideoModel();
        List<Video> lista = model.listar(request.getParameter("busca"));
        response.setContentType("application/json");
        response.getWriter().print((new Gson()).toJson(lista));
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
