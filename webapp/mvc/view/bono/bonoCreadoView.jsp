<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bono Creado</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<%@include file="../../../includes/header_cliente.jsp" %>

<div class="login">

		<h1>Bono creado con &eacutexito</h1>
		
		<form action="${pageContext.request.contextPath}/obtenerTipoBonoControllerServlet">
			<input type="submit" name="inicio" id="a" class="boton-personalizado" style="width:496px;" value="Ir a realizar reserva con bono"><br> 
		</form>

</div>



</body>
</html>