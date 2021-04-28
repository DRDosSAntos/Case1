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
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long userID;

    private String name;
    private String emailAddress;
    private String address;
    private String gekozenBezorgwijze;

    @ManyToMany
    private List<Bezorgwijze> bezorgwijzes = new ArrayList<>();

}

