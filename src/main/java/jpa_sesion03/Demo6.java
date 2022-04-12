package jpa_sesion03;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo6 {
	
	public static void main(String[] args) { 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		
		TypedQuery<Usuario> consulta =  em.createQuery("select u from Usuario u", Usuario.class);
		
		for (Usuario u : consulta.getResultList()) {
			System.out.println(u);
		}
		
		em.close();
	}
}
