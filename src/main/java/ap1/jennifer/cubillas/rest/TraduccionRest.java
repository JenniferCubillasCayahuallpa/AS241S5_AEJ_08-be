package ap1.jennifer.cubillas.rest;

import ap1.jennifer.cubillas.model.Traduccion;
import ap1.jennifer.cubillas.service.TraduccionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/traduccion")
public class TraduccionRest {

    private final TraduccionService service;

    public TraduccionRest(TraduccionService service) {
        this.service = service;
    }

    /**
     * POST /api/traduccion
     * Body: { "texto": "Hello World", "origen": "en", "destino": "es" }
     */
    @PostMapping
    public Mono<Traduccion> traducir(@RequestBody TraduccionRequest request) {
        return service.traducir(request.texto(), request.origen(), request.destino());
    }

    /**
     * GET /api/traduccion
     * Retorna todas las traducciones guardadas en MongoDB
     */
    @GetMapping
    public Flux<Traduccion> listar() {
        return service.listarTodas();
    }

    public record TraduccionRequest(
            @JsonProperty("texto") String texto,
            @JsonProperty("origen") String origen,
            @JsonProperty("destino") String destino) {}
}
