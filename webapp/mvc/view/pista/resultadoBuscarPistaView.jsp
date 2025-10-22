<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.pistaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar Pista</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>


	<% Object pistaObj = pageContext.findAttribute("lista_pistas");
			   
			pistaBean lista = new pistaBean();
			if (pistaObj instanceof pistaBean) {
			    	lista = (pistaBean) pistaObj;
			   }
	%>
	
<%@include file="../../../includes/header_cliente.jsp" %>
	
<div class="login">
	<h1>Pistas</h1>
			<table style=" width:300px; position:relative; left:110px; text-align:center;">
	  		<tr>
	  			<th><h3 class="tituloPerfil">Id Pista</h3></th>
	    		<th><h3 class="tituloPerfil">Nombre</h3></th>
	    		
	 		</tr>
	 		<%if(lista.getPistas()!=null){
	 			for(int i=0;i<lista.getPistas().size();i++){ %>
	 			<tr>
	 				<td><% out.println(lista.getPistas().get(i).getId_pista()); %></td>
	 				<td><% out.println(lista.getPistas().get(i).getNomPista()); %></td>
	 			</tr>
	 		<% } }%>
	 		</table>
</div>				
				
</body>
</html>