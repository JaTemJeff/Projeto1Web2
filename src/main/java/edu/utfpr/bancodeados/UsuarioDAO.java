package edu.utfpr.bancodeados;

import edu.utfpr.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

public class UsuarioDAO {
    private ConexaoBD bd;
    private Connection con;
    
    public UsuarioDAO() {
        bd = new ConexaoBD();
    }
    public void salvaUsuario (Usuario u) throws PSQLException{
        try {
            con = bd.getConnection();
            PreparedStatement st = st = con.prepareStatement("INSERT INTO Usuario (email, senha) " +"VALUES (?, ?);");
            st.setString(1, u.getEmail());
            st.setString(2, u.getSenha());
            st.execute();
            con.close();
        }catch (PSQLException e) {
            throw e;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void excluirUsuario (Usuario u) throws Exception {
        try{
            con = bd.getConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM Usuario WHERE email LIKE '" + u.getEmail() + "';");
            st.execute();
            con.close();
        } catch (Exception e) {
            throw new Exception ("Email Inexistente");
        }
    }
    public void alterarUsuario (Usuario u, String email) throws Exception {
        try{
            con = bd.getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE Usuario SET email = '"+ email +"' WHERE email LIKE '" + u.getEmail() + "';");
            st.execute();
            con.close();
        } catch (Exception e) {
            throw new Exception ("Email Inexistente");
        }
    }
    public Usuario buscarUsuarioEmailSenha(String email, String senha) throws Exception {
        con = bd.getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Usuario WHERE email LIKE '" + email +"' AND senha LIKE '"+senha+"';");
        ResultSet rs = st.executeQuery();
        if (rs.next()){
            Usuario u = new Usuario();
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            con.close();
            return u;
        } else {
            throw new Exception ("Usuario errado!");
        }
    }
}
