package edu.utfpr.model.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private int id;
    
    @NotNull(message = "Email não pode ser nulo")
    @Column(unique=true)
    private String email;
    
    @NotNull(message = "Senha não pode ser nulo")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        
        if (email == null)
            throw new Exception ("Email n�o foi informado!");
        else    
            this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if (senha == null)
            throw new Exception ("Senha n�o foi informada!");
        else    
            this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
