package br.com.locadoraclienteweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.locadoraclienteweb.auxiliardata.ConverteData;
import br.com.locadoraclienteweb.model.Aluga;


public class AlugaDAO {
	private Connection conexao = Conexao.getConnection();
	private ConverteData converteData = new ConverteData();

	public void persistenciaSalvarAluguel(Aluga aluga){
		String sql= "INSERT INTO ALUGA(codigocarro, cpf, dataretiradacarro, dataentregacarro, horaretiradacarro,"
				+ " horaentregacarro, valoraluguel, periodo, valortotal)VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, aluga.getCarro());
			preparador.setInt(2, Integer.parseInt(aluga.getCliente()));
			preparador.setDate(3, converteData.converteLocalDateToSqlDate(aluga.getDataRetiradaCarro())); 
			preparador.setDate(4, converteData.converteLocalDateToSqlDate(aluga.getDataEntregaCarro()));
			preparador.setTime(5, converteData.converteLocalDateToSqlTime(aluga.getHoraRetiradaCarro()));
			preparador.setTime(6, converteData.converteLocalDateToSqlTime(aluga.getHoraEntregaCarro()));
			preparador.setBigDecimal(7, aluga.getValorAluguel());
			preparador.setString(8, String.valueOf(aluga.getPeriodo()));
			preparador.setBigDecimal(9, aluga.getTotalAluguel());
			
			preparador.execute();
			
			JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO COM SUCESSO.");
			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR!!! \n"+ e.getMessage());
				e.printStackTrace();
			}finally{
				if (conexao != null) {
					try { conexao.close(); } catch (SQLException e) {}
				}	  	  
			}
		AlteraValorDisponivelCarro(aluga);
	}
	
	public void AlteraValorDisponivelCarro(Aluga aluga){
		
		CarroDAO carroDAO = new CarroDAO();
		boolean disponivel = aluga.carroDisponivel();
		int codigocarro = aluga.getCarro();
		carroDAO.persistenciaAlterarCarro(codigocarro, disponivel);
	}
	
	public void persistenciaAlterarAluguel(Aluga aluga){
		String sql = "UPDATE ALUGA SET codigocarro=?, cpf=?, datacetiradacarro=?, dataentregacarro=?, horaretiradacarro=?, "
				   + " horaentrgacarro=?, valoraluguel=? WHERE codigoaluguel=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, aluga.getCarro());
			preparador.setString(2, aluga.getCliente());
			preparador.setDate(3, converteData.converteLocalDateToSqlDate(aluga.getDataRetiradaCarro())); 
			preparador.setDate(4, converteData.converteLocalDateToSqlDate(aluga.getDataEntregaCarro()));
			preparador.setTime(5, converteData.converteLocalDateToSqlTime(aluga.getHoraRetiradaCarro()));
			preparador.setTime(6, converteData.converteLocalDateToSqlTime(aluga.getHoraEntregaCarro()));
			preparador.setBigDecimal(7, aluga.getValorAluguel());
			preparador.execute();
			
			System.out.println("atualizado com sucesso");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR!!! \n"+ e.getMessage());
				e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}
		}
	}
	
	public void persistenciaDesativarAluguel(Aluga aluga){
		String sql = "DELETE FROM ALUGA WHERE codigoaluguel=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, aluga.getCarro());
			preparador.execute();
			
			System.out.println("excluido com sucesso");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}
		}
	}
	
	public Aluga buscarPorIdAluguel(Integer cpf){
		String sql = "SELECT * FROM ALUGA WHERE cpf=?";
		Aluga aluga = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, cpf);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				aluga = new Aluga();
				aluga.setCodigoAluguel(resultado.getInt("codigoaluguel"));
				aluga.setCarro(resultado.getInt("codigocarro"));
				aluga.setCliente(resultado.getString("cpf")); 
				aluga.setDataRetiradaCarro(converteData.converteSqlDateToLocalDate(resultado.getDate("dataretiradacarro")));
				aluga.setDataEntregaCarro(converteData.converteSqlDateToLocalDate(resultado.getDate("dataentregacarro")));
				aluga.setHoraRetiradaCarro(converteData.converteSqlDateToLocalTime(resultado.getTime("horaretiradacarro")));
				aluga.setHoraEntregaCarro(converteData.converteSqlDateToLocalTime(resultado.getTime("horaentregacarro")));
				aluga.setValorAluguel(resultado.getBigDecimal("valoraluguel"));
				aluga.setPeriodo(Period.parse(resultado.getString("periodo")));
				aluga.setTotalAluguel(resultado.getBigDecimal("valortotal"));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return aluga;
	}
	
	public List<Aluga> listarTodosAluguel(){
		String sql = "SELECT * FROM ALUGA";
	//	String sql = "select aluga.codigoaluguel, carro.codigocarro, carro.marca, carro.modelo, aluga.dataentregacarro "
	//			+ "from aluga inner join carro on aluga.codigoaluguel = carro.codigocarro WHERE dataentregacarro <= now()";
		List<Aluga> listaAluguel = new ArrayList<Aluga>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Aluga aluga = new Aluga();
				aluga.setCodigoAluguel(resultado.getInt("codigoaluguel"));
				aluga.setCarro(resultado.getInt("codigocarro"));
				aluga.setCliente(resultado.getString("cpf")); 
				aluga.setDataRetiradaCarro(converteData.converteSqlDateToLocalDate(resultado.getDate("dataretiradacarro")));
				aluga.setDataEntregaCarro(converteData.converteSqlDateToLocalDate(resultado.getDate("dataentregacarro")));
				aluga.setHoraRetiradaCarro(converteData.converteSqlDateToLocalTime(resultado.getTime("horaretiradacarro")));
				aluga.setHoraEntregaCarro(converteData.converteSqlDateToLocalTime(resultado.getTime("horaentregacarro")));
				aluga.setValorAluguel(resultado.getBigDecimal("valoraluguel"));
				aluga.setPeriodo(Period.parse(resultado.getString("periodo")));
				aluga.setTotalAluguel(resultado.getBigDecimal("valortotal"));
				
				listaAluguel.add(aluga);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaAluguel;
	}
}
