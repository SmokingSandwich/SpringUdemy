package com.example.udemy.security;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class SsoController {
    @GetMapping("/logout")
    void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
