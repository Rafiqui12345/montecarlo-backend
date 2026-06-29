package com.montecarlo.controller;

import com.montecarlo.dto.DashboardDTO;
import com.montecarlo.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardDTO dashboard(){

        return dashboardService.obtenerDashboard();

    }

}