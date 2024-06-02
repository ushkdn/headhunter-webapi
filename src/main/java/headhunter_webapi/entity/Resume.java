package headhunter_webapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;
    //User owner;

    public Resume(Byte id) {
        this.id = id;
       // this.owner = owner;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

//    public User getOwner() {
//        return owner;
//    }
//
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
}
