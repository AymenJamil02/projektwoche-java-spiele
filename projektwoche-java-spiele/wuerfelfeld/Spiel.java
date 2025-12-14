import java.util.Random;
import java.util.Scanner;

/**
 * Wuerfelfeld (Projektwoche Januar 2025, Variante A).
 * Ein- und Ausgaben finden nur in dieser Klasse statt.
 */
public class Spiel {

    private static Scanner sc = new Scanner(System.in);
    private static Random r = new Random();

    public static void main(String[] args) {
        int wahl;
        do {
            wahl = leseZahl("1 - Ein neues Spiel starten\n2 - Programm beenden", 1, 2);
            if (wahl == 1) spielen();
        } while (wahl != 2);
    }

    private static void spielen() {
        System.out.println("Name von Spieler 1:");
        String name1 = sc.next();
        System.out.println("Name von Spieler 2:");
        String name2 = sc.next();
        int n = leseZahl("Groesse n des Spielfelds (n x n, 2-9):", 2, 9);

        Spieler[] spieler = { new Spieler(name1, n), new Spieler(name2, n) };

        // n*n Zuege pro Spieler, abwechselnd
        for (int zug = 0; zug < n * n; zug++) {
            for (Spieler s : spieler) {
                System.out.println(s.getName() + " ist an der Reihe.");
                System.out.println(s.getFeld());
                int wurf = r.nextInt(6) + 1;
                System.out.println("Gewuerfelt: " + wurf);

                int z, sp;
                do {
                    z = leseZahl("Zeile (1-" + n + "):", 1, n) - 1;
                    sp = leseZahl("Spalte (1-" + n + "):", 1, n) - 1;
                    if (!s.getFeld().istFrei(z, sp)) System.out.println("Feld ist schon belegt.");
                } while (!s.getFeld().istFrei(z, sp));
                s.getFeld().setze(z, sp, wurf);
            }
        }

        // Auswertung
        for (Spieler s : spieler) {
            System.out.println(s.getName() + ":");
            System.out.println(s.getFeld());
            System.out.println("Punkte: " + s.getFeld().punkte());
        }
        int p1 = spieler[0].getFeld().punkte();
        int p2 = spieler[1].getFeld().punkte();
        if (p1 > p2) System.out.println(name1 + " hat gewonnen!");
        else if (p2 > p1) System.out.println(name2 + " hat gewonnen!");
        else System.out.println("Unentschieden!");
    }

    /** Liest so lange eine Zahl ein, bis sie zwischen min und max liegt. */
    private static int leseZahl(String frage, int min, int max) {
        while (true) {
            System.out.println(frage);
            if (sc.hasNextInt()) {
                int x = sc.nextInt();
                if (x >= min && x <= max) return x;
            } else {
                sc.next(); // falsche Eingabe verwerfen
            }
            System.out.println("Ungueltige Eingabe.");
        }
    }
}
