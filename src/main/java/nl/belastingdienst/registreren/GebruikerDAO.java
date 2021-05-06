package nl.belastingdienst.registreren;

import static nl.belastingdienst.registreren.RegistratieApp.em;

public class GebruikerDAO {

    //Hier query's verzamelen, hier creeren als methode en bij de inlogscherm bijv. aanroepen,
    // handig om bij tests te injecteren

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
