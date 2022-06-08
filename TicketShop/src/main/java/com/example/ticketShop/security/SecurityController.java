package com.example.ticketShop.security;

import com.example.ticketShop.dto.user.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest){
        return securityService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
