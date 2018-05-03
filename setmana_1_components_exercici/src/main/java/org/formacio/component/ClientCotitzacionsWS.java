package org.formacio.component;

import org.springframework.stereotype.Component;

//aquesta classe ha de ser detectada com un component
@Component
public class ClientCotitzacionsWS implements IntegradorCotitzacions {

	public int nombreInvocacions = 0;
	private final float MITJANA = 20f;

	public ClientCotitzacionsWS() {
		this.obteMitjanaDiariaCotitzacions();
	}

	public float getMitjanaCotitzacions() {
		return this.MITJANA;
	}
	
	public float obteMitjanaDiariaCotitzacions() {
		nombreInvocacions++; // per controls del test
		return MITJANA;
	}
	
	@Override
	public float obteCotitzacio(String empresa) {
		switch (empresa) {
		     case "cervesses.sa": return 30f;
		     case "shandies.sa": return 10f;
		     default : return MITJANA;
		}
	}

}
