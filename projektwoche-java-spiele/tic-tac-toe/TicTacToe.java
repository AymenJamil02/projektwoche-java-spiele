import java.util.Scanner;

/**
 * Tic-Tac-Toe fuer zwei Spieler auf der Konsole.
 * Die Spielfeldgroesse ist variabel (n x n).
 */
public class TicTacToe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int siege1 = 0, siege2 = 0;   // optionale Statistik
        String nochmal;

        do {
            System.out.print("Spielfeldgroesse (z.B. 3): ");
            int n = sc.nextInt();
            while (n < 3) {
                System.out.print("Mindestens 3. Neue Eingabe: ");
                n = sc.nextInt();
            }

            Spielfeld feld = new Spielfeld(n);
            char spieler = 'X';
            boolean gewonnen = false;

            while (!gewonnen && !feld.istVoll()) {
                feld.ausgeben();
                System.out.println("Spieler " + spieler + " ist am Zug.");
                System.out.print("Zeile (1-" + n + "): ");
                int zeile = sc.nextInt() - 1;
                System.out.print("Spalte (1-" + n + "): ");
                int spalte = sc.nextInt() - 1;

                if (!feld.setze(zeile, spalte, spieler)) {
                    System.out.println("Ungueltiger Zug, bitte nochmal.");
                    continue;
                }
                gewonnen = feld.hatGewonnen(spieler);
                if (!gewonnen) {
                    spieler = (spieler == 'X') ? 'O' : 'X';
                }
            }

            feld.ausgeben();
            if (gewonnen) {
                System.out.println("Spieler " + spieler + " hat gewonnen!");
                if (spieler == 'X') siege1++; else siege2++;
            } else {
                System.out.println("Unentschieden!");
            }
            System.out.println("Statistik: X = " + siege1 + " Siege, O = " + siege2 + " Siege");

            System.out.print("Nochmal spielen? (j/n): ");
            nochmal = sc.next();
        } while (nochmal.equals("j"));

        sc.close();
    }
}
