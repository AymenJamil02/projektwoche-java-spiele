/**
 * Spielblock mit allen Kniffel-Feldern. Leere Felder haben den Wert LEER (-1).
 * Alle Indizes sind null-basiert.
 */
public class Spielblock {

    public final static int LEER = -1;
    public final static int EINER = 0;
    public final static int ZWEIER = 1;
    public final static int DREIER = 2;
    public final static int VIERER = 3;
    public final static int FUENFER = 4;
    public final static int SECHSER = 5;
    public final static int BONUS = 6;
    public final static int DREIERPASCH = 7;
    public final static int VIERERPASCH = 8;
    public final static int FULLHOUSE = 9;
    public final static int KLEINESTRASSE = 10;
    public final static int GROSSESTRASSE = 11;
    public final static int KNIFFEL = 12;
    public final static int CHANCE = 13;

    public final static String[] NAMEN = { "Einer", "Zweier", "Dreier", "Vierer", "Fuenfer", "Sechser",
            "Bonus", "Dreierpasch", "Viererpasch", "Full House", "Kleine Strasse", "Grosse Strasse",
            "Kniffel", "Chance" };

    private int[][] block;   // [spalte][position]

    public Spielblock(int spalten) {
        block = new int[spalten][14];
        for (int s = 0; s < spalten; s++)
            for (int p = 0; p < 14; p++)
                block[s][p] = LEER;
    }

    public boolean istFrei(int spalte, int position) {
        return position != BONUS && block[spalte][position] == LEER;
    }

    /** Traegt die Punkte fuer die Wuerfel ein (0, wenn die Kombination nicht passt). */
    public boolean setWert(int spalte, int position, int[] werte) {
        if (!istFrei(spalte, position)) return false;
        block[spalte][position] = berechne(position, werte);
        pruefeBonus(spalte);
        return true;
    }

    /** Bonus wird automatisch gesetzt, sobald Einer bis Sechser ausgefuellt sind. */
    private void pruefeBonus(int spalte) {
        int summe = 0;
        for (int p = EINER; p <= SECHSER; p++) {
            if (block[spalte][p] == LEER) return;
            summe += block[spalte][p];
        }
        block[spalte][BONUS] = (summe >= 63) ? 35 : 0;
    }

    /** Berechnet die Punkte, die ein Wurf in einem Feld bringen wuerde. */
    public static int berechne(int position, int[] werte) {
        int[] anzahl = new int[7];   // anzahl[augen]
        int summe = 0;
        for (int w : werte) {
            anzahl[w]++;
            summe += w;
        }
        int max = 0;
        boolean zwei = false, drei = false;
        for (int a = 1; a <= 6; a++) {
            if (anzahl[a] > max) max = anzahl[a];
            if (anzahl[a] == 2) zwei = true;
            if (anzahl[a] == 3) drei = true;
        }
        int strasse = laengsteStrasse(anzahl);

        if (position <= SECHSER) return anzahl[position + 1] * (position + 1);
        switch (position) {
            case DREIERPASCH:   return max >= 3 ? summe : 0;
            case VIERERPASCH:   return max >= 4 ? summe : 0;
            case FULLHOUSE:     return (drei && zwei) ? 25 : 0;
            case KLEINESTRASSE: return strasse >= 4 ? 30 : 0;
            case GROSSESTRASSE: return strasse >= 5 ? 40 : 0;
            case KNIFFEL:       return max == 5 ? 50 : 0;
            case CHANCE:        return summe;
            default:            return 0;
        }
    }

    private static int laengsteStrasse(int[] anzahl) {
        int laenge = 0, best = 0;
        for (int a = 1; a <= 6; a++) {
            laenge = (anzahl[a] > 0) ? laenge + 1 : 0;
            if (laenge > best) best = laenge;
        }
        return best;
    }

    public int summe(int spalte) {
        int summe = 0;
        for (int p = 0; p < 14; p++)
            if (block[spalte][p] != LEER) summe += block[spalte][p];
        return summe;
    }

    public void ausgeben() {
        for (int p = 0; p < 14; p++) {
            String name = NAMEN[p];
            while (name.length() < 15) name += " ";
            System.out.print(name);
            for (int s = 0; s < block.length; s++)
                System.out.print(block[s][p] == LEER ? "   -" : String.format("%4d", block[s][p]));
            System.out.println();
        }
        System.out.println("Summe          " + summe(0));
    }
}
