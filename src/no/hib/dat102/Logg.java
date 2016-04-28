package no.hib.dat102;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity(name="logg")
@Table(schema="stigespill")
public class Logg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer spill;
	Integer trill;
	Integer fra;
	Integer til;
	Integer spiller;
	public Logg() {
	}
	public Logg(Spiller s, Integer trill, Integer spillId) {
		this.trill = trill;
		this.fra = s.getPlassering();
		if(!s.isStuck()){
		this.til = this.fra+trill;
		} else{
		this.til = 1;
		}
		this.spill = spillId;
		this.spiller = s.getID();
	}
	public void log() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
		em.close();
	}
}
