import java.util.Random;

/**
 * Spielfeld fuer Memory: 4 x 6 Felder mit den Buchstaben A-L und a-l.
 */
public class Memory {

    public static final int ZEILEN = 4;
    public static final int SPALTEN = 6;

    private char[][] feld = new char[ZEILEN][SPALTEN];
    private boolean[][] aufgedeckt = new boolean[ZEILEN][SPALTEN];

    public Memory() {
        // 1. Alphabetisch fuellen: erst A-L (65..76), dann a-l (97..108)
        char c = 65;
        int i = 0;
        for (int z = 0; z < ZEILEN; z++) {
            for (int s = 0; s < SPALTEN; s++) {
                feld[z][s] = c;
                c++;
                i++;
                if (i == 12) c = 97;   // nach L kommen die Kleinbuchstaben
            }
        }
        // 2. Zufaellig mischen: jede Stelle mit einer zufaelligen Stelle tauschen
        Random r = new Random();
        for (int z = 0; z < ZEILEN; z++) {
            for (int s = 0; s < SPALTEN; s++) {
                int z2 = r.nextInt(ZEILEN);
                int s2 = r.nextInt(SPALTEN);
                char tmp = feld[z][s];
                feld[z][s] = feld[z2][s2];
                feld[z2][s2] = tmp;
            }
        }
    }

    /** Zwei Zeichen passen, wenn sie sich um genau 32 (Gross/Klein) unterscheiden. */
    public static boolean passen(char c1, char c2) {
        int diff = c1 - c2;
        return diff == 32 || diff == -32;
    }

    public char getZeichen(int z, int s) {
        return feld[z][s];
    }

    public boolean istGueltigUndVerdeckt(int z, int s) {
        return z >= 0 && z < ZEILEN && s >= 0 && s < SPALTEN && !aufgedeckt[z][s];
    }

    public void aufdecken(int z1, int s1, int z2, int s2) {
        aufgedeckt[z1][s1] = true;
        aufgedeckt[z2][s2] = true;
    }

    public boolean allesAufgedeckt() {
        for (int z = 0; z < ZEILEN; z++)
            for (int s = 0; s < SPALTEN; s++)
                if (!aufgedeckt[z][s]) return false;
        return true;
    }

    /** Zeigt nur die bereits gefundenen Paare an, sonst '*'. */
    public void ausgeben() {
        System.out.print("  ");
        for (int s = 1; s <= SPALTEN; s++) System.out.print(" " + s);
        System.out.println();
        for (int z = 0; z < ZEILEN; z++) {
            System.out.print((z + 1) + " ");
            for (int s = 0; s < SPALTEN; s++)
                System.out.print(" " + (aufgedeckt[z][s] ? feld[z][s] : '*'));
            System.out.println();
        }
    }
}
