package nl.belastingdienst.registreren;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Bezorgwijze {
    @Id
    private int id;

    private String naam;

//    @ManyToMany(mappedBy = "bezorgwijzes")
//    private List<Gebruiker> gebruikers = new ArrayList<>();

}
