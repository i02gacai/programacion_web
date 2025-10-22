<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.kartBean"%>
<%@page import = "es.uco.pw.business.javabeans.pistaBean"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Asociar Kart a Pista</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validarFormatoPassword.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkbox.js"></script>
</head>
<body>
		<%if(customerBean==null || customerBean.getRol().equals("cliente")) {
			String parametro1 = "No cuenta con los permisos necesarios";
			response.sendRedirect(request.getContextPath()+"/index.jsp?message="+parametro1);
	}%>

<%@include file="../../../includes/header_admin.jsp" %>

	<div class="login">
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/asociarKartPistaControllerServlet" method="post">
				<br><br>
				<h1>Asocie un kart a una pista</h1>
				<%
			   // Obtenemos el valor de la variable 'lista_kart.karts' mediante Expression Language
			   Object kartsObj = pageContext.findAttribute("lista_kart");
			   
			   // Verificamos si el objeto es de tipo ArrayList<Integer> y lo asignamos a la variable 'lista'
			   kartBean lista = new kartBean();
			   if (kartsObj instanceof kartBean) {
			       lista = (kartBean) kartsObj;
			   }
	%>
	
				<label class="container">Kart: <select name="id_kart"> 
				
				<%for(int i=0;i<lista.getKarts().size();i++){ %>
					<option value="<%= lista.getKarts().get(i) %>" > <%=lista.getKarts().get(i)%></option> <% } %>
				</select>
				</label>
				
				<% Object kartsObj1 = pageContext.findAttribute("lista_pistas");
			   
			   // Verificamos si el objeto es de tipo ArrayList<Integer> y lo asignamos a la variable 'lista'
			   pistaBean lista1 = new pistaBean();
			   if (kartsObj1 instanceof pistaBean) {
			       lista1 = (pistaBean) kartsObj1;
			   }
				%>
				
				<label class="container">Pista: <select name="id_pista"> 
				
				<%for(int i=0;i<lista1.getPistas().size();i++){ %>
					<option value="<%= lista1.getPistas().get(i).getId_pista() %>" > <%= lista1.getPistas().get(i).getNomPista() %></option> <% } %>
				</select>
				</label>
				
				<input type="submit" value="Enviar">
		</form>
	</div>

</body>
</html>