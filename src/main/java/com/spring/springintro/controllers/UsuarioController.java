package com.spring.springintro.controllers;

import com.spring.springintro.dao.UsuarioDao;
import com.spring.springintro.models.Usuario;
import com.spring.springintro.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Gabriel");
        usuario.setApellido("Vidal");
        usuario.setEmail("gabriel@correo.com");
        usuario.setTelefono("91313414");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios( @RequestHeader(value = "Authorization") String token ){
        try {
            if(!validarToken(token)){ return null; }

            return usuarioDao.getUsuarios();

        }catch (Exception e){
            System.out.println("Error al verificar JWT");
            System.out.println(e.toString());
            return new ArrayList<>();
        }

    }

    private boolean validarToken(String token){
        try {
            String usuarioId = jwtUtil.getKey(token);
            return usuarioId != null;

        }catch (Exception e){
          throw e;
        }
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword().toCharArray());

        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "usuario2")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Gabriel");
        usuario.setApellido("Vidal");
        usuario.setEmail("gabriel@correo.com");
        usuario.setTelefono("91313414");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        try{
            if(!validarToken(token)){ return; }
            usuarioDao.eliminar(id);

        }catch (Exception e){
            throw e;
        }
    }

//    @RequestMapping(value = "usuario4")
//    public Usuario buscar(){
//        Usuario usuario = new Usuario();
//        usuario.setNombre("Gabriel");
//        usuario.setApellido("Vidal");
//        usuario.setEmail("gabriel@correo.com");
//        usuario.setTelefono("91313414");
//        return usuario;
//    }
}
