   <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> ${sessionScope.nombre} - Crear una pista</title>
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

		<h1>Registro de pista</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/crearPistaControllerServlet" method="post">

					<h3>La pista introducida ya existe, intentelo de nuevo </h3>
										
					<input type="text" name="nombre" placeholder="Introduzca el nombre de la pista" required="required" />
				   
				    <input type="number" name="num_max" placeholder="Introduce el numero maximo de karts:" required="required" />
				
					<br> <br>
					
					<h1>Seleccione el estado: </h1>
					
					<label class="container"><input type="checkbox" name="estado" value="disponible" onclick="uncheckEstado(this)"><span class="checkmark"></span>Disponible</label>
					
					<label class="container"><input type="checkbox" name="estado" value="mantenimiento" onclick="uncheckEstado(this)"><span class="checkmark"></span>En mantenimiento</label>
					
					<br> <br>
									
					<h1>Seleccione la dificultad: </h1>
					
					<label class="container"><input type="checkbox" name="dificultad" value="infantil"><span class="checkmark" onclick="uncheckDificultad(this)"></span>Infantil</label>
					
					<label class="container"><input type="checkbox" name="dificultad" value="familiar"><span class="checkmark" onclick="uncheckDificultad(this)"></span>Familiar</label>
					
					<label class="container"><input type="checkbox" name="dificultad" value="adultos"><span class="checkmark" onclick="uncheckDificultad(this)"></span>Adultos</label>
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>