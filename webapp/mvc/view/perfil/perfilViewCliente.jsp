<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<%@page import = "java.time.LocalDate"%>
<%@page import = "java.time.format.DateTimeFormatter"%>
<%@page import = "java.time.Period"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TU PERFIL</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
	<%if(customerBean==null || !customerBean.getRol().equals("cliente")) {
		RequestDispatcher rd;
		request.setAttribute("mensaje", "Se ha producido un error: No cuenta con los permisos necesarios o no ha iniciado sesión.");
		rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp");
		rd.forward(request, response);
	}%>
	
	
	<%@include file="../../../includes/header_cliente.jsp" %>
	
	<% if(request.getAttribute("mensaje")!= null){ %>
	<h3 style="text-align: center;"><%= request.getAttribute("mensaje") %></h3> <% } %>

	<!-- Nos muestra la información del usuario y nos muestra enlaces a las distintas funcionalidades como crear anuncios o mostrarlos -->
	<div class="perfil">
	
		<div class="imagen">
			<img  alt="Foto de perfil" width= 150px src="${pageContext.request.contextPath}/img/perfilAvatar.svg" />      
		</div>      
        
        <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Formatea la fecha como una cadena
        String fecha_Actual = customerBean.getFecha_actual().format(formatter);
        LocalDate fechaHoy = LocalDate.now();
    	Period periodo = Period.between(customerBean.getFecha_inscripcion(), fechaHoy);
        int diferencia = periodo.getYears();%>
 
        <h3 class="tituloPerfil"> Bienvenido, <%= customerBean.getNombre() %></h3>
        <h3 class="tituloPerfil"> Fecha Actual: <%= fecha_Actual %></h3>
        <h3 class="tituloPerfil">Antig&uumledad: <%= diferencia %> a&ntildeos</h3>

        <div class="container" >
        	<a href="${pageContext.request.contextPath}/mvc/view/modificar/modificarView.jsp"> Modifica tus datos</a>
        </div>
        <div class="container" >
        	<a href="${pageContext.request.contextPath}/mvc/control/login/logoutController.jsp">Cerrar Sesión</a>
        </div>       
    </div>

        	<% if(customerBean.getFecha_reserva()!=null){ 
        	String fecha_Reserva = customerBean.getFecha_reserva().format(formatter); %>
		 	<p id="text"  style="font-size: 24px;" > La fecha de tu pr&oacutexima reserva es <%= fecha_Reserva %>  </p>
		 	<script src="${pageContext.request.contextPath}/js/texto_movimiento.js"></script> <% } %>
</body>

</html>