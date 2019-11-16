<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caixa </title>
<style>
		h1{
		font-size:18px;
		margin-left:05px;
		color:black;
	}

</style>
</head>
<body>
	<a href="index.html"><button>Home</button></a>
	<a href="formularioCarro.html"><button>cadastro carro</button></a>
	<a href="formularioaluga.do?acao=lista"><button>tabela aluguel</button></a>
	<a href="autcontrole.do"><button>sair</button></a>
	<h1>Caixa</h1>
	
	<form action="caixacontrole.do" method="GET">
		<label>Cpf: </label>
		<input type="text" name="cpf" value="" placeholder="Digite seu cpf"/>
		
		<input type="submit" value="pesquisar"/>
	</form>
	
	<br><br>
		<c:choose>
    		<c:when test="${requestScope.aluga.cliente eq null}">
       		 	<h1>Cliente não existe</h1>
    		</c:when>    
    		<c:otherwise>
	    		<h1>Cpf: ${requestScope.aluga.cliente}</h1>
	       		<h1>Data retirada: ${requestScope.aluga.dataRetiradaCarro} </h1>
				<h1>Data entrega: ${requestScope.aluga.dataEntregaCarro}</h1>
				<h1>Hora retirada: ${requestScope.aluga.horaRetiradaCarro}</h1>
				<h1>Hora entrega: ${requestScope.aluga.horaEntregaCarro}</h1>
				<h1>Valor diaria: ${requestScope.aluga.valorAluguel}</h1>
				<h1>Dias: ${requestScope.aluga.periodo}</h1>
				<h1>Valor total: ${requestScope.aluga.totalAluguel}</h1>
				
				<form action="caixacontrole.do" method="POST">
					<label>Valor: </label>
					<input type="text" name="cpf" value="${requestScope.aluga.totalAluguel}"/><br><br>
					<label>Carro</label>
					<input type="text" name="codigocarro" value="${requestScope.aluga.carro}" readonly/>
					<input type="submit" value="faturar"/>
				</form>
				
    		</c:otherwise>
		</c:choose>	

</body>
</html>