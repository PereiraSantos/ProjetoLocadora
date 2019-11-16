<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lista de carros</title>
<style>
	#container{
		width:100%;
		height:600px;
		background:white;
	
	}
	.container_a{
		width:200px;
		height:230px;
		background:#49d049;
		float:left;
		margin-left:10px;
		margin-top:10px;
	}
	.container_b{
		width:200px;
		height:230px;
		background:#ff3333;
		float:left;
		margin-left:10px;
		margin-top:10px;
	}
	h1{
		font-size:15px;
		margin-left:05px;
		color:white;
	}
	#formulario{
		float:right;
	}
	.aa{
		margin-left: 25%;
    	
	}

</style>
</head>
<body>
	<div id="container">
	<br>
		<a href="formularioCliente.html"><button>cadastro cliente</button></a>
		<form id="formulario" action="autcontrole.do" method="POST">
			<label>cpf: </label><input type="text" name="cpf" placeholder="cpf"/><input type="submit" value="logar"/>
		</form>
		
		<br><br>
		<c:forEach items="${requestScope.lista}" var="usu">
			<c:if test="${usu.disponivel == true}">
				<div class="container_a">
					<h1>Marca: ${usu.marca}</h1>
					<h1>Modelo: ${usu.modelo}</h1>
					<h1>Ano: ${usu.anoFabricacao}</h1>
					<h1>Portas: ${usu.quantidadePortas}</h1>
					<h1>Combustivel: ${usu.tipoCombustivel}</h1>
					<h1>Valor diaria: R$:60.00</h1>
					
					<a class="aa" href="formulariocarro.do?acao=codigo&id=${usu.codigoCarro}"><button>Alugar</button></a>
					
				</div>
			</c:if>
			<c:if test="${usu.disponivel == false}">
				<div class="container_b">
					<h1>Marca: ${usu.marca}</h1>
					<h1>Modelo: ${usu.modelo}</h1>
					<h1>Ano: ${usu.anoFabricacao}</h1>
					<h1>Portas: ${usu.quantidadePortas}</h1>
					<h1>Combustivel: ${usu.tipoCombustivel}</h1>
					<h1>Valor diaria: R$:60.00</h1>
					<h1>INDISPONIVEL</h1>
				</div>
			</c:if>
		</c:forEach>
	</div>		
</body>
</html>