package nl.belastingdienst.registreren;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registratie implements Boundary {

    private static Registratie self;

    private Registratie() {
    }

    public static synchronized Registratie registrerenGebruiker() {
        if (self == null) self = new Registratie();
        return self;
    }

    public void start() {
        while (true) {
            System.out.println("(1) [Naam doorgeven]");
            System.out.println("(x) [Terug]");

            switch (readLine()) {
                case "1":
                    add();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Ongeldige keuze; probeer opnieuw.");
            }
        }
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void add() {

        String naam = prompt("Naam: ");

        String emailAdres = prompt("e-Mailadres: ");
        int bezorgwijzeId = prompt2("Welke bezorgwijze(n) kiest u (1=Afhalen, 2=.., ...): ");

        Gebruiker g = new Gebruiker();
        g.setName(naam);
        g.setEmailAddress(emailAdres);

        Bezorgwijze b = new Bezorgwijze();
        b.setId(bezorgwijzeId);

        List<Bezorgwijze> bezorgwijzes = new ArrayList<>();
        bezorgwijzes.add(b);

        g.setBezorgwijzes(bezorgwijzes);

        GebruikerDAO dao1 = new GebruikerDAO();
        dao1.persist(g);


        System.out.println("Gebruiker geregistreerd!");
    }

    public static String prompt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int prompt2(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
