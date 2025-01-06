package com.onlineshop.be.services;

import com.onlineshop.be.dtos.AuthRequestDTO;
import com.onlineshop.be.entities.Role;
import com.onlineshop.be.entities.RoleType;
import com.onlineshop.be.entities.User;
import com.onlineshop.be.repositories.RoleRepository;
import com.onlineshop.be.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private UserDetailsServiceImpl userDetailsService;

    private JwtTokenService jwtTokenService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    public User register (AuthRequestDTO authRequestDTO){

        User user = new User();
        user.setUsername(authRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER);
        user.getRoles().add(role);
        role.getUsers().add(user);
        return userRepository.save(user);

    }

    public String authenticate (AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        String token = jwtTokenService.generateToken(userDetails);
        return token;
    }
}
