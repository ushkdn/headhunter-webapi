package headhunter_webapi.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Resume")
public class Resume {
    @Id
    @Column(name="resume_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) - handle rows on db level (current - jpa)
    private User owner;
    @Column(name="speciality")
    private String speciality;
    @Column(name="years_of_experience")
    private Byte yearsOfExperience;
    @Column(name="expected_salary")
    private String expectedSalary;
    @Enumerated(EnumType.STRING)
    @Column(name="work_plan")
    private WorkPlan workPlan;
    @Enumerated(EnumType.STRING)
    @Column(name="work_mode")
    private WorkMode workMode;
}
