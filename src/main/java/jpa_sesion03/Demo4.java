package jpa_sesion03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo4 {
	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager();  
		Usuario u = new Usuario();  
		u = em.find(Usuario.class,99); 
		
		if(u==null)
			System.out.println("No hay ID");
		else 
			System.out.println(u.toString());
		
		em.close();
	}
}
