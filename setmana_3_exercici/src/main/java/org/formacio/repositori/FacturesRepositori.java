package org.formacio.repositori;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacturesRepositori extends CrudRepository <Factura, Long>  {

	// Test 3 implementam el metodo sum dins el query, retorna la suma del total de totes le lineas del client donat.
	@Query("select sum(linia.total) from Factura factura join factura.linies linia where factura.client.nom = ?1")
	public Number totalClient(String client);
	
	@Query("select factura from Factura factura where factura.client.nom =?1")
	public List<Factura> cercaPerNomClient(String client);
}
