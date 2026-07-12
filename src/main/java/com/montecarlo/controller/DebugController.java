package com.montecarlo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.Socket;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @GetMapping("/smtp")
    public String smtp() {

        try {
            Socket socket = new Socket();

            socket.connect(
                    new InetSocketAddress(
                            "smtp.gmail.com",
                            587
                    ),
                    10000
            );

            socket.close();

            return "Conectado";

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}