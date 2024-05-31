package headhunter_webapi.repository;

import headhunter_webapi.entity.Resume;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface ResumeRepository extends Function<Resume, Byte> {
}
