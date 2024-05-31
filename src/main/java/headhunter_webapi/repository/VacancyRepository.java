package headhunter_webapi.repository;

import headhunter_webapi.entity.Vacancy;

import java.util.function.Function;

public interface VacancyRepository extends Function<Vacancy, Long> {
}
