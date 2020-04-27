package com.carrepairshop.springframework.agh.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/accessDenied")
public class AccessDeniedController {

    @GetMapping
    public String message(Principal principal) {
        return principal.getName() + " you don't have access to this page\n";
    }
}
