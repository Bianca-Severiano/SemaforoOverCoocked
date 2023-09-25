package controller;

import java.util.concurrent.Semaphore;

import model.Prato;

public class ControllerOvercoocked extends Thread{

	Semaphore semaforoFogao;
	Semaphore semaforoEntrega;
	Prato p;
	
	public ControllerOvercoocked(Semaphore semaforoFogao, Semaphore semaforoEntrega, Prato p) {
		this.semaforoFogao = semaforoFogao;
		this.semaforoEntrega = semaforoEntrega;
		this.p = p;
	}
	
	public void run() {
		Fogao();
	}
	
	private void Fogao() { // Entra no fogao
		try {
			semaforoFogao.acquire();
			Cozimento();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforoFogao.release();
		}
	}
	
	private void Cozimento () {
		System.err.println(p.nome + "#" + p.id + " está no fogo");
		double aux = p.tempoCozimento;
		int cont = 1;
		do {
			try {
				Thread.sleep(100);
				p.cozimento = (10000*cont)/p.tempoCozimento;
				p.cozimento = (Math.round(p.cozimento * Math.pow(10, 1)) / Math.pow(10, 1));
				System.out.println(p.nome + "#" + p.id + ": " + p.cozimento + "% cozido");
				cont++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			aux = aux - 100;
		} while (aux > 0);
		Entrega();
	}
	
	private void Entrega() {
		try {
			semaforoEntrega.acquire();
			processaEntrega();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforoEntrega.release();
		}
	}
	
	private void processaEntrega() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(p.nome + "#" + p.id + ": entregue");
	}

}
