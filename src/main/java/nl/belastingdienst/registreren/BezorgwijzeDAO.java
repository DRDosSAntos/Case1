package nl.belastingdienst.registreren;

import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import static nl.belastingdienst.registreren.RegistratieApp.em;

public class BezorgwijzeDAO {

   public void initData(){

       Bezorgwijze afhalen = new Bezorgwijze();
       afhalen.setId(1);
       afhalen.setNaam("Afhalen");
       persist(afhalen);

       Bezorgwijze verzenden = new Bezorgwijze();
       verzenden.setId(2);
       verzenden.setNaam("Verzenden");
       persist(verzenden);

       Bezorgwijze magazijn = new Bezorgwijze();
       magazijn.setId(3);
       magazijn.setNaam("Magazijn");
       persist(magazijn);

       Bezorgwijze rembours = new Bezorgwijze();
       rembours.setId(4);
       rembours.setNaam("Rembours");
       persist(rembours);

   }

    private void persist(Bezorgwijze b) {
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
    }
}
