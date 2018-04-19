 package edu.utfpr;

import edu.utfpr.bancodeados.ConexaoBD;
import edu.utfpr.bancodeados.VideoBD;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

@WebServlet(urlPatterns="/uploadvideo")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
                 maxFileSize=1024*1024*50,
                 maxRequestSize=1024*1024*100)
public class UploadVideo extends HttpServlet {

    private String path;
    private String nome_video; 

    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();
        HttpSession session = req.getSession();
        
        if (req.getParameter("sucesso") != null) {
            if (req.getParameter("sucesso").equals("false")) {
                res.getWriter().println("<script>alert(\"Nome do video existente!\");</script>");
            } else if (req.getParameter("sucesso").equals("true")){
                res.getWriter().println("<script>alert(\"Video salvo com sucesso!\");</script>");
            }
        }
        if (req.getParameter("erroinesperado") != null &&
            req.getParameter("erroinesperado").equals("true")){
                res.getWriter().println("<script>alert(\"Erro inesperado!\");</script>");
        }
        
        if (session.getAttribute("logado") != null){
            writer.println("<!DOCTYPE HTML>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
            writer.println("        <title>Upload de Video</title>");
            writer.println("        <link rel=\"stylesheet\" href=\"styles.css\">");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>Enviar videos: (.mp4)</h1>");
            writer.println("        <form action=\"uploadvideo\" method=\"POST\"");
            writer.println("                          accept-charset=\"utf-8\"");
            writer.println("                          enctype=\"multipart/form-data\">");
            writer.println("            <input type=\"file\" name=\"arquivo\" value=\"\" />");
            writer.println("            <input type=\"submit\" name=\"enviar\" value=\"submit\" />");
            writer.println("        </form>");
            writer.println("        <form style=\"text-align:center;\" action=\"buscarvideo\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Buscar videos\">");
            writer.println("        </form>");
            writer.println("        <form style=\"text-align:center;\" action=\"listavideos\" method=\"GET\">");
            writer.println("            <input type=\"submit\" value=\"Listar videos\">");
            writer.println("        </form>");
            writer.println("    </body>");
            writer.println("</html>");
        }else{
            res.sendRedirect("login");
        }
    }

    public void doPost (HttpServletRequest req,
                        HttpServletResponse res) throws IOException, ServletException {
        Part part = req.getPart("arquivo");
        nome_video = part.getSubmittedFileName();
        String videos_path = req.getServletContext().getRealPath("");
        String[] newpath = videos_path.split("\\\\");
        StringBuilder str = new StringBuilder();
        for(String i : newpath){
            if (i.equals("target")){
                break;
            }
            str.append(i+"/");
        }
        str.append("uploads/");
        path = str.toString();
        System.out.println(path);
        InputStream in = part.getInputStream();
      
        if (part.getContentType().equals("video/mp4")) {
            try {
                VideoBD bd = new VideoBD();
                bd.salvarVideo(nome_video.split("\\.")[0]);
                Files.copy(in, Paths.get(str.toString() + nome_video), StandardCopyOption.REPLACE_EXISTING);
                
            } catch (PSQLException ex) {
                res.sendRedirect("uploadvideo?sucesso=false");
            } catch (SQLException e){
                res.sendRedirect("uploadvideo?erroinesperado=true");
            }
        }
        part.delete(); 
        res.sendRedirect("uploadvideo?sucesso=true");  
    }
}

