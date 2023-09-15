package com.example.demo.auth.cotroller;

import com.example.demo.auth.DTO.DadosLogin;
import com.example.demo.auth.infra.security.DadosToken;
import com.example.demo.auth.infra.security.TokenService;
import com.example.demo.auth.model.Usuario;
import com.example.demo.auth.repository.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRespository usuarioRespository;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> logar(@RequestBody DadosLogin dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authenticate = authenticationManager.authenticate(token);
        String tokenJWT = tokenService.generateToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok().body(new DadosToken(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody DadosLogin data){

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        Usuario usuario = new Usuario(null, data.login(), encryptedPassword);

        usuarioRespository.save(usuario);
        return ResponseEntity.ok("");
    }
}
