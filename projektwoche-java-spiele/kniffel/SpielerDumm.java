/**
 * Spieler ohne Strategie: behaelt nie Wuerfel und waehlt ein zufaelliges freies Feld.
 */
public class SpielerDumm extends Spieler {

    public SpielerDumm(String name) {
        super(name);
    }

    public void entscheideBehalten(int[] werte, boolean[] behalten) {
        // nichts behalten -> es wird immer neu gewuerfelt
    }

    public int entscheideFeld(int[] werte) {
        int feld;
        do {
            feld = r.nextInt(14);
        } while (!block.istFrei(0, feld));
        return feld;
    }
}
