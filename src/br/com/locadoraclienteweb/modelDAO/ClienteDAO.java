package br.com.locadoraclienteweb.modelDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.locadoraclienteweb.model.Cliente;

public class ClienteDAO {
	private Connection conexao = Conexao.getConnection();
	
	public void persistenciaSalvarCliente(Cliente cliente){
		String sql= "INSERT INTO CLIENTE(cpf, nomecompleto, telefone, endereco, datanascimento)VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, cliente.getCpf());
			preparador.setString(2, cliente.getNomecompleto());
			preparador.setString(3, cliente.getTelefone());
			preparador.setString(4, cliente.getEndereco());
			Date date = Date.valueOf(cliente.getDataNascimento());
			preparador.setDate(5, date);
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
	}
	public void persistenciaAlterarCliente(Cliente cliente){
		String sql = "UPDATE CLIENTE SET nomeCompleto=?, telefone=?, endereco=?, dataNascimento=? WHERE cpf=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, cliente.getNomecompleto());
			preparador.setString(2, cliente.getTelefone());
			preparador.setString(3, cliente.getEndereco());
			Date date = Date.valueOf(cliente.getDataNascimento());
			preparador.setDate(4, date);
			preparador.setInt(5, cliente.getCpf());
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
	public void persistenciaDesativarCliente(Cliente cliente){
		String sql = "DELETE FROM CLIENTE WHERE cpf=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, cliente.getCpf());
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
	
	public Cliente buscarPorIdCliente(Integer cpf){
		String sql = "SELECT FROM CLIENTE WHERE cpf=?";
		Cliente cliente = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, cpf);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				cliente = new Cliente();
				cliente.setCodigoCliente(resultado.getInt("codigocliente"));
				cliente.setCpf(resultado.getInt("cpf"));
				cliente.setNomecompleto(resultado.getString("nomecompleto"));
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setEndereco(resultado.getString("endereco"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
				LocalDate dataNascimento = LocalDate.parse((CharSequence) resultado.getDate("datanascimento"), formatter);
				cliente.setDataNascimento(dataNascimento);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return cliente;
	}
	
	public List<Cliente> listarTodosCliente(){
		String sql = "SELECT * FROM CLIENTE";
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Cliente cliente = new Cliente();
				cliente.setCodigoCliente(resultado.getInt("codigocliente"));
				cliente.setCpf(resultado.getInt("cpf"));
				cliente.setNomecompleto(resultado.getString("nomecompleto"));
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setEndereco(resultado.getString("endereco"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
				LocalDate dataNascimento = LocalDate.parse((CharSequence) resultado.getDate("datanascimento"), formatter);
				cliente.setDataNascimento(dataNascimento);
				
				listaClientes.add(cliente);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaClientes;
	}
	
}

