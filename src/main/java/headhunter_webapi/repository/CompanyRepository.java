package headhunter_webapi.repository;

import headhunter_webapi.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface CompanyRepository extends Function<Company, Long> {
}
