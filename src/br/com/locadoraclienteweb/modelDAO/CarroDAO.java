package br.com.locadoraclienteweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.locadoraclienteweb.model.Carro;

public class CarroDAO {
	private Connection conexao = Conexao.getConnection();
	
	public void persistenciaSalvarCarro(Carro carro){
		String sql= "INSERT INTO CARRO(marca, modelo, anofabricacao, quantidadeportas, tipocombustivel, disponivel)VALUES(?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, carro.getMarca());
			preparador.setString(2, carro.getModelo());
			preparador.setInt(3, carro.getAnoFabricacao());
			preparador.setInt(4, carro.getQuantidadePortas());
			preparador.setString(5, carro.getTipoCombustivel());
			preparador.setBoolean(6, carro.getDisponivel());
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
	public void persistenciaAlterarCarro(int codigocarro, boolean disponivel){
		String sql = "UPDATE CARRO SET disponivel=? WHERE codigocarro=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setBoolean(1, disponivel);
			preparador.setInt(2, codigocarro);
			//preparador.setString(2, carro.getModelo());
			//preparador.setInt(3, carro.getAnoFabricacao());
			//preparador.setInt(4, carro.getQuantidadePortas());
			//preparador.setString(5, carro.getTipoCombustivel());
			//preparador.setInt(6, carro.getCodigoCarro());
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
	public void persistenciaDesativarCarro(Carro carro){
		String sql = "DELETE * FROM CARRO WHERE id=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, carro.getCodigoCarro());
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
	
	public Carro buscarPorIdCarro(int codigocarro){
		String sql = "SELECT * FROM CARRO WHERE codigocarro=?";
		Carro carro = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, codigocarro);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				carro = new Carro();
				carro.setCodigoCarro(resultado.getInt("codigocarro"));
				carro.setMarca(resultado.getString("marca"));
				carro.setModelo(resultado.getString("modelo"));
				carro.setAnoFabricacao(resultado.getInt("anofabricacao"));
				carro.setQuantidadePortas(resultado.getInt("quantidadeportas"));
				carro.setTipoCombustivel(resultado.getString("tipocombustivel"));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return carro;
	}
	
	public List<Carro> listarTodosCarro(){
		String sql = "SELECT * FROM CARRO";
		List<Carro> listaCarros = new ArrayList<Carro>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Carro carro = new Carro();
				carro.setCodigoCarro(resultado.getInt("codigocarro"));
				carro.setMarca(resultado.getString("marca"));
				carro.setModelo(resultado.getString("modelo"));
				carro.setAnoFabricacao(resultado.getInt("anofabricacao"));
				carro.setQuantidadePortas(resultado.getInt("quantidadeportas"));
				carro.setTipoCombustivel(resultado.getString("tipocombustivel"));
				carro.setDisponivel(resultado.getBoolean("disponivel"));
				listaCarros.add(carro);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaCarros;
	}
	
}
