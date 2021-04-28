package nl.belastingdienst.registreren;

import java.util.Scanner;

import static nl.belastingdienst.registreren.Registratie.registrerenGebruiker;

public class StartScherm implements Boundary {

    public void start() {

            System.out.println("----------------------------------------------");
            System.out.println("Welkom bij het startscherm!");
            System.out.println("Wat wilt u doen?");
            System.out.println("----------------------------------------------");

            System.out.println("(1) [Registreren] ");
            System.out.println("(x) [Afsluiten] ");


            try {
                switch (readLine()) {
                    case "1":
                        registrerenGebruiker().start(); break;
                    case "x":
                        System.out.println("Tot ziens."); return;
                    default:
                        System.out.println("Ongeldige keuze; probeer opnieuw."); break;
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

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

