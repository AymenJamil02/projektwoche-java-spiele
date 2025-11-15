/**
 * Einfache Strategie:
 * - behaelt alle Wuerfel mit der haeufigsten Augenzahl (bei Gleichstand die hoehere),
 * - waehlt das freie Feld mit den meisten Punkten (Chance nur als letzte Wahl).
 */
public class SpielerSchlau extends Spieler {

    public SpielerSchlau(String name) {
        super(name);
    }

    public void entscheideBehalten(int[] werte, boolean[] behalten) {
        int[] anzahl = new int[7];
        for (int w : werte) anzahl[w]++;
        int beste = 1;
        for (int a = 1; a <= 6; a++)
            if (anzahl[a] >= anzahl[beste]) beste = a;
        for (int i = 0; i < 5; i++)
            behalten[i] = (werte[i] == beste);
    }

    public int entscheideFeld(int[] werte) {
        int bestesFeld = -1, bestePunkte = -1;
        for (int p = 0; p < 14; p++) {
            if (p == Spielblock.CHANCE || !block.istFrei(0, p)) continue;
            int punkte = Spielblock.berechne(p, werte);
            if (punkte > bestePunkte) {
                bestePunkte = punkte;
                bestesFeld = p;
            }
        }
        // Chance nur nehmen, wenn sonst nichts Punkte bringt oder nichts mehr frei ist
        if (block.istFrei(0, Spielblock.CHANCE) && bestePunkte <= 0) return Spielblock.CHANCE;
        return bestesFeld;
    }
}
