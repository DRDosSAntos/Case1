package nl.belastingdienst.registreren;

import java.util.Scanner;

import static nl.belastingdienst.registreren.Inloggen.inloggenGebruiker;
import static nl.belastingdienst.registreren.Registratie.registrerenGebruiker;

public class StartScherm implements Boundary {

    public void start() {
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("Welkom bij het startscherm!");
            System.out.println("Wat wilt u doen?");
            System.out.println("----------------------------------------------");

            System.out.println("(1) \uD83D\uDC64 [Registreren] ");
            System.out.println("(2) \uD83D\uDCE7 [Inloggen] ");
            System.out.println("(x) \uD83D\uDD1A [Afsluiten] ");

            try {
                switch (new Util().readLine()) {
                    case "1":
                        registrerenGebruiker().start();
                        break;
                    case "2":
                        inloggenGebruiker().start();
                        break;
                    case "x":
                        System.out.println("Tot ziens.");
                        return;
                    default:
                        System.out.println("Ongeldige keuze; probeer opnieuw.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Dit is ongeldige invoer. Probeer het opnieuw.");
            } catch (RuntimeException t) {
                System.out.println("Er ging iets mis... Probeer het opnieuw. ");
                System.out.println("Foutmelding: " + t.getMessage());
                t.printStackTrace();
            } catch (Exception e) {
                System.out.println("Er ging iets vreselijk mis... ");
                System.out.println("Foutmelding: " + e.getMessage());
                System.out.println("Neem contact op met de leverancier.");
                System.out.println("Tot ziens.");
            }
        }
    }
}

