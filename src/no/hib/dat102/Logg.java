package no.hib.dat102;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity(name="logg")
@Table(schema="stigespill")
public class Logg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
//	Integer spill;
	Integer trill;
	Integer fra;
	Integer til;
	 // TODO begynn her
	@ManyToOne(targetEntity=Spiller.class)
	@JoinColumn(name="spiller", referencedColumnName="id")
	Spiller spiller;
	public Logg() {
	}
//	public Logg(Spiller s, Integer trill, Integer spillId) {
//		this.trill = trill;
//		this.fra = s.getPlassering();
//		if(!s.isStuck()){
//		this.til = this.fra+trill;
//		} else{
//		this.til = 1;
//		}
//		this.spill = spillId;
//		this.spiller = s.getID();
//	}
	public void logEntry() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
		em.close();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTrill() {
		return trill;
	}
	public void setTrill(Integer trill) {
		this.trill = trill;
	}
	public Integer getFra() {
		return fra;
	}
	public void setFra(Integer fra) {
		this.fra = fra;
	}
	public Integer getTil() {
		return til;
	}
	public void setTil(Integer til) {
		this.til = til;
	}
	public Spiller getSpiller() {
		return spiller;
	}
	public void setSpiller(Spiller spiller) {
		this.spiller = spiller;
	}
}
