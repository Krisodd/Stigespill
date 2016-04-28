package no.hib.dat102;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class DB_logic {
	
	EntityManagerFactory emf;
    EntityManager em;
    public DB_logic() {
    	emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
    	em = emf.createEntityManager();
    }
    public Rute[] getSlanger() {
    	TypedQuery<Slange> query = em.createQuery("SELECT s.fra FROM slange s", Slange.class);
    	List<Slange> slanger = query.getResultList();
    	return (Rute[]) slanger.toArray();
    	
    }
    public Brett getBrett() {
    	TypedQuery<Brett> query = em.createQuery("SELECT b.stig FROM brett b WHERE b.id='1'", Brett.class);
    	System.out.println(query.toString());
    	return null;
    }
    public void persistBrett(Brett b){
    try {
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
    } catch (RollbackException e) {
    	System.out.println(e);
        em.getTransaction().rollback();
    }
    }
    
}
