package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private Company reviewCompany;
    private String title;
    private String description;

    private Double rating;
}
