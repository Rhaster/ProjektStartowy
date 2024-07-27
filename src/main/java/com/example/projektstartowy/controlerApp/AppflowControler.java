package com.example.projektstartowy.controlerApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AppflowControler {

    @GetMapping("/user/home")
    public String userhome() {
        return "userhome.html";  // Zwraca plik welcome.html z katalogu templates
    }
    @GetMapping("/admin/home")
    public String adminhome() {
        return "adminhome.html";  // Zwraca plik welcome.html z katalogu templates
    }
    @GetMapping("/admin/author/addAuthor")
    public String addAuthor() {
        return "Author/addAuthor.html";  // Zwraca plik welcome.html z katalogu templates
    }
    @GetMapping("/admin/author/authorDetails")
    public String authorDetail() {
        return "Author/authorDetails.html";  // Zwraca plik welcome.html z katalogu templates
    }
}
