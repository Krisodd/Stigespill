package no.hib.dat102;

public class Rute {
	protected int rutenummer;
	public Rute(int rutenummer){ // Constructor
		this.rutenummer = rutenummer;
	}
	
	public int getRuteIndex() {
		return rutenummer;
	}
	public void setRuteIndex(int rutenummer) {
		this.rutenummer = rutenummer;
	}
}
