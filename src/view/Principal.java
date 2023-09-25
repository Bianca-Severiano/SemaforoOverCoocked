package view;


import java.util.concurrent.Semaphore;

import controller.ControllerOvercoocked;
import model.Prato;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforoFogao = new Semaphore(5);
		Semaphore semaforoEntrega = new Semaphore(1);
		
		
		for (int i = 1; i < 7; i++) {
			Prato p = new Prato();
			p.id = i;
			p.cozimento = 0.0;
			if(i%2 == 0) {
				p.nome = "Lasanha a Bolonhesa";
				p.tempoCozimento = Math.random() * 0.3 + 0.5;
				
			} else {
				p.nome = "Sopa de Cebola";
				p.tempoCozimento = Math.random() * 0.6 + 0.6;
			}
			
			p.tempoCozimento = (Math.round(p.tempoCozimento * Math.pow(10, 1)) / Math.pow(10, 1)) * 1000;
			
			Thread t = new ControllerOvercoocked(semaforoFogao, semaforoEntrega, p);
			t.start();
		}

	}

}
