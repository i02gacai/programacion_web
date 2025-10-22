<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear un kart</title>
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

		<h1>Registro de kart</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/crearKartControllerServlet" method="post">
				
					<br> <br>
					
					<h1>Seleccione el estado: </h1>
					
					<label class="container"><input type="checkbox" name="estado" value="disponible" onclick="uncheckEstado(this)"><span class="checkmark"></span>Disponible</label>
					
					<label class="container"><input type="checkbox" name="estado" value="mantenimiento" onclick="uncheckEstado(this)"><span class="checkmark"></span>En mantenimiento</label>

					<label class="container"><input type="checkbox" name="estado" value="reservado" onclick="uncheckEstado(this)"><span class="checkmark"></span>Reservado</label>
													
					<br> <br>
									
					<h1>Seleccione el tipo: </h1>
					
					<label class="container"><input type="checkbox" name="tipo" value="niño" onclick="uncheckTipo(this)"><span class="checkmark"></span>Niño</label>
					
					<label class="container"><input type="checkbox" name="tipo" value="adulto" onclick="uncheckTipo(this)"><span class="checkmark"></span>Adulto</label>
									
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>