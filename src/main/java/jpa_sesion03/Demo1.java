package jpa_sesion03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo1 {

	public static void main(String[] args) {
		//nuevo usuario
		Usuario u = new Usuario();
		u.setCodigo(11);
		u.setNombre("Juan");
		u.setApellido("Perez");
		u.setUsuario("jperez2@lim.com");
		u.setClave("12345");
		u.setFchnacim("2000/01/05");
		u.setTipo(1);
		u.setEstado(1);
		 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql"); 
		EntityManager em = fabrica.createEntityManager(); 
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		em.close();
	}

}
