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
    
    private ConexaoBD bd;
    private Connection con;
    
    public VideoModel(){
    }
    
    public void salvarVideo(String nome) throws PSQLException{
        String sql = "insert into Video (titulo) values (?);";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            st.execute();
            con.close();
        }catch (PSQLException e) {
            throw e;
        }
        catch (SQLException e) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean buscarVideo(String nome){
        String sql = "SELECT * FROM Video WHERE titulo LIKE '" + nome + "';";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            con.close();
            if (rs.next())
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
