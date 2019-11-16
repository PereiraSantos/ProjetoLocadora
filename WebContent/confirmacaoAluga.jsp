<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabela aluga</title>

<style>
	#container{
		width:100%;
		height:600px;
		background:#f2f2f2;
	
	}
	h1{
		font-size:18px;
		margin-left:05px;
		color:black;
	}
	a{
		margin-left:10px;
		
	}
	

</style>

</head>
<body>

	<div id="container">

		<h1>Cpf: ${requestScope.aluga.cliente}</h1>
		<h1>Data retirada: ${requestScope.aluga.dataRetiradaCarro} </h1>
		<h1>Data entrega: ${requestScope.aluga.dataEntregaCarro}</h1>
		<h1>Hora retirada: ${requestScope.aluga.horaRetiradaCarro}</h1>
		<h1>Hora entrega: ${requestScope.aluga.horaEntregaCarro}</h1>
		<h1>Valor diaria: ${requestScope.aluga.valorAluguel}</h1>
		<h1>Dias: ${requestScope.aluga.periodo}</h1>
		<h1>Valor total: ${requestScope.aluga.totalAluguel}</h1>
		<br>
		<a href="index.html"><button>Confirma aluguel</button></a>
			
	</div>


</body>
</html>