package edu.utfpr.model.entidades;

import javax.validation.constraints.NotNull;

public class Usuario {
    
    @NotNull(message = "Nome n√£o pode ser nulo")
    private String email;
    
    @NotNull(message = "Senha n√£o pode ser nulo")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        
        if (email == null)
            throw new Exception ("Email n„o foi informado!");
        else    
            this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if (senha == null)
            throw new Exception ("Senha n„o foi informada!");
        else    
            this.senha = senha;
    }
}
