package com.montecarlo.service;

import com.montecarlo.dto.AuthResponseDTO;
import com.montecarlo.dto.LoginDTO;

public interface AuthService {

    AuthResponseDTO login(LoginDTO loginDTO);
}