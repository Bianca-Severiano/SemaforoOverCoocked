package model;

public class Prato {
	public String nome;
	public double tempoCozimento;
	public int id;
	public double cozimento;
	
	public Prato(int id, double cozimento, String nome, int tempoCozimento) {
		this.nome = nome;
		this.cozimento = cozimento;
		this.id = id;
		this.tempoCozimento = tempoCozimento;
	}

	public Prato() {
		super();
	}

}
