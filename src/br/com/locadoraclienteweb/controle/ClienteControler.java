package br.com.locadoraclienteweb.controle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.locadoraclienteweb.model.Cliente;
import br.com.locadoraclienteweb.modelDAO.ClienteDAO;


@WebServlet("/formulariocliente.do")
public class ClienteControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ClienteControler() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String cpf = request.getParameter("txtCpf");
		 String nomeCompleto = request.getParameter("txtNomeCompleto");
		 String telefone = request.getParameter("txtTelefone");
		 String endereco = request.getParameter("txtEndereco");
		 String localDate  = request.getParameter("txtDataNascimento");
	
		 if(validaCliente(cpf, nomeCompleto, telefone, endereco, localDate)){
			 request.getRequestDispatcher("index.html").forward(request, response);
		 }else{
			 request.getRequestDispatcher("formularioCliente.html").forward(request, response);
		 }
	
	}
	public boolean validaCliente(String cpf, String nomeCompleto, String telefone, String endereco, String localDate){
		
		if(cpf.equals("") || nomeCompleto.equals("") || telefone.equals("") || endereco.equals("") || localDate.equals("")){
		return false;	
		}else{
			Cliente cliente =  new Cliente();
			cliente.setCpf(Integer.parseInt(cpf));
			cliente.setNomecompleto(nomeCompleto);			
			cliente.setTelefone(telefone);
			cliente.setEndereco(endereco);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDate dataNascimento = LocalDate.parse(localDate, formatter);
			cliente.setDataNascimento(dataNascimento);
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.persistenciaSalvarCliente(cliente);
		return true;
		}
	}
	
	public void listarTodosCliente(){
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> listaClientes = clienteDAO.listarTodosCliente();
		
		for (Cliente cliente : listaClientes) {
			System.out.println("codigo cliente: "+cliente.getCodigoCliente()); 
		}
	}

}
