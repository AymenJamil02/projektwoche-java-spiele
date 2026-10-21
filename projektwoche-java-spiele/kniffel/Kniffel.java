import java.util.Scanner;

/**
 * Vereinfachtes Kniffel (Aufgabenblatt C) mit automatischen Spielern.
 * Es spielen 2-8 Spieler, abwechselnd SpielerDumm und SpielerSchlau.
 */
public class Kniffel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int anzahl;
        do {
            System.out.print("Anzahl Spieler (2-8): ");
            anzahl = sc.nextInt();
        } while (anzahl < 2 || anzahl > 8);

        Spieler[] spieler = new Spieler[anzahl];
        for (int i = 0; i < anzahl; i++) {
            if (i % 2 == 0) spieler[i] = new SpielerDumm("Dumm-" + (i + 1));
            else spieler[i] = new SpielerSchlau("Schlau-" + (i + 1));
        }

        // 13 Runden: jedes Feld (ausser Bonus) wird genau einmal gefuellt
        for (int runde = 1; runde <= 13; runde++) {
            for (Spieler s : spieler) s.zugAusfuehren();
        }

        Spieler sieger = spieler[0];
        for (Spieler s : spieler) {
            s.ausgeben();
            if (s.getBlock().summe(0) > sieger.getBlock().summe(0)) sieger = s;
        }
        System.out.println("Gewinner: " + sieger.getName() + " mit " + sieger.getBlock().summe(0) + " Punkten");
        sc.close();
    }
}
