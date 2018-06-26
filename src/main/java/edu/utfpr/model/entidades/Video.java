package edu.utfpr.model.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Video implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private int id;
    
    private String nome;

    public Video() {
    }

    public Video(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
