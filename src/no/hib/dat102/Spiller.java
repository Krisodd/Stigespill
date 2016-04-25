package no.hib.dat102;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "stigespill")
public class Spiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int plassering;
	String spillerNavn;
	
	
	public Spiller(){
		plassering = 1;
		
	}
	
	public void setNavn(String navn){
		spillerNavn = navn;
	}
	
	public String getNavn(){
		return spillerNavn;
	}
	
	public void setPlassering(int plassering){
		this.plassering = plassering;
	}
	
	public int getPlassering(){
		return plassering;
	}
}