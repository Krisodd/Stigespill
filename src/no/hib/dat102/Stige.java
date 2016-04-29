package no.hib.dat102;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity(name="stige")
@Table(name="stige", schema="stigespill")
public class Stige extends Rute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name="til")
	private Integer til;
	@Column(name="fra")
	private Integer fra;
	@ManyToOne
	@JoinColumn(name="brett", referencedColumnName="id")
	private Brett brett;

	
	public Stige(int rutenummer, int stigeMaal, Brett brett) {
		super(rutenummer);

		this.til = stigeMaal;
		this.fra = rutenummer;
		this.brett = brett;
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
