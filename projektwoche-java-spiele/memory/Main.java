import java.util.Scanner;

/**
 * Memory (Projektwoche Januar 2024): Menue und Spielablauf.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int wahl;
        do {
            System.out.println("MEMORY");
            System.out.println("1 - Ein neues Spiel starten");
            System.out.println("2 - Programm beenden");
            wahl = sc.nextInt();
            if (wahl == 1) {
                spielen(sc);
            }
        } while (wahl != 2);
        sc.close();
    }

    private static void spielen(Scanner sc) {
        System.out.println("Geben Sie den Namen von Spieler 1 ein:");
        Spieler s1 = new Spieler(sc.next());
        System.out.println("Geben Sie den Namen von Spieler 2 ein:");
        Spieler s2 = new Spieler(sc.next());

        Memory memory = new Memory();
        Spieler aktuell = s1;

        while (!memory.allesAufgedeckt()) {
            System.out.println(aktuell.getName() + " ist an der Reihe.");
            memory.ausgeben();

            int[] p1 = frageStelle(sc, memory);
            char c1 = memory.getZeichen(p1[0], p1[1]);
            System.out.println("An der Stelle befindet sich ein " + c1 + ".");

            int[] p2 = frageStelle(sc, memory);
            while (p2[0] == p1[0] && p2[1] == p1[1]) {
                System.out.println("Bitte eine andere Stelle waehlen.");
                p2 = frageStelle(sc, memory);
            }
            char c2 = memory.getZeichen(p2[0], p2[1]);
            System.out.println("An der Stelle befindet sich ein " + c2 + ".");

            if (Memory.passen(c1, c2)) {
                memory.aufdecken(p1[0], p1[1], p2[0], p2[1]);
                aktuell.punkt();
                System.out.println("Passt! " + aktuell.getName() + " ist erneut an der Reihe.");
            } else {
                System.out.println("Passt leider nicht.");
                aktuell = (aktuell == s1) ? s2 : s1;
            }
            System.out.println("Der aktuelle Spielstand lautet:");
            System.out.println(s1);
            System.out.println(s2);
        }

        if (s1.getPunkte() > s2.getPunkte())
            System.out.println(s1.getName() + " hat dieses Spiel gewonnen!");
        else if (s2.getPunkte() > s1.getPunkte())
            System.out.println(s2.getName() + " hat dieses Spiel gewonnen!");
        else
            System.out.println("Das Spiel endet unentschieden!");
    }

    /** Liest Zeile und Spalte (1-basiert) ein, bis eine noch verdeckte Stelle gewaehlt wurde. */
    private static int[] frageStelle(Scanner sc, Memory memory) {
        while (true) {
            System.out.println("Zeile " + "(1-" + Memory.ZEILEN + "):");
            int z = sc.nextInt() - 1;
            System.out.println("Spalte " + "(1-" + Memory.SPALTEN + "):");
            int s = sc.nextInt() - 1;
            if (memory.istGueltigUndVerdeckt(z, s)) return new int[] { z, s };
            System.out.println("Ungueltige oder bereits aufgedeckte Stelle.");
        }
    }
}
