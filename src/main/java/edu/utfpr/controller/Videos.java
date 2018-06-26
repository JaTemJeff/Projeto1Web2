package edu.utfpr.controller;

import com.google.gson.Gson;
import edu.utfpr.model.bancodedados.VideoModel;
import edu.utfpr.model.entidades.Video;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;

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
        if (request.getParameter("opcoes").equals("inserir")) {
            try {
                VideoModel model = new VideoModel();
                model.salvarVideo(request.getParameter("nome"));
            } catch (PSQLException ex) {
                Logger.getLogger(Videos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
