package com.spring.springintro.controllers;

import com.spring.springintro.dao.UsuarioDao;
import com.spring.springintro.models.Usuario;
import com.spring.springintro.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;



    @RequestMapping(value = "api/login")
    public String login(@RequestBody Usuario usuario){
        Usuario usuarioDB = usuarioDao.verificarEmailPassword(usuario);
        if(usuarioDB != null){
            String token = jwtUtil.create(String.valueOf(usuarioDB.getId()), usuario.getEmail());
            return token;
        }
        return "FAIL";
    }

}
