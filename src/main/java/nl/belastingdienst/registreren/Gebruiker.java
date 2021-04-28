package nl.belastingdienst.registreren;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Gebruiker {
    @Id @GeneratedValue
    private long userID;

    private String naam;
    private String emailadres;
    private String adres;
    private String wachtwoord;

    @ManyToMany
    private List<Bezorgwijze> bezorgwijzes = new ArrayList<>();

    public void addBezorgwijze(Bezorgwijze b){
        this.bezorgwijzes.add(b);
    }

}

