package com.evaluation.HireTrack.controller;

import com.evaluation.HireTrack.dto.LoginResponseDto;
import com.evaluation.HireTrack.dto.RegisterDto;
import com.evaluation.HireTrack.dto.TokenDto;
import com.evaluation.HireTrack.model.User;
import com.evaluation.HireTrack.service.AuthService;
import com.evaluation.HireTrack.service.UserService;
import com.evaluation.HireTrack.utility.JwtUtility;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtility jwtUtility;
    private final AuthService authService;


    @GetMapping("/login")
    public TokenDto login(Principal principal){
        String username = principal.getName();
        String token = jwtUtility.generateToken(username);
        return new TokenDto(username,token);
    }



    @GetMapping("/user-details")
    public LoginResponseDto getUserDetails(Principal principal){
        String loggedUsername = principal.getName();
        User user =(User) userService.loadUserByUsername(loggedUsername);
        return new LoginResponseDto(
                user.getId(),
                user.getUsername(),
                user.getRole().toString()
        );
    }



    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterDto dto){

        authService.register(dto);
    }




}
