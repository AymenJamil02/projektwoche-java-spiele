import java.util.Scanner;

/**
 * Spielfeld fuer Solitaer. '#' = Pin, 'O' = leeres Loch.
 */
public class Spielfeld {

    private char[][] feld;
    private int n;

    public Spielfeld(int n) {
        this.n = n;
        feld = new char[n][n];
    }

    /** Alle Felder mit Pins belegen, nur die Mitte bleibt leer. */
    public void starteSpiel() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                feld[i][j] = '#';
        feld[n / 2][n / 2] = 'O';
    }

    public void ausgabeSpielfeld() {
        System.out.print("  ");
        for (int j = 1; j <= n; j++) System.out.print(" " + j);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < n; j++) System.out.print(" " + feld[i][j]);
            System.out.println();
        }
    }

    /** Fragt Start- und Zielfeld ab und fuehrt den Zug aus, wenn er erlaubt ist. */
    public void zug(Scanner sc) {
        while (true) {
            System.out.print("Pin waehlen - Zeile Spalte: ");
            int vz = sc.nextInt() - 1, vs = sc.nextInt() - 1;
            System.out.print("Leeres Zielfeld - Zeile Spalte: ");
            int nz = sc.nextInt() - 1, ns = sc.nextInt() - 1;

            if (istErlaubt(vz, vs, nz, ns)) {
                feld[vz][vs] = 'O';
                feld[(vz + nz) / 2][(vs + ns) / 2] = 'O';   // uebersprungener Pin
                feld[nz][ns] = '#';
                return;
            }
            System.out.println("Dieser Zug ist nicht erlaubt.");
        }
    }

    /** Erlaubt: Start ist Pin, Ziel ist leer, genau 2 Felder gerade entfernt, dazwischen ein Pin. */
    private boolean istErlaubt(int vz, int vs, int nz, int ns) {
        if (!imFeld(vz, vs) || !imFeld(nz, ns)) return false;
        if (feld[vz][vs] != '#' || feld[nz][ns] != 'O') return false;
        int dz = Math.abs(nz - vz), ds = Math.abs(ns - vs);
        if (!((dz == 2 && ds == 0) || (dz == 0 && ds == 2))) return false;
        return feld[(vz + nz) / 2][(vs + ns) / 2] == '#';
    }

    private boolean imFeld(int z, int s) {
        return z >= 0 && z < n && s >= 0 && s < n;
    }

    public boolean zugMoeglich() {
        int[][] richtungen = { { 2, 0 }, { -2, 0 }, { 0, 2 }, { 0, -2 } };
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int[] r : richtungen)
                    if (istErlaubt(i, j, i + r[0], j + r[1])) return true;
        return false;
    }

    public int anzahlPins() {
        int anzahl = 0;
        for (char[] zeile : feld)
            for (char c : zeile)
                if (c == '#') anzahl++;
        return anzahl;
    }
}
