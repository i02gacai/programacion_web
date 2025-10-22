<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.javabeans.kartBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Modifica el kart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validarFormatoPassword.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkbox.js"></script>
</head>
<body>
<%@include file="../../../includes/header_admin.jsp" %>
	<div class="login">
		
		<!--  Modificaremos los datos que nos interese mediante un nuevo formulario y se actualizarán los datos del perfil del usuario -->
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/modificarKartControllerServlet" method="post">
			<br><br>
			<h1>Modifique sus datos</h1>
			<%
		   // Obtenemos el valor de la variable 'lista_kart.karts' mediante Expression Language
		   Object kartsObj = pageContext.findAttribute("lista_kart");
		   
		   // Verificamos si el objeto es de tipo ArrayList<Integer> y lo asignamos a la variable 'lista'
		   kartBean lista = new kartBean();
		   if (kartsObj instanceof kartBean) {
		       lista = (kartBean) kartsObj;
		   }
%>

			<label class="container">ID: <select name="id_kart"> 
			
			<%for(int i=0;i<lista.getKarts().size();i++){ %>
				<option value="<%= lista.getKarts().get(i) %>" > <%=lista.getKarts().get(i)%></option> <% } %>
			</select>
			</label>
			
					
					<br> <br>
					
					<h1>Seleccione el estado: </h1>
					
					<label class="container"><input type="checkbox" name="estado" value="disponible" onclick="uncheckEstado(this)"><span class="checkmark"></span>Disponible</label>
					
					<label class="container"><input type="checkbox" name="estado" value="mantenimiento" onclick="uncheckEstado(this)"><span class="checkmark"></span>En mantenimiento</label>

					<label class="container"><input type="checkbox" name="estado" value="reservado" onclick="uncheckEstado(this)"><span class="checkmark"></span>Reservado</label>
													
					<br> <br>
									
					<h1>Seleccione el tipo: </h1>
					
					<label class="container"><input type="checkbox" name="tipo" value="niño" onclick="uncheckTipo(this)"><span class="checkmark"></span>Niño</label>
					
					<label class="container"><input type="checkbox" name="tipo" value="adulto"onclick="uncheckTipo(this)"><span class="checkmark"></span>Adulto</label>
										
					<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>