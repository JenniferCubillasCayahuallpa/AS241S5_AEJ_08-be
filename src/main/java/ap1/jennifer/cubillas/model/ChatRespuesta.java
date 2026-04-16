package ap1.jennifer.cubillas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_respuestas")
public class ChatRespuesta {

    @Id
    private String id;
    private String pregunta;
    private String respuesta;
    private LocalDateTime fechaConsulta;

    public ChatRespuesta() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public LocalDateTime getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(LocalDateTime fechaConsulta) { this.fechaConsulta = fechaConsulta; }
}
