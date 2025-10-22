<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Bono</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<%@include file="../../../includes/header_cliente.jsp" %>
<div class="login">
	<h1>Crear Bono</h1>
	
	<form id="myForm" action="/practica3/crearBonoControllerServlet" method="post">
		<label>Tipo de bono: </label>
		
		<select id="seleccionFormulario" name="seleccion" required="required">
			<option value="" disabled selected>Seleccione una opción</option>
			<option value="infantil">Infantil</option>
			<option value="familiar">Familiar</option>
			<option value="adultos">Adultos</option>
		</select>
	
		<input type="submit" value="Enviar">
	</form>
</div>

</body>
</html>