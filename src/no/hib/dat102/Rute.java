package no.hib.dat102;

public class Rute {
	protected int rutenummer;
	public Rute(int rutenummer){ // Constructor
		this.rutenummer = rutenummer;
	}
	public Rute() {
		this.rutenummer = -1;
	}
	
	public int getRuteIndex() {
		return rutenummer;
	}
	public void setRuteIndex(int rutenummer) {
		this.rutenummer = rutenummer;
	}
	public int getDestinationIndex(){
		return -1;
	}
	public boolean isSpecialTile() {
		return this instanceof Slange || this instanceof Stige;
	}
	public int move() {
		return -1;
	}
}
