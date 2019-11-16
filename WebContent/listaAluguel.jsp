<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lista alguel</title>
</head>
<body>
<a href="index.html"><button>Home</button></a>
<h1>Lista aluguel</h1>
			<table border='1'>
	<tr>
		<th>codigo aluguel: </th>
		<th>codigo carro: </th>
		<th>cpf: </th>
		<th>data retirada : </th>
		<th>data entrega : </th>
		<th>hora retirada: </th>
		<th>hora entrega: </th>
		<th>valor aluguel: </th>
		<th>periodo: </th>
		<th>valor total : </th>
		
	</tr>
<c:forEach items="${requestScope.aluga}" var="aluga">

	<tr>
		<td>${aluga.codigoAluguel}</td>
		<td>${aluga.carro}</td>
		<td>${aluga.cliente}</td>
		<td>${aluga.dataRetiradaCarro}</td>
		<td>${aluga.dataEntregaCarro}</td>
		<th>${aluga.horaRetiradaCarro}</th>
		<th>${aluga.horaEntregaCarro}</th>
		<th>${aluga.valorAluguel}</th>
		<th>${aluga.periodo}</th>
		<th>${aluga.totalAluguel}</th>
		
	
	</tr>
</c:forEach>
	</table>

</body>
</html>