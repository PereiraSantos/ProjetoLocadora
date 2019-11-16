package br.com.locadoraclienteweb.controle;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}, urlPatterns = {"/.html"})
public class FiltroAutentica implements Filter {

    public FiltroAutentica() {
    }
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		// esta logado?
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url= httpServletRequest .getRequestURI();
		// captura sessao
		HttpSession sessao = httpServletRequest .getSession();
		
		// esta logado?
		if(sessao.getAttribute("usuLogado")!=null || url.lastIndexOf("index.html")>-1 || url.lastIndexOf("autcontrole.do")>-1){
			chain.doFilter(request, response); //permiteo fluxo da requisiçao
		}else{
			// redireciona para o login
			((HttpServletResponse)response).sendRedirect("index.html");
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
