package edu.utfpr.entidadesedao;

import edu.utfpr.ConexaoBD;
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
            PreparedStatement st = st = con.prepareStatement("INSERT INTO Usuario (nome, senha) " +"VALUES (?, ?);");
            st.setString(1, u.getNome());
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
            PreparedStatement st = con.prepareStatement("DELETE FROM Usuario WHERE nome LIKE '" + u.getNome() + "';");
            st.execute();
            con.close();
        } catch (Exception e) {
            throw new Exception ("Nome Inexistente");
        }
    }
    public void alterarUsuario (Usuario u, String nome) throws Exception {
        try{
            con = bd.getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE Usuario SET nome = '"+ nome +"' WHERE nome LIKE '" + u.getNome() + "';");
            st.execute();
            con.close();
        } catch (Exception e) {
            throw new Exception ("Nome Inexistente");
        }
    }
    public Usuario buscarUsuarioNomeSenha(String nome, String senha) throws Exception {
        con = bd.getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Usuario WHERE nome LIKE '" + nome +"' AND senha LIKE '"+senha+"';");
        ResultSet rs = st.executeQuery();
        if (rs.next()){
            Usuario u = new Usuario();
            u.setNome(rs.getString("nome"));
            u.setSenha(rs.getString("senha"));
            con.close();
            return u;
        } else {
            throw new Exception ("Usuario errado!");
        }
    }
}
