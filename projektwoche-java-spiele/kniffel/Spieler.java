import java.util.Random;

/**
 * Abstrakter Kniffel-Spieler mit fuenf Wuerfeln und eigenem Spielblock.
 * Unterklassen legen fest, welche Wuerfel behalten werden und welches Feld gewaehlt wird.
 */
public abstract class Spieler {

    protected static Random r = new Random();

    private String name;
    protected Spielblock block = new Spielblock(1);   // eine Spalte = ein Spiel
    protected int[] werte = new int[5];

    public Spieler(String name) {
        this.name = name;
    }

    /** Bis zu dreimal wuerfeln, dazwischen Wuerfel behalten, dann ein Feld waehlen. */
    public void zugAusfuehren() {
        boolean[] behalten = new boolean[5];
        for (int wurf = 1; wurf <= 3; wurf++) {
            for (int i = 0; i < 5; i++)
                if (!behalten[i]) werte[i] = r.nextInt(6) + 1;
            if (wurf < 3) entscheideBehalten(werte, behalten);
        }
        int feld = entscheideFeld(werte);
        block.setWert(0, feld, werte);
    }

    public abstract void entscheideBehalten(int[] werte, boolean[] behalten);

    public abstract int entscheideFeld(int[] werte);

    public String getName() { return name; }
    public Spielblock getBlock() { return block; }

    public void ausgeben() {
        System.out.println("=== " + name + " ===");
        block.ausgeben();
    }
}
