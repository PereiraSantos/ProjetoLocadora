package br.com.locadoraclienteweb.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class Aluga {
	private int codigoAluguel;
	private int carro;
	private String cliente;
	private LocalDate dataRetiradaCarro;
	private LocalDate dataEntregaCarro;
	private LocalTime horaRetiradaCarro;
	private LocalTime horaEntregaCarro;
	private BigDecimal valorAluguel;
	private BigDecimal iss = new BigDecimal(2);
	private BigDecimal icms = new BigDecimal(12);
	private BigDecimal confins = new BigDecimal(7.6);
	private BigDecimal ipva = new BigDecimal(3);
	private BigDecimal cem = new BigDecimal(100);
	private BigDecimal temporaria = new BigDecimal(0);
	private BigDecimal totalAluguel = new BigDecimal(0);
	private Period periodo;
	 
	// compara  se o carro esta disponnivel  //before antes //after depois
	
	 public Aluga(){
		this.valorAluguel = new BigDecimal(80);
	}
	
	public boolean carroDisponivel(){
		
		boolean disponivel = false;
		LocalDate dataHoje = LocalDate.now();
		
		if(dataHoje.isAfter(this.dataEntregaCarro)){
			disponivel = true;
		}if(dataHoje.isBefore(this.dataEntregaCarro)){
			disponivel = false;
		}
		
		return disponivel;
	}
	
	// conta o  periodo entre as datas
	
	public void calculaDias(){
		periodo = Period.between(this.getDataRetiradaCarro(), this.dataEntregaCarro);
		
		for (int i = 0; i < periodo.getDays(); i++) {
			this.totalAluguel = this.totalAluguel.add(this.getValorAluguel());
		}
	}
	
	//  dound_duwn duas casas decimais
	
	public void imposto(){
		temporaria = iss.multiply(this.getValorAluguel());
		iss = temporaria.divide(cem);
		this.setValorAluguel(this.getValorAluguel().subtract(iss).setScale(2, BigDecimal.ROUND_DOWN));
		temporaria = new BigDecimal(0);
		
		temporaria = icms.multiply(this.getValorAluguel());
		icms = temporaria.divide(cem);
		this.setValorAluguel(this.getValorAluguel().subtract(icms).setScale(2, BigDecimal.ROUND_DOWN));
		temporaria = new BigDecimal(0);
		
		temporaria = confins.multiply(this.getValorAluguel());
		confins = temporaria.divide(cem);
		this.setValorAluguel(this.getValorAluguel().subtract(confins).setScale(2, BigDecimal.ROUND_DOWN));
		temporaria = new BigDecimal(0);
		
		temporaria = ipva.multiply(this.getValorAluguel());
		ipva = temporaria.divide(cem);
		this.setValorAluguel(this.getValorAluguel().subtract(ipva).setScale(2, BigDecimal.ROUND_DOWN));
		
	}
	
	public int getCodigoAluguel() {
		return codigoAluguel;
	}

	public void setCodigoAluguel(int codigoAluguel) {
		this.codigoAluguel = codigoAluguel;
	}

	public int getCarro() {
		return carro;
	}

	public void setCarro(int carro) {
		this.carro = carro;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataRetiradaCarro() {
		return dataRetiradaCarro;
	}
	public void setDataRetiradaCarro(LocalDate dataRetiradaCarro) {
		this.dataRetiradaCarro = dataRetiradaCarro;
	}
	public LocalDate getDataEntregaCarro() {
		return dataEntregaCarro;
	}
	public void setDataEntregaCarro(LocalDate dataEntregaCarro) {
		this.dataEntregaCarro = dataEntregaCarro;
	}
	public LocalTime getHoraRetiradaCarro() {
		return horaRetiradaCarro;
	}
	public void setHoraRetiradaCarro(LocalTime horaRetiradaCarro) {
		this.horaRetiradaCarro = horaRetiradaCarro;
	}
	public LocalTime getHoraEntregaCarro() {
		return horaEntregaCarro;
	}
	public void setHoraEntregaCarro(LocalTime horaEntregaCarro) {
		this.horaEntregaCarro = horaEntregaCarro;
	}
	public BigDecimal getValorAluguel() {
		return valorAluguel;
	}
	public void setValorAluguel(BigDecimal valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public BigDecimal getTotalAluguel() {
		return totalAluguel;
	}

	public void setTotalAluguel(BigDecimal totalAluguel) {
		this.totalAluguel = totalAluguel;
	}

	public Period getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Period periodo) {
		this.periodo = periodo;
	}
	
		
}
