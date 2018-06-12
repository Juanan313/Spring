package org.formacio.servei;

import java.util.Optional;

import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FacturesService {

	@Autowired
	FacturesRepositori repositoriFact;

	@Autowired
	FidalitzacioService serviceFidelitzacio;

	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Optional<Factura> factura = repositoriFact.findById(idFactura);

		if (factura.isPresent()) {
			LiniaFactura linia = new LiniaFactura();
			linia.setProducte(producte);
			linia.setTotal(totalProducte);
			
			factura.get().getLinies().add(linia);

			repositoriFact.save(factura.get());
			
			notificarPremi(factura.get());
		}

		return factura.get();
	}

	public void notificarPremi (Factura factura) {
		final int NUM_LINIES_PER_PREMI = 4;

		if (factura.getLinies().size() >= NUM_LINIES_PER_PREMI) {
			serviceFidelitzacio.notificaRegal(factura.getClient().getEmail());
		}
	}
}

