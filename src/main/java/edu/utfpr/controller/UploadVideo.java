 package edu.utfpr.controller;

import edu.utfpr.model.bancodedados.VideoModel;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import javax.validation.constraints.NotNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.postgresql.util.PSQLException;

@WebServlet(urlPatterns="/uploadvideo")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
                 maxFileSize=1024*1024*50,
                 maxRequestSize=1024*1024*100)
public class UploadVideo extends HttpServlet {

    private String path;
    @NotNull(message = "Nome do vídeo não pode ser nulo")
    private String nome_video; 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/uploadvideo.jsp").include(request, response);
    }

    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException, ServletException {
        Part part = req.getPart("arquivo");
        nome_video = part.getSubmittedFileName();
        String target_path = req.getServletContext().getRealPath("");
        path = target_path + "/uploads/";
        System.out.println(path);
        InputStream in = part.getInputStream();
      
        if (part.getContentType().equals("video/mp4")) {
            try {
                VideoModel bd = new VideoModel();
                bd.salvarVideo(nome_video.split("\\.")[0]);
                Files.copy(in, Paths.get(path + nome_video), StandardCopyOption.REPLACE_EXISTING);
                
            } catch (PSQLException ex) {
                req.setAttribute("nomecadastrado", "Nome de video ja cadastrado");
                res.sendRedirect("uploadvideo?sucesso=false");
            } catch (SQLException e){
                res.sendRedirect("uploadvideo?erroinesperado=true");
            }
        }
        part.delete(); 
        res.sendRedirect("uploadvideo?sucesso=true");  
    }
}

