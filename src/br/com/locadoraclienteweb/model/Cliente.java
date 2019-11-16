package br.com.locadoraclienteweb.model;

import java.time.LocalDate;

public class Cliente extends Pessoa{
	
	private String telefone;
	private String endereco;
	private LocalDate dataNascimento;
	private int codigoCliente;
	
	public boolean validaIdade(){
		LocalDate dataHoje = LocalDate.now();
		
		if(dataHoje.compareTo(this.getDataNascimento()) < 18){
			return false;
		}else{
			return true;
		}
	}
	
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	
	
}
