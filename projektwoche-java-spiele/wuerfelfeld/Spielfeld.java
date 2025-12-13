/**
 * n x n Feld, das mit Wuerfelzahlen gefuellt wird. 0 bedeutet frei.
 */
public class Spielfeld {

    private int[][] feld;
    private int n;

    public Spielfeld(int n) {
        this.n = n;
        feld = new int[n][n];
    }

    public boolean istFrei(int z, int s) {
        return feld[z][s] == 0;
    }

    public void setze(int z, int s, int wert) {
        feld[z][s] = wert;
    }

    /**
     * Wertung:
     * - gerade Summe der Hauptdiagonale: 2 Punkte
     * - jede Zeilensumme >= 2n: 1 Punkt
     * - jede Spaltensumme < 4n: 1 Punkt
     */
    public int punkte() {
        int punkte = 0;
        int diag = 0;
        for (int i = 0; i < n; i++) {
            diag += feld[i][i];
            int zeile = 0, spalte = 0;
            for (int j = 0; j < n; j++) {
                zeile += feld[i][j];
                spalte += feld[j][i];
            }
            if (zeile >= 2 * n) punkte++;
            if (spalte < 4 * n) punkte++;
        }
        if (diag % 2 == 0) punkte += 2;
        return punkte;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += (feld[i][j] == 0 ? "." : "" + feld[i][j]) + " ";
            }
            res += "\n";
        }
        return res;
    }
}
