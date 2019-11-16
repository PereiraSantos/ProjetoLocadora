package br.com.locadoraclienteweb.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.locadoraclienteweb.model.Aluga;
import br.com.locadoraclienteweb.modelDAO.AlugaDAO;
import br.com.locadoraclienteweb.modelDAO.CarroDAO;

@WebServlet("/caixacontrole.do")
public class CaixaControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CaixaControler() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cpf = request.getParameter("cpf");
		
		if(!cpf.equals("")){
			AlugaDAO alugaDAO = new AlugaDAO();
			Aluga aluga = alugaDAO.buscarPorIdAluguel(Integer.parseInt(cpf));
			request.setAttribute("aluga", aluga);
			request.getRequestDispatcher("caixaFatura.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("caixaFatura.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codigocarro = request.getParameter("codigocarro");

		if(!codigocarro.equals("")){
			CarroDAO carroDAO = new CarroDAO();
			boolean disponivel = true;
			carroDAO.persistenciaAlterarCarro(Integer.parseInt(codigocarro), disponivel);
			
			request.getRequestDispatcher("index.html").forward(request, response);
		}else{
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}
}
