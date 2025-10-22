<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.reservaBean"%>
<%@page import = "java.time.LocalDate"%>
<%@page import = "java.time.format.DateTimeFormatter"%>
<%@page import = "java.time.Period"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Reservas</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tablas.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<%@include file="../../../includes/header_cliente.jsp" %>

	<div class="login">

<%			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		   // Obtenemos el valor de la variable 'lista_kart.karts' mediante Expression Language
		   Object reservaObj = pageContext.findAttribute("lista_reservas");
		   
		   // Verificamos si el objeto es de tipo ArrayList<Integer> y lo asignamos a la variable 'lista'
		   reservaBean lista = new reservaBean();
		   if (reservaObj instanceof reservaBean) {
		       lista = (reservaBean) reservaObj;
		   }
%>			
			<h1>Reservas finalizadas</h1>
			<table>
	  		<tr>
	  			<th><h3 class="tituloPerfil">Id Reserva</h3></th>
	    		<th><h3 class="tituloPerfil">Tipo</h3></th>
	    		<th><h3 class="tituloPerfil">Precio</h3></th>
	    		<th><h3 class="tituloPerfil">Duracion</h3></th>
	    		<th><h3 class="tituloPerfil">Descuento</h3></th>
	    		<th><h3 class="tituloPerfil">Hora</h3></th>
	    		<th><h3 class="tituloPerfil">Fecha</h3></th>
	    		<th><h3 class="tituloPerfil">Usuario</h3></th>
	    		<th><h3 class="tituloPerfil">id_pista</h3></th>
	    		<th><h3 class="tituloPerfil">N&uacutemero Adultos</h3></th>
	    		<th><h3 class="tituloPerfil">N&uacutemero Ni&ntildeos</h3></th>
	    		
	 		</tr>
	 		
	 		<%for(int i=0;i<lista.getLista_reservas().size();i++){ %>
	 		<%if(lista.getLista_reservas().get(i).getFecha().isBefore(LocalDate.now())){ %>
	 		<tr>
				<td><%out.println(lista.getLista_reservas().get(i).getId_reserva()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getTipo()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getPrecio()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getDuracion()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getDescuento()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getHora()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getFecha().format(formatter)); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getUsuario()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getId_pista()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getNumero_adultos()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getNumero_ninos()); %></td>
			</tr>
			<% } }%>
			</table>
			
			
			<h1>Reservas futuras</h1>
			<table>
	  		<tr>
	  			<th><h3 class="tituloPerfil">Id Reserva</h3></th>
	    		<th><h3 class="tituloPerfil">Tipo</h3></th>
	    		<th><h3 class="tituloPerfil">Precio</h3></th>
	    		<th><h3 class="tituloPerfil">Duracion</h3></th>
	    		<th><h3 class="tituloPerfil">Descuento</h3></th>
	    		<th><h3 class="tituloPerfil">Hora</h3></th>
	    		<th><h3 class="tituloPerfil">Fecha</h3></th>
	    		<th><h3 class="tituloPerfil">Usuario</h3></th>
	    		<th><h3 class="tituloPerfil">id_pista</h3></th>
	    		<th><h3 class="tituloPerfil">N&uacutemero Adultos</h3></th>
	    		<th><h3 class="tituloPerfil">N&uacutemero Ni&ntildeos</h3></th>
	    		
	 		</tr>
	 		
	 		<%for(int i=0;i<lista.getLista_reservas().size();i++){ %>
	 		<%if(lista.getLista_reservas().get(i).getFecha().isAfter(LocalDate.now())){ %>
	 		<tr>
				<td><%out.println(lista.getLista_reservas().get(i).getId_reserva()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getTipo()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getPrecio()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getDuracion()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getDescuento()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getHora()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getFecha().format(formatter)); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getUsuario()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getId_pista()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getNumero_adultos()); %></td>
	    		<td><%out.println(lista.getLista_reservas().get(i).getNumero_ninos()); %></td>
			</tr>
			<% } }%>
			</table>
			
	</div>
</body>
</html>