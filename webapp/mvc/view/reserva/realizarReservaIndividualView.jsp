<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Realizar reserva</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mostrarCampos.js"></script>
</head>
<body>

<%@include file="../../../includes/header_cliente.jsp" %>

<div class="login">

		<h1>Realizar Reserva</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/obtenerPistaReservaControllerServlet" method="post">
					
					<label>Fecha de la reserva: </label><input type="date" name="fecha" required="required" />
				   
				    <label>Tipo de la reserva: </label>
			        <select id="seleccionFormulario" name="seleccion" required="required" onchange="mostrarCampos()">
			            <option value="" disabled selected>Seleccione una opción</option>
			            <option value="infantil">Infantil</option>
			            <option value="familiar">Familiar</option>
			            <option value="adultos">Adultos</option>
			        </select>
					
			        <input type="number" name="numero_adultos" placeholder="Introduzca el numero de adultos" style="display:none;" />
			        
			        <input type="number" name="numero_ninos" placeholder="Introduzca el numero de niños" style="display:none;"/>					 
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>