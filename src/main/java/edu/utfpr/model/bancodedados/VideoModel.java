package edu.utfpr.model.bancodedados;

import edu.utfpr.model.entidades.Video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.postgresql.util.PSQLException;


public class VideoModel {
    
    public VideoModel(){
    }
    
    public void salvarVideo(String nome) throws PSQLException{
        Video v = new Video(nome);
        EntityManager em = EntityManagerPool.getEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(v);
            em.flush();
            em.getTransaction().commit();
        } catch(Exception e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
        }
    }
    
    public List<Video> listar(String parametro) {
        EntityManager em = EntityManagerPool.getEntityManager();

        @SuppressWarnings("unchecked")
        List<Video> lista = em.createQuery("SELECT * FROM Video WHERE nome like :busca")
            .setParameter("busca", parametro + "%")
            .getResultList();

        em.close();
        return lista;
    }
    
}
