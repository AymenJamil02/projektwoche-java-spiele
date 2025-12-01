import java.util.Random;
import java.util.Scanner;

/**
 * Zahlen raten (Projektwoche Juli 2024).
 * Wer zuerst 5 Punkte im Gesamtstand hat, gewinnt.
 */
public class ZahlenRaten {

    private static Scanner sc = new Scanner(System.in);
    private static Random r = new Random();

    public static void main(String[] args) {
        int wahl;
        do {
            wahl = menue();
            if (wahl == 1) {
                System.out.println("Name des Spielers:");
                spielen(new Spieler(sc.next(), false), new Spieler("Computer", true));
            } else if (wahl == 2) {
                System.out.println("Name von Spieler 1:");
                Spieler s1 = new Spieler(sc.next(), false);
                System.out.println("Name von Spieler 2:");
                Spieler s2 = new Spieler(sc.next(), false);
                spielen(s1, s2);
            }
        } while (wahl != 3);
    }

    private static int menue() {
        int wahl;
        do {
            System.out.println("Zahlen raten");
            System.out.println("1 - Ein Spieler");
            System.out.println("2 - Zwei Spieler");
            System.out.println("3 - Programm beenden");
            wahl = sc.nextInt();
        } while (wahl < 1 || wahl > 3);
        return wahl;
    }

    private static void spielen(Spieler s1, Spieler s2) {
        int runde = 1;
        while (s1.getGesamt() < 5 && s2.getGesamt() < 5) {
            System.out.println("--- Runde " + runde + " ---");
            int a = r.nextInt(101);
            int b = r.nextInt(101);
            int min = (a < b) ? a : b;
            int max = (a < b) ? b : a;
            System.out.println("min = " + min + ", max = " + max);

            Spieler erster = (runde % 2 == 1) ? s1 : s2;
            Spieler zweiter = (erster == s1) ? s2 : s1;
            int tipp1 = frageTipp(erster);
            int tipp2 = frageTipp(zweiter);

            int zahl = r.nextInt(101);
            int ergebnis = kategorie(zahl, min, max);
            System.out.println("Die neue Zahl ist " + zahl + " (Kategorie " + ergebnis + ").");

            int p1 = punkte(tipp1, ergebnis);
            int p2 = punkte(tipp2, ergebnis);
            System.out.println(erster.getName() + ": " + p1 + " Punkte, " + zweiter.getName() + ": " + p2 + " Punkte");

            if (p1 > p2) erster.addPunkte(p1);
            else if (p2 > p1) zweiter.addPunkte(p2);
            else System.out.println("Gleichstand - keine Punkte.");

            System.out.println("Gesamt: " + s1 + ", " + s2);
            runde++;
        }
        Spieler sieger = (s1.getGesamt() >= 5) ? s1 : s2;
        System.out.println(sieger.getName() + " hat gewonnen!");
    }

    private static int frageTipp(Spieler s) {
        if (s.istComputer()) {
            int tipp = r.nextInt(5);
            System.out.println(s.getName() + " waehlt " + tipp + ".");
            return tipp;
        }
        int tipp;
        do {
            System.out.println(s.getName() + ", die naechste Zahl ist ...");
            System.out.println("0 - kleiner als min, 1 - genau min, 2 - zwischen min und max, 3 - genau max, 4 - groesser als max");
            tipp = sc.nextInt();
        } while (tipp < 0 || tipp > 4);
        return tipp;
    }

    /** Ordnet die Zahl einer der Kategorien 0-4 zu. */
    private static int kategorie(int zahl, int min, int max) {
        if (zahl < min) return 0;
        if (zahl == min) return 1;
        if (zahl == max) return 3;
        if (zahl > max) return 4;
        return 2;
    }

    /** 0/2/4 richtig -> 1 Punkt, 1/3 richtig -> 3 Punkte, falsch -> 0. */
    private static int punkte(int tipp, int ergebnis) {
        if (tipp != ergebnis) return 0;
        return (tipp == 1 || tipp == 3) ? 3 : 1;
    }
}
