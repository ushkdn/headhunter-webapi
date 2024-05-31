package headhunter_webapi.repository;

import headhunter_webapi.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface ReviewRepository extends Function<Review, Long> {
}
