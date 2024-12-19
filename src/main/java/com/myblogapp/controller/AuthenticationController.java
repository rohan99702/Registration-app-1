package com.myblogapp.controller;
import com.myblogapp.entity.User;
import com.myblogapp.payload.LoginDto;
import com.myblogapp.payload.UserDto;
import com.myblogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")//http://localhost:8080/api/authentication
public class AuthenticationController
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto dto)
    {
        if(userRepository.existsByEmail(dto.getEmail()))
        {
            return new ResponseEntity<>("email already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepository.existsByUsername(dto.getUsername()))
        {
            return new ResponseEntity<>("username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userRepository.save(user);

        return new ResponseEntity<>("User Registered",HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> signin(@RequestBody LoginDto logindto)
    {
        //1.supply logindto.getusername() to loadbyuser method in coustomerUserDetails class
        //2.compare username and password(excepted credentials) with actual values in db.
        //3.create authentication reference;it's similar to session variable.

        UsernamePasswordAuthenticationToken ut=new UsernamePasswordAuthenticationToken
                (logindto.getUsername(),logindto.getPassword());
        Authentication authentication=authenticationManager.authenticate(ut);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      return new ResponseEntity<>("sign in successful",HttpStatus.OK);
    }
}
