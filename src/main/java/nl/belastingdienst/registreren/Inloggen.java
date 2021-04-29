package nl.belastingdienst.registreren;

import org.w3c.dom.ls.LSOutput;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.InputMismatchException;
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
        while (true) {
            try {
                System.out.println("(1) [Logingegevens invullen]");
                System.out.println("(x) [Terug]");

                switch (readLine()) {
                    case "1":
                        search();
                        break;
                    case "x":
                        return;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    private void search() {

        String eAdres = prompt("e-Mailadres: ");
        String wachtwoord = prompt("Wachtwoord: ");

        try {
            Gebruiker g = em.createQuery("SELECT e FROM Gebruiker e WHERE e.emailadres = :eAdres ", Gebruiker.class)
                    .setParameter("eAdres", eAdres).getSingleResult();

            if (g.getWachtwoord().equals(wachtwoord) && g.getEmailadres().equals(eAdres)) {
                System.out.println("\nINLOGGEN GELUKT!");
                System.out.println("\nWelkom op MP, koop/verkoopze!");
            } else {
                System.out.println("Combinatie e-Mailadres + wachtwoord is NIET correct, probeer opnieuw.");
//                search();
            }
        } catch (NoResultException e) {
            System.out.println("Gebruiker niet gevonden, probeer opnieuw");
//            search();
        }
    }
}


