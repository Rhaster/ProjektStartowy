package com.example.projektstartowy.logging;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginControler {
    @GetMapping("/login")
    public String handleLogin() {
        return "customloginpage.html";
    }
    @GetMapping("/logout")
    public String handleLogout() {
        return "customloginpage.html";  // przekierowanie na customowastronelogowania
    }
}
