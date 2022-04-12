package jpa_sesion03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo7 {
	
	public static void main(String[] args) { 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		
		int busq = 1;
		
		TypedQuery<Usuario> consulta =  em.createQuery("select u from Usuario u where u.tipo = :tipoq", Usuario.class).setParameter("tipoq", busq);
		
		for (Usuario u : consulta.getResultList()) {
			System.out.println(u);
		}
		
		em.close();
	}

}
