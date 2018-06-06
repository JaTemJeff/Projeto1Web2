package edu.utfpr.model.entidades;

import javax.validation.constraints.NotNull;

public class Usuario {
    
    @NotNull(message = "Nome não pode ser nulo")
    private String email;
    
    @NotNull(message = "Senha não pode ser nulo")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
