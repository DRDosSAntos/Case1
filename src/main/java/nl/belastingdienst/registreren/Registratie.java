package nl.belastingdienst.registreren;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registratie implements Boundary {

    private String[] bezorgwijzes = {"Afhalen", "Verzenden", "Magazijn", "Rembours"};

    private static Registratie self;

    private Registratie() {
    }

    public static synchronized Registratie registrerenGebruiker() {
        if (self == null) self = new Registratie();
        return self;
    }

    public void start() {
        while (true) {
            System.out.println("(1) [Registratiegegevens invullen]");
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
        Gebruiker g = new Gebruiker();

        String naam = prompt("Naam: ");
        String emailAdres = prompt("e-Mailadres: ");
        int bezorgwijzeId = prompt2("Welke bezorgwijze(n) kiest u: " +
                "\n1= Afhalen " +
                "\n2=Verzenden" +
                "\n3=Magazijn" +
                "\n4=Rembours");
        if (bezorgwijzeId == 1) {
            System.out.println("U heeft gekozen voor 'Afhalen', vul aub ook uw huisadres in.");
            String adres = prompt("Huisadres: ");
            g.setAddress(adres);
            naamZetten(g, naam, emailAdres, bezorgwijzeId);
        } else {

            naamZetten(g, naam, emailAdres, bezorgwijzeId);
        }
    }

    private void naamZetten(Gebruiker g, String naam, String emailAdres, int bezorgwijzeId) {
        g.setName(naam);
        g.setEmailAddress(emailAdres);
        g.setGekozenBezorgwijze(bezorgwijzes[bezorgwijzeId - 1]);


        Bezorgwijze b = new Bezorgwijze();
        b.setId(bezorgwijzeId);

        List<Bezorgwijze> bezorgwijzes = new ArrayList<>();
        bezorgwijzes.add(b);

        g.setBezorgwijzes(bezorgwijzes);

        GebruikerDAO dao1 = new GebruikerDAO();
        dao1.persist(g);

        System.out.println("Gebruiker geregistreerd!");
    }


    private void add2() {
        int bezorgwijzeId2 = prompt2("Welke bezorgwijze(n) kiest u: " +
                "\n1= Afhalen " +
                "\n2=Verzenden" +
                "\n3=Magazijn" +
                "\n4=Rembours");
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
