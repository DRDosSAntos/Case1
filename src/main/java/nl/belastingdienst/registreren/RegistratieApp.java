package nl.belastingdienst.registreren;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class RegistratieApp {

    public static final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-case1").createEntityManager();

    public static void main(String[] args) {

        BezorgwijzeDAO bezorgwijzeDAO = new BezorgwijzeDAO();
        bezorgwijzeDAO.initData();

        new StartScherm().start();
    }
}


