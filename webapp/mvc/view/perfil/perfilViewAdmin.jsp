<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<jsp:useBean id = "listarUsuariosBean" scope = "session" class = "es.uco.pw.business.javabeans.listarUsuariosBean"></jsp:useBean>

<%@ page import="java.util.ArrayList" %>
<%@page import = "es.uco.pw.business.usuario.usuarioDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TU PERFIL</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
		<%if(customerBean==null || customerBean.getRol().equals("cliente")) {
			String parametro1 = "No cuenta con los permisos necesarios";
			response.sendRedirect(request.getContextPath()+"/index.jsp?message="+parametro1);
	}%>
	
	
	<%@include file="../../../includes/header_admin.jsp" %>
<% if(request.getAttribute("mensaje")!= null){ %>
<h3 style="text-align: center;"><%= request.getAttribute("mensaje") %></h3> <% } %>

	<!-- Nos muestra la información del usuario y nos muestra enlaces a las distintas funcionalidades como crear anuncios o mostrarlos -->
	<div class="perfil">

		<div class="imagen">
			<img  alt="Foto de perfil" width= 150px src="${pageContext.request.contextPath}/img/perfilAvatar.svg" />      
		</div>      
        
		<table>
	  		<tr>
	  			<th><h3 class="tituloPerfil">Nombre</h3></th>
	    		<th><h3 class="tituloPerfil">Antigüedad</h3></th>
	    		<th><h3 class="tituloPerfil">Nº Reservas</h3></th>
	 		</tr>
			<% ArrayList<usuarioDTO> users = listarUsuariosBean.getUsuarios();
			   ArrayList<String> reserva = listarUsuariosBean.getNreservas();
			for(int i=0;i<users.size();i++){%>
			<tr>
	    		<td><%out.println(users.get(i).getNombre()); %></td>
	    		<td><%out.println(users.get(i).getAntiguedad()); %></td>
	    		<td><%out.println(reserva.get(i)); %></td>
  			</tr>		
		<%} %>
		</table>

        <div class="container" >
        	<a href="${pageContext.request.contextPath}/mvc/view/modificar/modificarView.jsp"> Modifica tus datos</a>
        </div>
        <div class="container" >
        	<a href="../../control/login/logoutController.jsp">Cerrar Sesión</a>
        </div>       
    </div>
</body>

</html>