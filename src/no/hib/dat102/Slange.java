package no.hib.dat102;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Slange extends Rute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@ManyToOne
	@JoinColumn(name="brett", referencedColumnName="id")
	private Integer snakeDestination;
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
