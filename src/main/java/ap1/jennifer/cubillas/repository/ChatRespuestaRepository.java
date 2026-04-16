package ap1.jennifer.cubillas.repository;

import ap1.jennifer.cubillas.model.ChatRespuesta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRespuestaRepository extends ReactiveMongoRepository<ChatRespuesta, String> {
}
