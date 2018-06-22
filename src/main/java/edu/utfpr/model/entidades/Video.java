/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.model.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aluno
 */
public class Video {
    
    @Id @GeneratedValue
    private long id;
    
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if (nome == null)
            throw new Exception ("Email não foi informado!");
        else    
            this.nome = nome;
    }
    
}
