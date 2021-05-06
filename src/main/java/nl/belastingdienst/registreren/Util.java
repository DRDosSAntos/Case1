package nl.belastingdienst.registreren;

import java.util.Scanner;

public class Util {
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String promptString(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int promptInt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
