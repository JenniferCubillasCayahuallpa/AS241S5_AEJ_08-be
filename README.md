# AS241S5_AEJ_##-be — Jennifer Cubillas

Proyecto Spring WebFlux que consume 2 APIs de Inteligencia Artificial via RapidAPI y almacena los resultados en MongoDB Atlas (NoSQL Cloud).

---

## APIs IA utilizadas

### 1. Deep Translate
- Proveedor: RapidAPI (gatzuma)
- Endpoint: `POST https://deep-translate1.p.rapidapi.com/language/translate/v2`
- Función: Traducción de texto entre múltiples idiomas
- Características:
  - Soporta texto plano y HTML
  - Detección automática de idioma origen
  - Más de 100 idiomas disponibles
  - Parámetros: `q` (texto), `source` (idioma origen), `target` (idioma destino)

### 2. ChatGPT API8
- Proveedor: RapidAPI (haxednet)
- Endpoint: `POST https://chatgpt-api8.p.rapidapi.com/`
- Función: Chat conversacional basado en ChatGPT 3
- Características:
  - Basado en ChatGPT 3 de OpenAI
  - Recibe array de mensajes con roles (system, user)
  - Respuestas en lenguaje natural
  - Compatible con formato OpenAI chat completions

---

## Herramientas y versiones

| Herramienta        | Versión     |
|--------------------|-------------|
| Java               | 17          |
| Spring Boot        | 3.3.5       |
| Spring WebFlux     | 6.x         |
| MongoDB Reactive   | Spring Data |
| Lombok             | 1.18.x      |
| Maven              | 3.x         |
| MongoDB Atlas      | Cloud (NoSQL)|

---

## Endpoints disponibles

### Traducción (Deep Translate)
```
POST /api/traduccion
Body: { "texto": "Hello World", "origen": "en", "destino": "es" }

GET /api/traduccion
Retorna todas las traducciones guardadas
```

### Chat IA (Llama 3.3 70b)
```
POST /api/chat
Body: { "pregunta": "¿Cuál es la capital de Francia?" }

GET /api/chat
Retorna todas las respuestas guardadas
```

---

## Configuración

Las credenciales se configuran en `src/main/resources/application.yaml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://<usuario>:<password>@<cluster>.mongodb.net/<dbname>

rapidapi:
  key: TU_RAPIDAPI_KEY
  deep-translate:
    url: https://deep-translate1.p.rapidapi.com/language/translate/v2
    host: deep-translate1.p.rapidapi.com
  openai21:
    url: https://open-ai21.p.rapidapi.com/conversationllama
    host: open-ai21.p.rapidapi.com
```

---

## Colecciones MongoDB

- `traducciones` — resultados de Deep Translate API
- `chat_respuestas` — respuestas del modelo Llama 3.3 70b
