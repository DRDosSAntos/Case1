package nl.belastingdienst.registreren;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Gebruiker {
    @Id
    private long userID;

    private String name;
    private String emailAddress;
    private String address;


}

