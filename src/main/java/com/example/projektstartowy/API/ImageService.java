package com.example.projektstartowy.API;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

@Service
public class ImageService {

    private final WebClient webClient;

    @Value("${cat.api.key}")
    private String apiKey;

    public ImageService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.thecatapi.com/v1").build();
    }

    public Mono<String> getRandomCatImageUrl() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/images/search")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> response.get(0).get("url").asText());
    }
}
