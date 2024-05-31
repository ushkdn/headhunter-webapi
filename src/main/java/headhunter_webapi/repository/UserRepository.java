package headhunter_webapi.repository;

import headhunter_webapi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface UserRepository extends Function<User, Long> {
}
