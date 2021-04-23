package nl.belastingdienst.registreren;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class GebruikerDAO {
    public static final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-case1").createEntityManager();

    public Gebruiker findUserId(long userID) {
        return em.find(Gebruiker.class, userID);

    }

//    public Gebruiker createUser(){
//
//    }

}
