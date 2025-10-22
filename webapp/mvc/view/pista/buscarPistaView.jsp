<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar pista</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mostrarFormulario.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkbox.js"></script>
</head>
<body>

<%@include file="../../../includes/header_cliente.jsp" %>
	
	<div class="login">
		<h1>Buscar pista</h1>
		
		<label for="seleccionFormulario">Selecciona el criterio para buscar pista:</label>
	    <select id="seleccionFormulario" onchange="mostrarFormulario()">
	        <option value="0">Seleccione una opci&oacuten</option>
	        <option value="1">Tipo de pista</option>
	        <option value="2">Fecha</option>
	        <option value="3">Karts disponibles</option>
	    </select>
		    
    <div id="formularioContenedor">
        <form id="form1" style="display: none;" action="/practica3/buscarPistaDificultadControllerServlet" method="post">
        
           	<label class="container"><input type="checkbox" name="dificultad" value="infantil"><span class="checkmark" onclick="uncheckDificultad(this)"></span>Infantil</label>
					
			<label class="container"><input type="checkbox" name="dificultad" value="familiar"><span class="checkmark" onclick="uncheckDificultad(this)"></span>Familiar</label>
					
			<label class="container"><input type="checkbox" name="dificultad" value="adultos"><span class="checkmark" onclick="uncheckDificultad(this)"></span>Adultos</label>
          	
          	<input type="submit" value="Enviar">
        </form>
        
        <form id="form2" style="display: none;" action="/practica3/buscarPistaFechaControllerServlet" method="post">
        
            <input type="date" name="fecha" required="required">
            
            <input type="submit" value="Enviar">
        </form>
        
        <form id="form3" style="display: none;" action="/practica3/buscarPistaKartControllerServlet" method="post">
        
            <input type="number" name="karts_disponibles" placeholder="Introduce el numero de karts" required="required" />
            
            <input type="submit" value="Enviar">
        </form>
        
        
		
		
	</div>
</body>
</html>