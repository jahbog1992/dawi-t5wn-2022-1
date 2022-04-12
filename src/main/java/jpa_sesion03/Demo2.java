package jpa_sesion03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo2 {
	public static void main(String[] args) {
		//Actualizar usuario
		Usuario u = new Usuario();
		u.setCodigo(3);
		u.setNombre("Carla");
		u.setApellido("Toro");
		u.setUsuario("aaaa@lim.com");
		u.setClave("12345");
		u.setFchnacim("2000/01/05");
		u.setTipo(1);
		u.setEstado(1);
		 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		em.getTransaction().begin();
		//Usuario ok = 
				em.merge(u);
		em.getTransaction().commit();
		em.close();
		
	}
}
