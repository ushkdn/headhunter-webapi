package headhunter_webapi.repository;

import headhunter_webapi.entity.Review;

import java.util.function.Function;

public interface ReviewRepository extends Function<Review, Long> {
}
