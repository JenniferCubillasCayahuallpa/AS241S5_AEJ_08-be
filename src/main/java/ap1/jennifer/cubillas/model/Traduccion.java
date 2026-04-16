package ap1.jennifer.cubillas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "traducciones")
public class Traduccion {

    @Id
    private String id;
    private String textoOriginal;
    private String idiomaOrigen;
    private String idiomaDestino;
    private String textoTraducido;
    private LocalDateTime fechaConsulta;

    public Traduccion() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTextoOriginal() { return textoOriginal; }
    public void setTextoOriginal(String textoOriginal) { this.textoOriginal = textoOriginal; }

    public String getIdiomaOrigen() { return idiomaOrigen; }
    public void setIdiomaOrigen(String idiomaOrigen) { this.idiomaOrigen = idiomaOrigen; }

    public String getIdiomaDestino() { return idiomaDestino; }
    public void setIdiomaDestino(String idiomaDestino) { this.idiomaDestino = idiomaDestino; }

    public String getTextoTraducido() { return textoTraducido; }
    public void setTextoTraducido(String textoTraducido) { this.textoTraducido = textoTraducido; }

    public LocalDateTime getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(LocalDateTime fechaConsulta) { this.fechaConsulta = fechaConsulta; }
}
