package ap1.jennifer.cubillas.rest;

import ap1.jennifer.cubillas.model.ChatRespuesta;
import ap1.jennifer.cubillas.service.ChatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
public class ChatRest {

    private final ChatService service;

    public ChatRest(ChatService service) {
        this.service = service;
    }

    /**
     * POST /api/chat
     * Body: { "pregunta": "¿Cuál es la capital de Francia?" }
     */
    @PostMapping
    public Mono<ChatRespuesta> preguntar(@RequestBody ChatRequest request) {
        return service.preguntar(request.pregunta());
    }

    /**
     * GET /api/chat
     * Retorna todas las respuestas guardadas en MongoDB
     */
    @GetMapping
    public Flux<ChatRespuesta> listar() {
        return service.listarTodas();
    }

    public record ChatRequest(@JsonProperty("pregunta") String pregunta) {}
}
