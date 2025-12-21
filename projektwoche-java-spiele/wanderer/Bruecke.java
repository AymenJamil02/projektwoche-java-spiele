/**
 * Spielzustand: welcher Wanderer ist links, wo ist die Lampe, wie viel Zeit ist vergangen.
 * Wanderer A-D brauchen 5, 10, 20 und 25 Minuten.
 */
public class Bruecke {

    private static final int[] ZEITEN = { 5, 10, 20, 25 };

    private boolean[] links = { true, true, true, true };
    private boolean lampeLinks = true;
    private int zeit = 0;

    /** Bewegt einen oder zwei Wanderer (w2 = ' ' fuer nur einen) auf die andere Seite. */
    public void gehe(char w1, char w2) {
        int i1 = w1 - 'A';
        int dauer = ZEITEN[i1];
        links[i1] = !links[i1];
        if (w2 != ' ') {
            int i2 = w2 - 'A';
            if (ZEITEN[i2] > dauer) dauer = ZEITEN[i2];   // der Langsamere zaehlt
            links[i2] = !links[i2];
        }
        zeit += dauer;
        lampeLinks = !lampeLinks;
    }

    public boolean istLinks(char w) {
        return links[w - 'A'];
    }

    public boolean lampeLinks() {
        return lampeLinks;
    }

    public int getZeit() {
        return zeit;
    }

    public boolean istGeloest() {
        for (boolean l : links) if (l) return false;
        return zeit <= 60;
    }

    /** Liefert die Wanderer einer Seite, z.B. "A(5) C(20)". */
    public String seite(boolean linkeSeite) {
        String res = "";
        for (int i = 0; i < 4; i++)
            if (links[i] == linkeSeite) res += (char) ('A' + i) + "(" + ZEITEN[i] + ") ";
        return res;
    }
}
