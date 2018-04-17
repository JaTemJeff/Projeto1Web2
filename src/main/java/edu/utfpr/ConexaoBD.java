package edu.utfpr;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexaoBD {
    
    private Connection con;
    private String driver = "org.postgresql.Driver";
    
    public ConexaoBD(){
        getConnection();
        criaTabelas();
    }
    
    public Connection getConnection(){
        URI dbUri;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            System.out.println(username);
            String password = dbUri.getUserInfo().split(":")[1];
            System.out.println(password);
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() +
                                  ':' + dbUri.getPort() + dbUri.getPath();
            System.out.println(dbUrl);
            Class.forName(driver);
            this.con = DriverManager.getConnection(dbUrl, username, password);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    private void criaTabelas(){
        getConnection();
        try {
            PreparedStatement st = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Video (" +
                            "id SERIAL PRIMARY KEY," +
                            "titulo VARCHAR(255)" +
                            ");");
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvarVideo(String nome){
        getConnection();
        String sql = "insert into Video (titulo) values (?);";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
