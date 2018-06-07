package org.formacio.setmana2.repositori;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.stereotype.Component;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Component
public class RepositoriEscola {

	@PersistenceContext
	EntityManager em;
	
	public Curs carregaCurs(String nom) {

		Curs curs16= new Curs();
		curs16.setNom(nom);
		curs16.setEdatMinima(16);
		return  curs16;
	}

	
	public Matricula apunta (String alumne, String curs) throws EdatIncorrecteException {
		Matricula matricula = new Matricula();
		
		Alumne alumneAInsertar = em.find(Alumne.class, alumne);
		Curs cursAInsertar = em.find(Curs.class, curs);

		if( alumneAInsertar.getEdat() < cursAInsertar.getEdatMinima()) {
			throw new EdatIncorrecteException();
		}

		matricula.setAlumne(alumneAInsertar);
		matricula.setCurs(cursAInsertar);

		em.persist(matricula);

		return matricula;

	}
	
	
}
