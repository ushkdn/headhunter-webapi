package headhunter_webapi.repository;

import headhunter_webapi.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Token findValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);
}