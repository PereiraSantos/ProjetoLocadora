package br.com.locadoraclienteweb.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AutenticadorController", urlPatterns = { "/autcontrole.do" })
public class Altenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Altenticador() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// mata sessao
		HttpSession sessao = request.getSession(false);
		if(sessao!=null){
			sessao.invalidate();		
		}
		request.getRequestDispatcher("index.html").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		
		if(cpf.equals("1")){
			// cria  sessao
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usuLogado", cpf);
			
			request.getRequestDispatcher("caixaFatura.jsp").forward(request, response);
		}
		if(cpf.equals("") || !cpf.equals("1")){
			request.getRequestDispatcher("index.html").forward(request, response);
				
		}else{
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}

}
