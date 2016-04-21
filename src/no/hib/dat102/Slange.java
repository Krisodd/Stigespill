package no.hib.dat102;

public class Slange extends Rute {
	private int snakeDestination;
	public Slange(int rutenummer, int slangeMaal) {
		super(rutenummer);
		this.snakeDestination = slangeMaal;
	}
	public int getDestinationIndex(){
		return snakeDestination;
	}
	public void setDestinationIndex(int ladderDestinationIndex) {
		this.snakeDestination = ladderDestinationIndex;
	}
}
