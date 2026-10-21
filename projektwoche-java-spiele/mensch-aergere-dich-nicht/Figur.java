package menschaergeredichnicht;

public class Figur {
	protected boolean istImSpiel;
	protected boolean istZuHause;
	protected int position = -1;
	protected int gezogeneSchritte;
	private Spieler meinSpieler;
	private int lfdnr;
	private Spielfeld sf;
	
		
	Figur(Spieler spieler, int lnr){
		meinSpieler = spieler;
		lfdnr = lnr;
		sf = meinSpieler.getSpielfeld();
	}
	
	public void wirdGeschlagen() {
		sf.nimmVomFeld(this);
		istImSpiel = false;
		position = -1;
		gezogeneSchritte = 0;
		
	}
	
	// setzt Figur auf das Spielfeld
	public void geht_raus() {
		istImSpiel = true;
		position = 15 * meinSpieler.getNr();
		sf.setzeAufPos(this);
		}
	
	public void ziehe (int augen) {
		sf.nimmVomFeld(this);
		position = (position + augen) % sf.anzahlFelder();
		gezogeneSchritte += augen;
		if(gezogeneSchritte > sf.anzahlFelder()) {
			sf.nimmVomFeld(this);
			istZuHause = true;
			position = -1;
		} else { 
			sf.setzeAufPos(this); 
		}
	}
	
	public String toString() {
		return ""+ (char)('A' + meinSpieler.getNr()) + lfdnr;
	}
	
	
}
