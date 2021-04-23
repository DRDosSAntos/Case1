package nl.belastingdienst.registreren;

public class GebruikerStart {
    public static void main(String[] args) {
        new GebruikerStart().start();

    }

    private void start() {
        Gebruiker gebruiker = new GebruikerDAO().findUserId(1);
        System.out.println(gebruiker);

    }
}


