package org.formacio.setmana2.servei;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.formacio.setmana2.domini.Matricula;
import org.formacio.setmana2.repositori.EdatIncorrecteException;
import org.formacio.setmana2.repositori.RepositoriEscola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServeiEscola {

	
	/**
	 * Important: els alumnes i els cursos indicats JA existeixen a la base de dades.
	 * Per tant, els hem de carregar, no crear de nou.
	 * L'excepcio EdatIncorrecteException no s'ha de capturar. S'ha de propagar cap el client
	 */

	@Autowired
	RepositoriEscola repositoriEscola;
	
	@Transactional(rollbackOn = {EdatIncorrecteException.class})
	public List<Matricula> apunta (String curs, List<String> alumnes) throws EdatIncorrecteException {
		List<Matricula> llistaMatricules = new ArrayList();
		
		for (String alumne: alumnes) {
			Matricula matricula = repositoriEscola.apunta(alumne, curs);
			llistaMatricules.add(matricula);
		}

		return llistaMatricules;
    }
}
