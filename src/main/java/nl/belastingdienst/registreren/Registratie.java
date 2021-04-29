package nl.belastingdienst.registreren;

import java.util.*;

public class Registratie implements Boundary {

    private static Registratie self;

    private Registratie() {
    }

    public static synchronized Registratie registrerenGebruiker() {
        if (self == null) self = new Registratie();
        return self;
    }

    public void start() {

        try {
            System.out.println("(1) [Registratiegegevens invullen]");
            System.out.println("(x) [Terug]");

            switch (readLine()) {
                case "1":
                    add();
                    break;
                case "x":
                    new StartScherm().start();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
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
        Set<Integer> bezorgwijzeIds = new HashSet<>();

        while (true) {
            try {
                int bezorgwijzeId = prompt2("Welke bezorgwijze(n) kiest u: " +
                        "\n1 = Afhalen " +
                        "\n2 = Verzenden" +
                        "\n3 = Magazijn" +
                        "\n4 = Rembours" +
                        "\n0 = Stoppen");

                if (bezorgwijzeId == 0) {
                    break;
                } else if(!(bezorgwijzeId>=0 && bezorgwijzeId <= 4)){
                    System.out.println("Ongeldige keuze, kies uit de gegeven opties: 1/2/3/4");
                } else {
                    bezorgwijzeIds.add(bezorgwijzeId);
                }
            } catch (InputMismatchException e) {
                System.out.println("Dit is ongeldige invoer. Probeer het opnieuw.");
            }
        }

        if (bezorgwijzeIds.contains(1)) {
            System.out.println("U heeft in uw bezorgwijze(n) keuzes gekozen voor 'Afhalen', vul aub ook uw huisadres in.");
            String adres = prompt("\nHuisadres: ");
            g.setAdres(adres);
        }

        registratieGegevensSetten(g, naam, emailAdres, bezorgwijzeIds);
    }

    private void registratieGegevensSetten(Gebruiker g, String naam, String emailAdres, Set<Integer> bezorgwijzeIds) {
        g.setNaam(naam);
        g.setEmailadres(emailAdres);
        g.setWachtwoord("Welkom123");


        for (Integer bezorgwijzeId : bezorgwijzeIds) {
            Bezorgwijze b = new Bezorgwijze();
            b.setId(bezorgwijzeId);
            g.addBezorgwijze(b);
        }

        GebruikerDAO dao1 = new GebruikerDAO();
        dao1.persist(g);

        System.out.println("\nREGISTREREN GELUKT!");
        System.out.println("\nUW WACHTWOORD IS: " + g.getWachtwoord());
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
