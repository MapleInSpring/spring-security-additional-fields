package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {
    @GetMapping("only-special-ones-can-see")
    public String getContent() {
        return "You should only see this after authentication";
    }
}
