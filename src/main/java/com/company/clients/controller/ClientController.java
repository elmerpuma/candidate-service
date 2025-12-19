package com.company.clients.controller;

import com.company.clients.dto.request.CreateClientRequest;
import com.company.clients.dto.response.ClientMetricsResponse;
import com.company.clients.dto.response.ClientResponse;
import com.company.clients.security.JwtProvider;
import com.company.clients.service.ClientService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public ClientController(ClientService clientService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody CreateClientRequest request) {
        ClientResponse r = clientService.createClient(request);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponse>> list() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/clients/metrics")
    public ResponseEntity<ClientMetricsResponse> metrics() {
        return ResponseEntity.ok(clientService.getMetrics());
    }

    public static class LoginRequest {
        public String username;
        public String password;
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class TokenResponse {
        public String token;
        public TokenResponse(String token) { this.token = token; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }

    @PostMapping("/auth/token")
    public ResponseEntity<TokenResponse> token(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = jwtProvider.generateToken(principal);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}

