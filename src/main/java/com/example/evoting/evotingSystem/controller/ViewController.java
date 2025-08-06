package com.example.evoting.evotingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "index";  // templates/index.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";  // templates/register.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // templates/login.html
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard"; // templates/admin-dashboard.html
    }

    @GetMapping("/voter-dashboard")
    public String voterDashboard() {
        return "voter-dashboard"; // templates/voter-dashboard.html
    }

    @GetMapping("/candidate-dashboard")
    public String candidateDashboard() {
        return "candidate-dashboard"; // templates/candidate-dashboard.html
    }
}
