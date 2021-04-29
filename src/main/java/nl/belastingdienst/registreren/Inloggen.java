package nl.belastingdienst.registreren;

import org.w3c.dom.ls.LSOutput;

import javax.persistence.TypedQuery;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static nl.belastingdienst.registreren.Registratie.prompt;
import static nl.belastingdienst.registreren.RegistratieApp.em;

public class Inloggen implements Boundary {
    private static Inloggen self;

    private Inloggen() {
    }

    public static synchronized Inloggen inloggenGebruiker() {
        if (self == null) self = new Inloggen();
        return self;
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void start() {
        try {
            System.out.println("(1) [Logingegevens invullen]");
            System.out.println("(x) [Terug]");

            switch (readLine()) {
                case "1":
                    search();
                    break;
                case "x":
                    new StartScherm().start();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
    }

    private void search() {
//        while (true) {
            String eAdres = prompt("e-Mailadres: ");
            String wachtwoord = prompt("Wachtwoord: ");

            TypedQuery<Gebruiker> e = em.createQuery("SELECT e FROM Gebruiker e WHERE e.emailadres = :eAdres ", Gebruiker.class).setParameter("eAdres", eAdres);
            if (e.getSingleResult().getWachtwoord().equals(wachtwoord) && e.getSingleResult().getEmailadres().equals(eAdres)) {
                System.out.println("\nINLOGGEN GELUKT!");
                System.out.println("\nWelkom op MP, koop/verkoopze!");
            } else {
                System.out.println("Combinatie e-Mailadres + wachtwoord is NIET correct, probeer opnieuw.");
                search();
            }
        }
    }
//}

