package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateUserDto;
import com.project.proiectspring.model.LoginRequest;
import com.project.proiectspring.model.LoginResponse;
import com.project.proiectspring.model.User;
import com.project.proiectspring.security.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Arrays;


@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private JWTService jwtService;

    private UserController userController;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JWTService jwtService, UserController userController) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userController = userController;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtService.generateJWTToken(authentication);

            Cookie cookie = new Cookie("token", jwtToken);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            response.setHeader("Authorization", jwtToken);

            logger.info("User logged in. Email:" + loginRequest.getEmail());

            return ResponseEntity.ok(new RedirectView("/"));
        } catch (AuthenticationException e) {
            logger.info("Incorrect credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();
        Arrays.stream(request.getCookies()).forEach(x -> x.setMaxAge(0));
        logger.info("User logged out");
        response.sendRedirect("/");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody CreateUserDto createUserDto, HttpServletResponse response) {

        try {
            logger.info("Received signup POST");

            userController.create(createUserDto);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(createUserDto.getEmail(), createUserDto.getPassword())
            );

            logger.info("Authenticated User");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtService.generateJWTToken(authentication);

            Cookie cookie = new Cookie("token", jwtToken);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            response.setHeader("Authorization", jwtToken);

            logger.info("Redirecting user to Main Menu");
            return ResponseEntity.ok(new RedirectView("/"));
        } catch (AuthenticationException e) {
            logger.info("Invalid Data");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid data");
        }
    }

}
