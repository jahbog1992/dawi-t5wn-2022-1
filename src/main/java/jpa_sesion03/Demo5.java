package jpa_sesion03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo5 {
	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		em.getTransaction().begin();
		
		Usuario u = new Usuario(); 
		u.setCodigo(3); 
		
		em.remove(u);
		
		em.getTransaction().commit();
		em.close();
	}
}
