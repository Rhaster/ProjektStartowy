package com.example.projektstartowy.API;


import com.example.projektstartowy.API.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class CatController {

    private final ImageService catService;

    public CatController(ImageService catService) {
        this.catService = catService;
    }

    @GetMapping("/random-cat")
    public String getRandomCat(Model model) {
        Mono<String> catImageUrl = catService.getRandomCatImageUrl();
        model.addAttribute("catImageUrl", catImageUrl.block());
        return "random-cat";
    }
}
