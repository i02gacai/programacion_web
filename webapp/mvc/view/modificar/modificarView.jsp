<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Modifica tus datos</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
</head>
<body>
<% if(customerBean.getRol().equals("cliente")) { %>
	<%@include file="../../../includes/header_cliente.jsp"%> <%}

else{ %>
	<%@include file="../../../includes/header_admin.jsp"%> 
	<% } %>
	<div class="login">
		
		<!--  Modificaremos los datos que nos interese mediante un nuevo formulario y se actualizarán los datos del perfil del usuario -->
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="../../control/modificar/modificarController.jsp" method="post">
			<br><br>
			<h1>Modifique sus datos</h1>
			<h4>Nombre y apellidos: </h4><input type="text" name="nombre" value="<%=customerBean.getNombre()%>" required="required"  />
			<br><br>
			<h4>Contraseña: </h4> <input type="password" name="password" placeholder="Introduzca su contraseña" required="required" />
			<br><br>
			<h4>Correo: <%= customerBean.getCorreo() %> </h4>
			<br>
			<h4>Rol: <%= customerBean.getRol() %></h4>
			<br>
			<h4>Fecha de nacimiento:</h4> <input type="date" name="fecha_nacimiento" value="<%=customerBean.getFecha_nacimiento()%>" required="required"  />
			<br><br>
			<input type="submit" value="Enviar">
		</form>
	</div>
		
</body>
</html>