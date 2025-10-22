    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="js/password.js"></script>
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
<title>Registro en el sitio Web</title>
<link rel="stylesheet" href="../../../css/registro.css">
</head>
<body>

	<div class="login">

		<h1>Registro de usuario</h1>
		
		<h3>Ese correo ya est� registrado</h3>

		<!-- Mediante un formulario y el m�todo post enviamos los datos al controlador que se encargar� de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="../../control/register/registerController" method="post">
					
					<input type="text" name="nombre" placeholder="Introduzca el nombre completo" required="required" />
				   
				    <input type="text" name="correo" placeholder="Introduzca su correo" required="required" />
				   
				    <input type="password" name="password" placeholder="Introduzca su contrase�a" required="required" />
				    
				    <input type="date" name="fecha_nacimiento" required="required" />
				
					<br> <br>
					
					<h1>Seleccione su rol: </h1>
					
					<label class="container"><input type="checkbox" name="rol" value="administrador" onclick="uncheckOther(this)"> <span class="checkmark"></span>Administrador</label>
					
					<label class="container"><input type="checkbox" name="rol" value="cliente" onclick="uncheckOther(this)"> <span class="checkmark"></span>Cliente</label>
					
					<input type="submit" value="Enviar">
		</form>
		<footer class="footer">
		 	<p> &copy; 2023 - i02gacai, Realizado por estudiantes de la UCO  </p>
		 	<a href="../../../index.jsp">Volver a inicio</a><i class="fas fa-id-card"></i>
		</footer>  
		<script>
		    function uncheckOther(checkbox) {
		        const checkboxes = document.getElementsByName("rol");
		        checkboxes.forEach(cb => {
		            if (cb !== checkbox) {
		                cb.checked = false;
		            }
		        });
		    }
		</script>
</div>
</body>
</html>
