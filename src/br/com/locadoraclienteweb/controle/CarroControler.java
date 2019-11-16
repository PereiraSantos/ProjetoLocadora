package br.com.locadoraclienteweb.controle;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.locadoraclienteweb.model.Carro;
import br.com.locadoraclienteweb.modelDAO.CarroDAO;



@WebServlet("/formulariocarro.do")
public class CarroControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CarroControler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		CarroDAO carroDAO = new CarroDAO();
		if(acao.equals("list")){
			List<Carro> lista = carroDAO.listarTodosCarro();
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("listaCarro.jsp").forward(request, response);
			
		}if(acao.equals("codigo")){
			String codigocarro = request.getParameter("id");
			Carro carro = carroDAO.buscarPorIdCarro((Integer.parseInt(codigocarro)));
			request.setAttribute("carro", carro);
			request.getRequestDispatcher("formularioAluga.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String marca = request.getParameter("txtMarca");
		String modelo = request.getParameter("txtModelo");
		String anoFabricacao = request.getParameter("txtAnoFabricacao");
		String quantidadePortas = request.getParameter("txtQuantidadePortas");
		String tipoCombustivel = request.getParameter("txtTipoCombustivel");
		
		if(autenticaCarro(marca, modelo, anoFabricacao, quantidadePortas, tipoCombustivel)){
			request.getRequestDispatcher("index.html").forward(request, response);
		}else{	
			request.getRequestDispatcher("formularioCarro.html").forward(request, response);
		
		}
	}
	public boolean autenticaCarro(String marca, String modelo, String anoFabricacao, String quantidadePortas,
								  String tipoCombustivel){
			
			if(marca.equals("") || modelo.equals("") || anoFabricacao.equals("") || quantidadePortas.equals("") 
			   || tipoCombustivel.equals("")){
				
			return false;
			}else{
				Carro carro = new Carro();
				carro.setMarca(marca);
				carro.setModelo(modelo);
				carro.setAnoFabricacao(Integer.parseInt(anoFabricacao));
				carro.setQuantidadePortas(Integer.parseInt(quantidadePortas));
				carro.setTipoCombustivel(tipoCombustivel);
				
				CarroDAO carroDAO = new CarroDAO();
				carroDAO.persistenciaSalvarCarro(carro);
				
			return true;
		}
	}
}
