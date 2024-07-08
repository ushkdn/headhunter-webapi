package headhunter_webapi.repository;

import headhunter_webapi.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<RefreshToken, String> {
}
