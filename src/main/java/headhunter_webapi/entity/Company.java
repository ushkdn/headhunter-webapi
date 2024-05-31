package headhunter_webapi.entity;

import java.util.List;

public class Company {
    private Long id;
    private String name;
    private String description;
    private String site;
    private String email;
    private Integer employeesCount;
    private List<Review> reviews;
    private Double rating;
    private List<Vacancy> activeVacancies;
}
