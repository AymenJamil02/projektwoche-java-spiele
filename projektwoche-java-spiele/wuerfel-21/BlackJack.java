import java.util.Random;
import java.util.Scanner;

/**
 * Wuerfel-21 / Black Jack (Projektwoche Mai 2024).
 * Wer zuerst 100 Punkte im Gesamtstand hat, gewinnt.
 */
public class BlackJack {

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
            System.out.println("21 / Black Jack");
            System.out.println("1 - Ein Spieler");
            System.out.println("2 - Zwei Spieler");
            System.out.println("3 - Programm beenden");
            wahl = sc.nextInt();
        } while (wahl < 1 || wahl > 3);
        return wahl;
    }

    private static void spielen(Spieler s1, Spieler s2) {
        int runde = 1;
        while (s1.getGesamt() < 100 && s2.getGesamt() < 100) {
            System.out.println("--- Runde " + runde + " ---");
            // Spieler beginnen abwechselnd
            Spieler erster = (runde % 2 == 1) ? s1 : s2;
            Spieler zweiter = (erster == s1) ? s2 : s1;

            int p1 = rundenPunkte(wuerfeln(erster));
            int p2 = rundenPunkte(wuerfeln(zweiter));
            System.out.println(erster.getName() + ": " + p1 + " Punkte, " + zweiter.getName() + ": " + p2 + " Punkte");

            if (p1 > p2) erster.addPunkte(p1);
            else if (p2 > p1) zweiter.addPunkte(p2);
            else System.out.println("Gleichstand - keine Punkte.");

            System.out.println("Gesamt: " + s1 + ", " + s2);
            runde++;
        }
        Spieler sieger = (s1.getGesamt() >= 100) ? s1 : s2;
        System.out.println(sieger.getName() + " hat gewonnen!");
    }

    /** Ein Spieler wuerfelt so lange er will und gibt seine Augensumme zurueck. */
    private static int wuerfeln(Spieler s) {
        int summe = 0;
        System.out.println(s.getName() + " ist an der Reihe.");
        while (true) {
            int wurf = r.nextInt(6) + 1;
            summe += wurf;
            System.out.println("Gewuerfelt: " + wurf + " - Summe: " + summe);
            if (summe > 21) break;

            boolean weiter;
            if (s.istComputer()) {
                weiter = summe < 18;   // Computer wuerfelt bis mindestens 18
            } else {
                System.out.println("Nochmal wuerfeln? (j/n)");
                weiter = sc.next().charAt(0) == 'j';
            }
            if (!weiter) break;
        }
        return summe;
    }

    /** Wertung: ueber 21 -> 0, genau 21 -> 42, sonst die Summe. */
    private static int rundenPunkte(int summe) {
        if (summe > 21) return 0;
        if (summe == 21) return 42;
        return summe;
    }
}
