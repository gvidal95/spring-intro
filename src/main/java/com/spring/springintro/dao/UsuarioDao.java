package com.spring.springintro.dao;

import com.spring.springintro.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario verificarEmailPassword(Usuario usuario);
}
