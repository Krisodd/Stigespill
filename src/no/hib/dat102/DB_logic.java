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
    	TypedQuery<Slange> query = em.createQuery("SELECT s FROM slange s", Slange.class);
    	List<Slange> slanger = query.getResultList();
    	return (Rute[]) slanger.toArray();
    	
    }
    /*
    try {
        em.getTransaction().begin();
        em.persist(kunde);
        em.getTransaction().commit();
    } catch (RollbackException e) {
    	System.out.println(e);
        em.getTransaction().rollback();
    }
    */
    
    
    
}
