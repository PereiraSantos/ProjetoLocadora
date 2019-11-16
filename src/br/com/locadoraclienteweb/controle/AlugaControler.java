package br.com.locadoraclienteweb.controle;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.locadoraclienteweb.auxiliardata.ConverteData;
import br.com.locadoraclienteweb.model.Aluga;
import br.com.locadoraclienteweb.modelDAO.AlugaDAO;

@WebServlet("/formularioaluga.do")
public class AlugaControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ConverteData converteData = new ConverteData();
       
    public AlugaControler() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao.equals("lista")){
			AlugaDAO alugaDAO = new AlugaDAO();
			List<Aluga> aluga = alugaDAO.listarTodosAluguel();
			request.setAttribute("aluga", aluga);
			request.getRequestDispatcher("listaAluguel.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codigoCarro = request.getParameter("txtCodigoCarro");
		String cpf = request.getParameter("txtCpf");
		String dataRetiradaCarro = request.getParameter("txtDataRetiradaCarro");
		String dataEntregaCarro = request.getParameter("txtDataEntregaCarro");
		String horaRetiradaCarro = request.getParameter("txtHoraRetiradaCarro");
		String horaEntregaCarro = request.getParameter("txtHoraEntregaCarro");
		
		
		
		if(validaAluga(codigoCarro, cpf, dataRetiradaCarro, dataEntregaCarro, horaRetiradaCarro, horaEntregaCarro)){
			
			AlugaDAO alugaDAO = new AlugaDAO();
			Aluga aluga = alugaDAO.buscarPorIdAluguel((Integer.parseInt(cpf)));
			request.setAttribute("aluga", aluga);
			request.getRequestDispatcher("confirmacaoAluga.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("formularioCarro.html").forward(request, response);
		}
	}
	
	public boolean validaAluga(String codigoCarro, String cpf,String dataRetiradaCarro, String dataEntregaCarro, 
							   String horaRetiradaCarro, String horaEntregaCarro){
		
		if(codigoCarro.equals("") || cpf.equals("") || dataRetiradaCarro.equals("") || dataEntregaCarro.equals("") ||
			horaRetiradaCarro.equals("") || horaEntregaCarro.equals("")){
		return false;
		}else{
			Aluga aluguel = new Aluga();
			aluguel.setCarro(Integer.parseInt(codigoCarro));
			aluguel.setCliente(cpf);
			aluguel.setDataRetiradaCarro(converteData.converteStringToLocalDate(dataRetiradaCarro));
			aluguel.setDataEntregaCarro(converteData.converteStringToLocalDate(dataEntregaCarro));
			aluguel.setHoraRetiradaCarro(converteData.converteStringToLocalTime(horaRetiradaCarro));
			aluguel.setHoraEntregaCarro(converteData.converteStringToLocalTime(horaEntregaCarro));
						
			aluguel.imposto();
			aluguel.calculaDias();
			
			aluguel.carroDisponivel();
			
			AlugaDAO alugaDAO = new AlugaDAO();
			alugaDAO.persistenciaSalvarAluguel(aluguel);
			
		return true;
		}
	}

}
