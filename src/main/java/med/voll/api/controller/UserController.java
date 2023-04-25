package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DTOS.AuthUserDTO;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class UserController {
    
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid AuthUserDTO userData) {
        Authentication Authtoken = new UsernamePasswordAuthenticationToken(userData.usuario(), userData.clave());
        authenticationManager.authenticate(Authtoken);
        var token = tokenService.GenerateToken();
        return ResponseEntity.ok(token);

    }



}
