package no.hib.dat102;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Stige extends Rute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
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
