package no.hib.dat102;

public class Stige extends Rute {
	private int ladderDestination;
	public Stige(int rutenummer, int stigeMaal) {
		super(rutenummer);
		this.ladderDestination = stigeMaal;
	}
	@Override
	public int getDestinationIndex(){
		return ladderDestination;
	}
	public void setDestinationIndex(int ladderDestinationIndex) {
		this.ladderDestination = ladderDestinationIndex;
	}
	public int move() {
		System.out.println("Du landet på en stige! Spenstig! Klatre opp til rute " + ladderDestination);
		return getDestinationIndex();
	}
}
