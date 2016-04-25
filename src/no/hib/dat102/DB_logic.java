package no.hib.dat102;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class DB_logic {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
    EntityManager em = emf.createEntityManager();
    
    try {
        em.getTransaction().begin();
        em.persist(kunde);
        em.getTransaction().commit();
    } catch (RollbackException e) {
    	System.out.println(e);
        em.getTransaction().rollback();
    }
    
    
    
    
}
