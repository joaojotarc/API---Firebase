package br.com.firebase.api.dao;

import lombok.Data;

@Data
public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
}
