<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.pistaBean"%>
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

<% Object pistaObj = pageContext.findAttribute("lista_pistas");
			   
			pistaBean lista = new pistaBean();
			if (pistaObj instanceof pistaBean) {
			    	lista = (pistaBean) pistaObj;
			   }
	%>

		<h1>Realizar Reserva</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/realizarModificarReservaControllerServlet" method="post">
					
					<label>Fecha de la reserva: </label><input type="date" name="fecha" value="<%= request.getAttribute("fecha") %>" required="required" disabled/>
				   
				    <label>Tipo de la reserva: </label>
			        <select id="seleccionFormulario" name="seleccion" required="required" disabled onchange="mostrarCampos()">
			            <option value="<%= request.getAttribute("seleccion") %>"><%= request.getAttribute("seleccion") %></option>
			        </select>
			        
					<br></br>
					
			        <label>N&uacutemero de adultos: </label><input type="number" name="numero_adultos" value="<%= request.getAttribute("numero_adultos") %>" disabled />
			        
			        <label>N&uacutemero de ni&ntildeos:  </label><input type="number" name="numero_ninos" value="<%= request.getAttribute("numero_ninos") %>" disabled/>
			     
			        
			        <label>Pista: <select name="id_pista"> 
					
					<%for(int i=0;i<lista.getPistas().size();i++){ %>
						<option value="<%= lista.getPistas().get(i).getId_pista() %>"> <%= lista.getPistas().get(i).getNomPista() %></option> <% } %>
					</select>
					</label>
					
			       	<br></br>
			       	
			     	<label>Hora: </label><input type="time" name="hora" required="required" />
				   
				    <label>Duraci&oacuten: <select name="duracion"> 
						<option value="60">60 minutos</option>
						<option value="90">90 minutos</option>
						<option value="120">120 minutos</option>
					</select>
					</label>	
					
					<input type="hidden" name="id_reserva" value="<%= request.getAttribute("id_reserva") %>"/>
					
					<input type="hidden" name="numero_adultos" value="<%= request.getAttribute("numero_adultos") %>"/>
			        
			        <input type="hidden" name="numero_ninos" value="<%= request.getAttribute("numero_ninos") %>"/>
			        
			        <input type="hidden" name="fecha" value="<%= request.getAttribute("fecha") %>"/>
			        
			        <input type="hidden" name="seleccion" value="<%= request.getAttribute("tipo") %>"/>
			        
			        <input type="hidden" name="tipo" value="individual"/>	 
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>