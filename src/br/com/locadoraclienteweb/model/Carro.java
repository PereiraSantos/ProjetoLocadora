package br.com.locadoraclienteweb.model;


public class Carro extends Altomovel{
	
	private int codigoCarro;
	private String tipoCombustivel;
	private boolean disponivel;
	
	public Carro(){
		this.disponivel = true;
	}

	public int getCodigoCarro() {
		return codigoCarro;
	}

	public void setCodigoCarro(int codigoCarro) {
		this.codigoCarro = codigoCarro;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	
}
