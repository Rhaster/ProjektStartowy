package com.example.projektstartowy.API;



import com.example.projektstartowy.API.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiCatController {

    private final ImageService catService;

    public ApiCatController(ImageService catService) {
        this.catService = catService;
    }

    @GetMapping("/api/random-cat")
    public Mono<Map<String, String>> getRandomCat() {
        return catService.getRandomCatImageUrl().map(url -> {
            Map<String, String> response = new HashMap<>();
            response.put("url", url);
            return response;
        });
    }
}
