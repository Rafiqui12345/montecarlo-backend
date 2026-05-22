package com.montecarlo.controller;

import com.montecarlo.dto.AuthResponseDTO;
import com.montecarlo.dto.LoginDTO;
import com.montecarlo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginDTO loginDTO) {

        return authService.login(loginDTO);
    }
}