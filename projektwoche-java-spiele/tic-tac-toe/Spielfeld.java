/**
 * Quadratisches Spielfeld fuer Tic-Tac-Toe.
 * Leere Felder enthalten ein Leerzeichen.
 */
public class Spielfeld {

    private char[][] feld;
    private int n;

    public Spielfeld(int n) {
        this.n = n;
        feld = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                feld[i][j] = ' ';
    }

    /** Setzt das Zeichen, falls das Feld gueltig und frei ist. */
    public boolean setze(int zeile, int spalte, char zeichen) {
        if (zeile < 0 || zeile >= n || spalte < 0 || spalte >= n) return false;
        if (feld[zeile][spalte] != ' ') return false;
        feld[zeile][spalte] = zeichen;
        return true;
    }

    public boolean istVoll() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (feld[i][j] == ' ') return false;
        return true;
    }

    /** Prueft alle Zeilen, Spalten und beide Diagonalen. */
    public boolean hatGewonnen(char z) {
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < n; i++) {
            boolean zeile = true, spalte = true;
            for (int j = 0; j < n; j++) {
                if (feld[i][j] != z) zeile = false;
                if (feld[j][i] != z) spalte = false;
            }
            if (zeile || spalte) return true;
            if (feld[i][i] != z) diag1 = false;
            if (feld[i][n - 1 - i] != z) diag2 = false;
        }
        return diag1 || diag2;
    }

    public void ausgeben() {
        System.out.print("   ");
        for (int j = 1; j <= n; j++) System.out.print(" " + j + "  ");
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < n; j++) System.out.print(" " + feld[i][j] + " |");
            System.out.println();
        }
    }
}
