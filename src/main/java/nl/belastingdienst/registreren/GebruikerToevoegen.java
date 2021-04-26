package nl.belastingdienst.registreren;

import java.util.Scanner;

public class GebruikerToevoegen implements Boundary {

    private static GebruikerToevoegen self;

    private GebruikerToevoegen() {
    }

    public static synchronized GebruikerToevoegen registrerenGebruiker() {
        if (self == null) self = new GebruikerToevoegen();
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
        Gebruiker g = new Gebruiker();
        g.setName(naam);
        g.setEmailAddress(emailAdres);

        GebruikerDAO dao1 = new GebruikerDAO();
        dao1.persist(g);


        System.out.println("Gebruiker geregistreerd!");
    }

    public static String prompt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
