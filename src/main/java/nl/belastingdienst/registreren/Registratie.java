package nl.belastingdienst.registreren;

import java.util.*;

import static nl.belastingdienst.registreren.Util.promptString;
import static nl.belastingdienst.registreren.Util.promptInt;

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
            System.out.println("(1) \uD83D\uDC64 [Registratiegegevens invullen]");
            System.out.println("(x) \uD83D\uDD19 [Terug]");

            switch (readLine()) {
                case "1":
                    add();
                    break;
                case "x":
                    return;
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

        String naam = promptString("Naam: ");
        String emailAdres = promptString("e-Mailadres: ");
        Set<Integer> bezorgwijzeIds = new HashSet<>();

        while (true) {
            try {
                int bezorgwijzeId = promptInt("\nWelke bezorgwijze(n) kiest u: \n" +
                        "\n1 = Afhalen " +
                        "\n2 = Verzenden" +
                        "\n3 = Magazijn" +
                        "\n4 = Rembours" +
                        "\n0 = Stoppen\n");

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
            System.out.println("\nU heeft in uw bezorgwijze(n) keuzes gekozen voor 'Afhalen', vul aub ook uw huisadres in.");
            String adres = promptString("\nHuisadres: ");
            g.setAdres(adres);
        }

        registratieGegevensSetten(g, naam, emailAdres, bezorgwijzeIds);
    }

    private void registratieGegevensSetten(Gebruiker g, String naam, String emailAdres, Set<Integer> bezorgwijzeIds) {
        g.setNaam(naam);
        g.setEmailadres(emailAdres);
        g.setWachtwoord(new WachtwoordGenerator().maakEenRandomWachtwoord());


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

}
