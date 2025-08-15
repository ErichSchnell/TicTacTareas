package com.practicas.TicTacTareas.controller;

import com.practicas.TicTacTareas.config.JwtUtil;
import com.practicas.TicTacTareas.dtos.auth.AuthRequestDTO;
import com.practicas.TicTacTareas.dtos.auth.AuthResponseDTO;
import com.practicas.TicTacTareas.dtos.auth.RegisterRequestDTO;
import com.practicas.TicTacTareas.entity.Usuario;
import com.practicas.TicTacTareas.service.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        System.out.println("Login attempt: " + authRequestDTO.getEmail() + " / " + authRequestDTO.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO request) {

        // Verificar si el usuario ya existe
        if (userDetailsService.userExists(request.getEmail())) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }

        // Crear usuario con contraseña encriptada
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));

        userDetailsService.saveUser(nuevoUsuario);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}