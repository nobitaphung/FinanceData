package com.pmkha.stockdata.controller;


import com.pmkha.stockdata.model.ERole;
import com.pmkha.stockdata.model.Role;
import com.pmkha.stockdata.model.User;
import com.pmkha.stockdata.payload.JwtResponse;
import com.pmkha.stockdata.payload.LoginRequest;
import com.pmkha.stockdata.payload.MessageResponse;
import com.pmkha.stockdata.payload.SignupRequest;
import com.pmkha.stockdata.repository.RoleRepository;
import com.pmkha.stockdata.repository.UserRepository;
import com.pmkha.stockdata.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user/auth")
public class UserAuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    /** Test API with Postman
     * {
     *     "username": "manhkha",
     *     "email": "pmkha@humaxdigital.com",
     *     "password" : "10090720"
     * }
     * **/
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        String strRoles = signupRequest.getPassword();

        if(strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        }

        user.setRoles(roles);
        user.setEnabled(true);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(("User registered successfully")));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
