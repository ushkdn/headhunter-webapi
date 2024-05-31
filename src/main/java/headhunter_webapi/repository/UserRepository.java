package headhunter_webapi.repository;

import headhunter_webapi.entity.User;

import java.util.function.Function;

public interface UserRepository extends Function<User, Long> {
}
