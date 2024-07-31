package com.example.projektstartowy.controlerApp;
import com.example.projektstartowy.API.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Controller
public class AppflowControler {

    private final ImageService catService;

    public AppflowControler(ImageService catService) {
        this.catService = catService;
    }
    @GetMapping("/user/home")
    public String userhome(Model model) {

        Mono<String> catImageUrl = catService.getRandomCatImageUrl();
        model.addAttribute("catImageUrl", catImageUrl.block());
        return "userhome.html";  // Zwraca plik welcome.html z katalogu templates
    }
    @GetMapping("/admin/home")
    public String adminhome(Model model) {
        Mono<String> catImageUrl = catService.getRandomCatImageUrl();
        model.addAttribute("catImageUrl", catImageUrl.block());
        return "adminhome.html";  // Zwraca plik welcome.html z katalogu templates
    }


    // ADMIN AUTHOR
    @GetMapping("/admin/author/addAuthor")
    public String addAuthor() {
        return "Author/addAuthor.html";  // Zwraca plik welcome.html z katalogu templates
    }
    @GetMapping("/admin/author/authorDetails")
    public String authorDetail() {
        return "Author/authorDetails.html";  // Zwraca plik welcome.html z katalogu templates
    }
    // ADMIN USER
    @GetMapping("/admin/user/addUsers")
    public String addUser() {
        return "User/addUser.html";
    }
    // ADMIN BOOKS
    @GetMapping("/admin/books/addBooks")
    public String addBook() {
        return "Book/manageBooks.html";
    }
    // ADMIN ORDERS
    @GetMapping("/admin/orders/manageOrders")
    public String manageOrders() {
        return "orders/manageOrders.html";
    }
    // USER ORDERS
    @GetMapping("/user/viewOrders")
    public String addOrder() {
        return "Orders/addOrder.html";
    }
}
