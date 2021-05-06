package nl.belastingdienst.registreren;

import javax.persistence.NoResultException;
import java.util.NoSuchElementException;

import static nl.belastingdienst.registreren.RegistratieApp.em;
import static nl.belastingdienst.registreren.Util.prompt;

public class Inloggen implements Boundary {
    private static Inloggen self;

    private Inloggen() {
    }

    public static synchronized Inloggen inloggenGebruiker() {
        if (self == null) self = new Inloggen();
        return self;
    }

    Gebruiker g = new Gebruiker();

    public void start() {

        while (true) {
            try {
                System.out.println("(1) \uD83D\uDCE7 [Logingegevens invullen]");
                System.out.println("(x) \uD83D\uDD19 [Terug]");

                switch (new Util().readLine()) {
                    case "1":
                        search();
                        break;
                    case "x":
                        return;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Ongeldige keuze, probeer opnieuw");
            }
        }

    }

    private void search() {

        String eAdres = prompt("e-Mailadres: ");
        String wachtwoord = prompt("Wachtwoord: ");

        try {
            g = em.createQuery("SELECT e FROM Gebruiker e WHERE e.emailadres = :eAdres ", Gebruiker.class)
                    .setParameter("eAdres", eAdres).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Gebruiker niet gevonden, probeer opnieuw");
        }

        try {
            if (g != null && g.getWachtwoord().equals(wachtwoord) && g.getEmailadres().equals(eAdres)) {
                System.out.println("\n[INLOGGEN GELUKT]");
                System.out.println("\nWelkom op MP!\n");

                ingelogd();

            } else {
                System.out.println("Combinatie e-Mailadres + wachtwoord is NIET correct, probeer opnieuw");
            }
        } catch (NullPointerException e) {
            new StartScherm().start();
        }
    }

    //loop eromheen met conditie, bij x stopt de loop anders komt hij (while loop)
    public void ingelogd() {
        while (true) {
            try {
                System.out.println("(1) [Product zoeken]");
                System.out.println("(2) [Product aanbieden]");
                System.out.println("(x) [Uitloggen]");

                switch (new Util().readLine()) {
                    case "1":
                        System.out.println("\n[ZOEKFUNCTIONALITEIT IS NOG NIET BESCHIKBAAR]\n\n--EINDE DEMO--\n");
                        break;
                    case "2":
                        System.out.println("\n[DEZE FUNCTIONALITEIT IS NOG NIET BESCHIKBAAR]\n\n--EINDE DEMO--\n");
                        break;
                    case "x":
                        System.out.println("\n[U BENT UITGELOGD]\n");
                        new StartScherm().start();
                        break;
                }
                }catch(NoSuchElementException e){
                System.out.println("Ongeldige keuze, probeer opnieuw");
            }
        }
    }
}



