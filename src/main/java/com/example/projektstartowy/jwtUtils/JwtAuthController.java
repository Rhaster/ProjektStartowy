package com.example.projektstartowy.jwtUtils;

import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserModel userModel) {
        try {
            System.out.println("Attempting to authenticate user: " + userModel.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword())
            );

            // Log successful authentication
            System.out.println("Authentication successful for user: " + userModel.getUsername());

            UserDetails userDetails = userService.loadUserByUsername(userModel.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }
}
