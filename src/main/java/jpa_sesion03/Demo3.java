package jpa_sesion03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo3 {
	public static void main(String[] args) {
 
		//Eliminar usuario

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();  
		em.getTransaction().begin();
		Usuario u = new Usuario();  
		u = em.find(Usuario.class,1); 
		
		if(u==null)
			System.out.println("No hay ID");
		else 
			em.remove(u);
		
		em.getTransaction().commit();
		
		em.close();
	}
}
