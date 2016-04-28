package no.hib.dat102;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity(name="slange")
@Table(name="slange", schema="stigespill")
public class Slange extends Rute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column
	private Integer til;
	@Column
	private Integer fra;
	@ManyToOne
	@JoinColumn(name="brett", referencedColumnName="id")
	private Brett brett;
	
	public Slange(int rutenummer, int slangeMaal) {
		super(rutenummer);
		this.til = slangeMaal;
	}
	public Slange() {
	}
	@Override
	public int getDestinationIndex(){
		return til;
	}
	public void setDestinationIndex(int ladderDestinationIndex) {
		this.til = ladderDestinationIndex;
	}
	public int move() {
		System.out.println("Du landet på en slange! Satans krypdyr! Du rykker ned til " + til);
		return getDestinationIndex();
	}
}
