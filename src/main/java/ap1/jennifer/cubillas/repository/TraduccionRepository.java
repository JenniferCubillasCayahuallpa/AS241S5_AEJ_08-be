package ap1.jennifer.cubillas.repository;

import ap1.jennifer.cubillas.model.Traduccion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraduccionRepository extends ReactiveMongoRepository<Traduccion, String> {
}
