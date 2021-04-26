package nl.belastingdienst.registreren;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static nl.belastingdienst.registreren.RegistratieApp.em;

public class GebruikerDAO {

    public void persist(Gebruiker g) {
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
    }

    public Gebruiker findUserId(long userID) {
        return em.find(Gebruiker.class, userID);

    }
//    Hash password
//    public byte[] hash(String password) throws NoSuchAlgorithmException {
//        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
//        byte[] passBytes = password.getBytes();
//        byte[] passHash = sha256.digest(passBytes);
//        return passHash;
//    }

}
