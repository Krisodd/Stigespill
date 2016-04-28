package no.hib.dat102;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity(name="spiller")
@Table(schema = "stigespill")
public class Spiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Boolean stuck;
	Integer plassering;
	String spillerNavn;
	
	
	public Spiller(){
		plassering = 1;
		stuck = false;
		
	}
	public Integer getID(){
		return id;
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
	public boolean isStuck() {
		return stuck;
	}
	public void setStuck(boolean b) {
		this.stuck = b;
	}
	public static void sendSpiller(Spiller s, int trill) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("eclipselink");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
		
	}
	
}