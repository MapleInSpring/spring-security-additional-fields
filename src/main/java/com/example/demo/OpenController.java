package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenController {
    @GetMapping("anyone-can-see")
    public String getContent() {
        return "You should see this regardless";
    }
}
