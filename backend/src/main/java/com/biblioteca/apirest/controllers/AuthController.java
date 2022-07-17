package com.biblioteca.apirest.controllers;


import com.biblioteca.apirest.models.Role;
import com.biblioteca.apirest.models.TypeRole;
import com.biblioteca.apirest.models.User;
import com.biblioteca.apirest.payload.request.LoginRequest;
import com.biblioteca.apirest.payload.request.SignupRequest;
import com.biblioteca.apirest.payload.response.JwtResponse;
import com.biblioteca.apirest.payload.response.MessageResponse;
import com.biblioteca.apirest.repository.RoleRepository;
import com.biblioteca.apirest.repository.UserRepository;
import com.biblioteca.apirest.security.jwt.JwtUtils;
import com.biblioteca.apirest.security.services.UserDetailsImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
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

    @ApiOperation(value="Retorna uma lista com todos os usuários")
    @GetMapping("/users")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers(){

        return userRepository.findAll();
    }
    
    @ApiOperation(value="Realiza login de usuário e retorna token de autenticação")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value="Realiza cadastro de usuário no sistema")
    @PostMapping("/signup")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erro: Já existe um usuário com esse nome"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erro: Já existe um usuário com esse email!"));
        }
        if(signUpRequest.getPassword().length() < 6){
            return ResponseEntity.badRequest().body(new MessageResponse("Erro: A senha possui menos de 6 caracteres!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        String strRole = signUpRequest.getRole();
        Role role;
            if (strRole.equals("admin")) {
                 role = roleRepository.findByName(TypeRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Erro: a função não foi encontrada."));
            } else if (strRole.equals("mod")) {
                role = roleRepository.findByName(TypeRole.ROLE_LIBRARIAN)
                        .orElseThrow(() -> new RuntimeException("Erro: a função não foi encontrada."));
            } else {
                role = roleRepository.findByName(TypeRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Erro: a função não foi encontrada."));
            }

        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value="Deleta uma usuário com base no id")
    @DeleteMapping("/user/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deleteUser(@PathVariable(value="id") long id) {
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value="Atualiza um usuário")
    @PutMapping("/user")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }


}
