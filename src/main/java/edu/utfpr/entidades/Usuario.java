package edu.utfpr.entidades;

import javax.validation.constraints.NotNull;

public class Usuario {
    
    @NotNull(message = "Nome n�o pode ser nulo")
    private String email;
    
    @NotNull(message = "Senha n�o pode ser nulo")
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
