 package edu.utfpr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

@WebServlet(urlPatterns="/uploadvideo")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
                 maxFileSize=1024*1024*50,
                 maxRequestSize=1024*1024*100)
public class UploadVideo extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private int id = 0;
    private String path;
    private String nome_video; 

    public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null){
            writer.println("<!DOCTYPE HTML>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
            writer.println("        <title>Upload de V�deo</title>");
            writer.println("    </head>");
            writer.println("    <body bgcolor=\"#000000\">");
            writer.println("    <font color=\"FF0000\"> </font>");
            writer.println("        <h1>Enviar v�deos: (.mp4)</h1>");
            writer.println("        <form action=\"uploadvideo\" method=\"POST\"");
            writer.println("                          accept-charset=\"utf-8\"");
            writer.println("                          enctype=\"multipart/form-data\">");
            writer.println("            <input type=\"file\" name=\"arquivo\" value=\"\" />");
            writer.println("            <input type=\"submit\" name=\"enviar\" value=\"submit\" />");
            writer.println("        </form>");
            writer.println("<ul>");
            for (int i = 0; i < this.id; i++) {
                writer.println("<li>"+
                "<video width=\"160\" height=\"120\" controls>"+
                    "<source src=\"uploads/" + i + ".mp4\" type=\"video/mp4\">"+
                "</video></li>");
            }
            writer.println("</ul>");
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
            Files.copy(in, Paths.get(str.toString() + nome_video), StandardCopyOption.REPLACE_EXISTING);
            ConexaoBD conexao = new ConexaoBD();
            conexao.salvarVideo(nome_video);
        }
        part.delete();
        res.sendRedirect("uploadvideo");
   }

}
