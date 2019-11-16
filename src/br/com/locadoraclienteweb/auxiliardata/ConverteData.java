package br.com.locadoraclienteweb.auxiliardata;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConverteData {
	
	public LocalDate converteStringToLocalDate(String data){
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate dataConvertida = LocalDate.parse(data, formatterDate);
	return dataConvertida;
	}
	public LocalTime converteStringToLocalTime(String time){
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:MM");
		LocalTime horaConvertida = LocalTime.parse(time, formatterTime);
	return horaConvertida;
	}
	public Date converteLocalDateToSqlDate(LocalDate data){
		Date dataConvertida = Date.valueOf(data);
	return dataConvertida;
	}

	public Time converteLocalDateToSqlTime(LocalTime time){
		Time horaConvertida = Time.valueOf(time);
	return horaConvertida;
	}
	
	public LocalDate converteSqlDateToLocalDate(Date data){
		Date dataTipoDate = data;
		LocalDate dataConvertida = dataTipoDate.toLocalDate();
	return dataConvertida;
	}
	public LocalTime converteSqlDateToLocalTime(Time hora){
		Time horaTipoTime = hora;
		LocalTime horaConvertida = horaTipoTime.toLocalTime();
	return horaConvertida;
	}
}
