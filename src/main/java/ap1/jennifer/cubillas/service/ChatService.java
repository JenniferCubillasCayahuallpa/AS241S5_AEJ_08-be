package ap1.jennifer.cubillas.service;

import ap1.jennifer.cubillas.model.ChatRespuesta;
import ap1.jennifer.cubillas.repository.ChatRespuestaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final WebClient webClient;
    private final ChatRespuestaRepository repository;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.openai21.url}")
    private String apiUrl;

    @Value("${rapidapi.openai21.host}")
    private String apiHost;

    public ChatService(WebClient.Builder builder, ChatRespuestaRepository repository) {
        this.webClient = builder.build();
        this.repository = repository;
    }

    /**
     * Envía un mensaje al modelo Llama 3.3 70b via Open AI21 API
     * y guarda la respuesta en MongoDB.
     *
     * @param pregunta mensaje del usuario
     */
    public Mono<ChatRespuesta> preguntar(String pregunta) {
        // ChatGPT API8 recibe un array de mensajes directamente
        List<Map<String, String>> body = List.of(
                Map.of("role", "system", "content", "I'm an AI assistant bot based on ChatGPT 3."),
                Map.of("role", "user", "content", pregunta)
        );

        return webClient.post()
                .uri(apiUrl)
                .header("Content-Type", "application/json")
                .header("x-rapidapi-host", apiHost)
                .header("x-rapidapi-key", rapidApiKey)
                .bodyValue(body)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(errorBody -> new RuntimeException(
                                        "Error de API [" + clientResponse.statusCode() + "]: " + errorBody)))
                .bodyToMono(Map.class)
                .map(response -> {
                    // La respuesta viene en response.choices[0].message.content
                    Object choicesRaw = response.get("choices");
                    String respuestaTexto;
                    if (choicesRaw instanceof List<?> choices && !choices.isEmpty()) {
                        Map<?, ?> first = (Map<?, ?>) choices.get(0);
                        Map<?, ?> message = (Map<?, ?>) first.get("message");
                        respuestaTexto = message != null ? message.get("content").toString() : "Sin respuesta";
                    } else {
                        respuestaTexto = response.toString();
                    }

                    ChatRespuesta chat = new ChatRespuesta();
                    chat.setPregunta(pregunta);
                    chat.setRespuesta(respuestaTexto);
                    chat.setFechaConsulta(LocalDateTime.now());
                    return chat;
                })
                .flatMap(repository::save);
    }

    public Flux<ChatRespuesta> listarTodas() {
        return repository.findAll();
    }
}
