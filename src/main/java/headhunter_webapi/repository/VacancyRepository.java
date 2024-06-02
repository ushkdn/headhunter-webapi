package headhunter_webapi.repository;

import headhunter_webapi.entity.Vacancy;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface VacancyRepository extends Function<Vacancy, Long> {
}