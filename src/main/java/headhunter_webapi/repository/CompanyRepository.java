package headhunter_webapi.repository;

import headhunter_webapi.entity.Company;

import java.util.function.Function;

public interface CompanyRepository extends Function<Company, Long> {
}
