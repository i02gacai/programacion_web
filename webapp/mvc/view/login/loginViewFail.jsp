<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="shortcut icon" href="../../../img/favicon.ico">


  </head>
  <body>
	
    <div class="login">
      <h1 class=sombra>Inicio de Sesión</h1>
      <h3>Error al iniciar sesion</h3>
	  
	  <!-- Se introducen los datos en el formulario para enviarlos mediante el método post -->
	  <form method="post" action="../../control/login/loginController.jsp"> <!--FORMULARIO-->   
        
        <div class="campos">
        	<!-- Correo INPUT -->
        	<input type="text" placeholder="Introduce el correo" name="correo" required>
        	<br>
   
        	<!-- CONTRASEÑA INPUT -->
        	<input type="password" placeholder="Introduce la contraseña" name="password" required>
	
        	<br>
        	<br>
        	<input type="submit" name="inicio" id="a" class="boton-personalizado" value="Iniciar Sesión"><br>        
		</div>  
      </form>
    </div>
    
    	<footer class="footer">
		 	<p> &copy; 2023 - i02gacai, Realizado por estudiantes de la UCO  </p>
		 	<a href="../../../index.jsp">Volver a inicio</a><i class="fas fa-id-card"></i>
		</footer>  
    
  </body>
</html>