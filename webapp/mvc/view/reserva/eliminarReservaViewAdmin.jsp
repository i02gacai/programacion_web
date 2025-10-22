<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.reservaBean"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminar Reserva</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
		<%if(customerBean==null || customerBean.getRol().equals("cliente")) {
			String parametro1 = "No cuenta con los permisos necesarios";
			response.sendRedirect(request.getContextPath()+"/index.jsp?message="+parametro1);
	}%>
<%@include file="../../../includes/header_admin.jsp" %>

	<div class="login">

<%
		   // Obtenemos el valor de la variable 'lista_kart.karts' mediante Expression Language
		   Object reservaObj = pageContext.findAttribute("lista_reservas");
		   
		   // Verificamos si el objeto es de tipo ArrayList<Integer> y lo asignamos a la variable 'lista'
		   reservaBean lista = new reservaBean();
		   if (reservaObj instanceof reservaBean) {
		       lista = (reservaBean) reservaObj;
		   }
%>
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/eliminarReservaAdminControllerServlet" method="post">

			<label class="container">ID: <select name="id_reserva"> 
			
			<%for(int i=0;i<lista.getReservas().size();i++){ %>
				<option value="<%= lista.getReservas().get(i) %>" > <%=lista.getReservas().get(i)%></option> <% } %>
			</select>
			</label>
			
			<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>