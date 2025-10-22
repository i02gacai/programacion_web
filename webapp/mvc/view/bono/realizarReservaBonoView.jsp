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

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/obtenerPistaReservaControllerServlet?bono=si" method="post">
					
					<label>Fecha de la reserva: </label><input type="date" name="fecha" required="required" />
				   
				    <label>Tipo de la reserva: </label>
			        <select id="seleccionFormulario" required="required" disabled>
			            <option value="<%= request.getAttribute("tipo") %>"><%= request.getAttribute("tipo") %></option>
			        </select>
					
					<% if(request.getAttribute("tipo").equals("familiar") || request.getAttribute("tipo").equals("adultos") ){ %>
			        <input type="number" name="numero_adultos" placeholder="Introduzca el numero de adultos"/><% } %>
			        
			        <% if(request.getAttribute("tipo").equals("familiar") || request.getAttribute("tipo").equals("infantil") ){ %>
			        <input type="number" name="numero_ninos" placeholder="Introduzca el numero de niños" /> <% } %>	
			        
			        <input type="hidden" name="seleccion" value="<%= request.getAttribute("tipo") %>"/>				 
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>