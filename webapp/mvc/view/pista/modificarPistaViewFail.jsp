<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica la pista</title>
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
		
		<!--  Modificaremos los datos que nos interese mediante un nuevo formulario y se actualizarán los datos del perfil del usuario -->
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/modificarPistaControllerServlet" method="post">
			<br><br>
			
			<h3>La pista introducida no existe en la BD, intente de nuevo</h3>
		
			<h1>Modifique los datos de la pista</h1>
			<input type="text" name="nombre" placeholder="Introduzca el nombre de la pista" required="required" />
						
			<input type="text" name="nombre_nuevo" placeholder=" Nuevo nombre de la pista (opcional)"/>
				   
				    <input type="number" name="num_max" placeholder="Introduce el numero maximo de karts:" required="required" />
				
					<br> <br>
					
					<h1>Seleccione el estado: </h1>
					
					<label class="container"><input type="checkbox" name="estado" value="disponible" onclick="uncheckEstado(this)"><span class="checkmark"></span>Disponible</label>
					
					<label class="container"><input type="checkbox" name="estado" value="mantenimiento" onclick="uncheckEstado(this)"><span class="checkmark"></span>En mantenimiento</label>
					
					<br> <br>
									
					<h1>Seleccione la dificultad: </h1>
					
					<label class="container"><input type="checkbox" name="dificultad" value="infantil" onclick="uncheckDificultad(this)"><span class="checkmark"></span>Infantil</label>
					
					<label class="container"><input type="checkbox" name="dificultad" value="familiar" onclick="uncheckDificultad(this)"><span class="checkmark"></span>Familiar</label>
					
					<label class="container"><input type="checkbox" name="dificultad" value="adultos" onclick="uncheckDificultad(this)"><span class="checkmark"></span>Adultos</label>
					
					<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>