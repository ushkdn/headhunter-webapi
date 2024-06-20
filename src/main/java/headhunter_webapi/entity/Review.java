package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Review")
public class Review {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private Company reviewCompany;
   @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Rating")
    private Double rating;
}
