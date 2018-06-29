package edu.utfpr.model.bancodedados;

import edu.utfpr.model.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.postgresql.util.PSQLException;

public class UsuarioDAO {
 

    public void salvaUsuario (Usuario u) throws PSQLException{
        EntityManager em = EntityManagerPool.getEntityManager();
        em.getTransaction().begin();
            try{
                em.persist(u);
                em.flush();
                em.getTransaction().commit();        
                EntityManagerPool.closeEntityManager();
            } catch(Exception e){
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
                em.getTransaction().rollback();
            }
    }
    public void excluirUsuario (Usuario u) throws Exception {
        EntityManager em = EntityManagerPool.getEntityManager();
        em.getTransaction().begin();
        try{
            em.remove(u);
            em.getTransaction().commit();
        }catch (PersistenceException px) {   
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, px);
        throw new PersistenceException(px.getMessage());  
        }catch(Exception e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
        }
    }
    
    public void alterarUsuario (Usuario u, String email) throws Exception {
        EntityManager em = EntityManagerPool.getEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(u);
            em.getTransaction().commit();
            EntityManagerPool.closeEntityManager();
        }catch(Exception e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
        }
    }
    public Usuario buscarUsuarioEmailSenha(String email, String senha) throws Exception {
        EntityManager em = EntityManagerPool.getEntityManager();
        Usuario usuario = new Usuario();
        em.getTransaction().begin();
        try{
            usuario = (Usuario) em.createQuery(
                    "SELECT u FROM Usuario u WHERE email like :email AND senha LIKE :senha")
                    .setParameter("email", email + "%")
                    .setParameter("senha", senha + "%")
                    .getSingleResult();
            
        } catch(Exception e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
        }
        if(usuario.getEmail() == null){
            throw new Exception("usuario não encontrado");
        } else {
            return usuario;
        }
    }
}
