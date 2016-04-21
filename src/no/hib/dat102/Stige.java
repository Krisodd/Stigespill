package no.hib.dat102;

public class Stige extends Rute {
	private int ladderDestination;
	public Stige(int rutenummer, int stigeMaal) {
		super(rutenummer);
		this.ladderDestination = stigeMaal;
	}
	public int getDestinationIndex(){
		return ladderDestination;
	}
	public void setDestinationIndex(int ladderDestinationIndex) {
		this.ladderDestination = ladderDestinationIndex;
	}
}
