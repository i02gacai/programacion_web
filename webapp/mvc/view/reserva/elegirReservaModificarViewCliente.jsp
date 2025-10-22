<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.reservaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Reserva</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<%@include file="../../../includes/header_cliente.jsp" %>
<%@page import = "java.time.format.DateTimeFormatter"%>

	<div class="login">

<%
		   // Obtenemos el valor de la variable 'lista_kart.karts' mediante Expression Language
		   Object reservaObj = pageContext.findAttribute("lista_reservas");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		   // Verificamos si el objeto es de tipo ArrayList<Integer> y lo asignamos a la variable 'lista'
		   reservaBean lista = new reservaBean();
		   if (reservaObj instanceof reservaBean) {
		       lista = (reservaBean) reservaObj;
		   }
%>
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/modificarReservaClienteControllerServlet" method="post">

			<label class="container">Fecha de la reserva a modificar: <select name="id_reserva"> 
			
			<%for(int i=0;i<lista.getLista_reservas().size();i++){ %>
				<option value="<%= lista.getLista_reservas().get(i).getId_reserva() %>" > <%=lista.getLista_reservas().get(i).getFecha().format(formatter) %></option> <% } %>
			</select>
			</label>
			
			<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>