package no.hib.dat102;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity(name="spiller")
@Table(schema = "stigespill")
public class Spiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany(mappedBy="spillere")
	Integer id;
	Boolean stuck;
	Integer plassering;
	String spillerNavn;
//	@ManyToOne
//	@JoinColumn(name="spill", referencedColumnName="id")
//	private Spill spill;
	
	
	
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
	public void persistSpiller() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
		em.close();
		
	}
	
}