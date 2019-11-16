<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alugar</title>

</head>
<body>
	
	<a href="formularioCliente.html"><button>cadastro cliente</button></a>
	<a href="index.html"><button>Home</button></a>
	<h1 style="color:silver">alugar carro</h1>
	<form id="form" action="formularioaluga.do" method="POST">
		<label>carro:</label><br>
		<input type="text" name="txtCodigoCarro" value="${requestScope.carro.codigoCarro}" required/><br><br>
		<label>cpf:</label><br>
		<input type="text" name="txtCpf" value="" placeholder="cpf" required/><br><br>
		<label>data retirada carro:</label><br>
		<input type="date" name="txtDataRetiradaCarro" value="" placeholder="data retirada" required/><br><br>
		<label>data entrega carro:</label><br>
		<input type="date" name="txtDataEntregaCarro" value="" placeholder="data entrega" required/><br><br>
		<label>hora retirada carro:</label><br>
		<input type="time" name="txtHoraRetiradaCarro" value="" placeholder="hora retirada" min="08:00" max="18:00" required/>
		<small>Office hours are 9am to 6pm</small><br><br>
		<label>hora entrega carro:</label><br>
		<input type="time" name="txtHoraEntregaCarro" value="" placeholder="hota entrega" min="08:00" max="18:00" required/>
		<small>Office hours are 9am to 6pm</small><br><br>
		<input type="submit" value="Alugar"/>
	</form>


</body>
</html>