package no.hib.dat102;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity(name="stige")
@Table(name="stige", schema="stigespill")
public class Stige extends Rute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	private Integer til;
	private Integer fra;
	@ManyToOne
	@JoinColumn(name="brett", referencedColumnName="id")
	private Brett brett;

	
	public Stige(int rutenummer, int stigeMaal) {
		super(rutenummer);
		this.til = stigeMaal;
		this.fra = super.getRuteIndex();
	}
	public Stige(){
	}
	@Override
	public int getDestinationIndex(){
		return til;
	}
	public void setDestinationIndex(int ladderDestinationIndex) {
		this.til = ladderDestinationIndex;
	}
	public int move() {
		System.out.println("Du landet på en stige! Spenstig! Klatre opp til rute " + til);
		return getDestinationIndex();
	}
	public int getRutenummer() {
		return super.getRuteIndex();
	}
}
