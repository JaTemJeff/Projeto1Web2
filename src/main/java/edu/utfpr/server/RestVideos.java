/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.server;
/**
 *
 * @author Aluno
 */
import edu.utfpr.model.bancodedados.ConexaoBD;
import edu.utfpr.model.bancodedados.VideoBD;
import edu.utfpr.model.entidades.Video;
import java.sql.SQLException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
  
@Path("/user")
public class RestVideos {
  
       @GET
       @Produces(MediaType.TEXT_HTML)
       public String getUserHTML(@QueryParam("titulo") String titulo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
             VideoBD connect = new VideoBD();
             Video video = connect.buscarVideo(titulo);
             return "<HTML><BODY>" + video.getNome()+ "</BODY></HTML>";
       }
  
       @GET
       @Produces(MediaType.APPLICATION_JSON)
       public Video getVideo(@QueryParam("titulo") String titulo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
             VideoBD connect = new VideoBD();
             Video video = connect.buscarVideo(titulo);
             return video;
       }
  
       @POST
       public Response salvarVideo(Video video) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
             VideoBD connect = new VideoBD();
             connect.salvarVideo(video);
             return Response.status(Status.OK).build();
       }
}
