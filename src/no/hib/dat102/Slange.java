package no.hib.dat102;

public class Slange extends Rute {
	private int snakeDestination;
	public Slange(int rutenummer, int slangeMaal) {
		super(rutenummer);
		this.snakeDestination = slangeMaal;
	}
	@Override
	public int getDestinationIndex(){
		return snakeDestination;
	}
	public void setDestinationIndex(int ladderDestinationIndex) {
		this.snakeDestination = ladderDestinationIndex;
	}
	public int move() {
		System.out.println("Du landet på en slange! Satans krypdyr! Du rykker ned til " + snakeDestination);
		return getDestinationIndex();
	}
}
