<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consultar Reservas</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<%@include file="../../../includes/header_cliente.jsp" %>

	<div class="login">
	
		<h3>Consultar reservas entre dos fechas</h3>
	
		<form id="myForm" action="/practica3/consultarReservasControllerServlet" method="post">
			
			<label>Fecha inicio:<input type="date" name="fecha_inicio" required="required"></label>
        
        	<label>Fecha fin:<input type="date" name="fecha_fin" required="required"></label>
			
			<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>