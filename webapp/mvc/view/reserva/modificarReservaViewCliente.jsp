<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "es.uco.pw.business.reserva.reservaDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar reserva</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mostrarCampos.js"></script>
</head>
<body>
<%@include file="../../../includes/header_cliente.jsp" %>

<div class="login">

<% Object reservaObj = pageContext.findAttribute("reserva");
			   
			reservaDTO reserva = new reservaDTO();
			if (reservaObj instanceof reservaDTO) {
			    	reserva = (reservaDTO) reservaObj;
			   }

			request.setAttribute("reserva", reserva);
			String tipo = reserva.getTipo();
			String seleccion="";
			int numero_adultos=reserva.getNumero_adultos();
			int numero_ninos=reserva.getNumero_ninos();
			if(numero_adultos==0 && numero_ninos!=0){
				seleccion="infantil";
			}
			else if(numero_adultos!=0 && numero_ninos==0){
				seleccion="adultos";
			}
			else if(numero_adultos!=0 && numero_ninos!=0){
				seleccion="familiar";
			}
			

	%>

		<h1>Modificar Reserva</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/obtenerPistaModificarReservaControllerServlet" method="post">
					
					<label>Fecha de la reserva: </label><input type="date" name="fecha" value="<%= reserva.getFecha() %>" required="required"/>
				   
				   <% if(tipo.equals("bono")){ %>
				    <label>Tipo de la reserva: </label>
			        <select id="seleccionFormulario" name="seleccion" required="required" disabled onchange="mostrarCampos()">
			            <option value="<%= seleccion %>"><%= seleccion %></option>
			        </select> 
			        <br></br><% if(numero_ninos!=0){ %>
			        <label>N&uacutemero de ni&ntildeos:  </label><input type="number" name="numero_ninos" value="<%= reserva.getNumero_ninos() %>"/> 
			        <% } if(numero_adultos!=0){ %>
			        <label>N&uacutemero de adultos:  </label><input type="number" name="numero_adultos" value="<%= reserva.getNumero_adultos() %>"/> <% }
			        } %>
			        <% if(tipo.equals("individual")){ %>
				    <label>Tipo de la reserva: </label>
			        <select id="seleccionFormulario" name="seleccion" required="required" onchange="mostrarCampos()">
			            <option value="" disabled selected>Seleccione una opción</option>
			            <option value="infantil">Infantil</option>
			            <option value="familiar">Familiar</option>
			            <option value="adultos">Adultos</option>
			        </select>
			         
			        <label>N&uacutemero de adultos:  </label><input type="number" name="numero_adultos" value="<%= reserva.getNumero_adultos() %>"  style="display:none;" />
			        
			        <label>N&uacutemero de ni&ntildeos:  </label><input type="number" name="numero_ninos" value="<%= reserva.getNumero_ninos() %>" style="display:none;"/>
			        
			        <% } %>
			        
			        <input type="hidden" name="id_reserva" value="<%= reserva.getId_reserva() %>"/>
			        
			        <input type="hidden" name="id_pista" value="<%= reserva.getId_pista() %>"/>
			        
			        <input type="hidden" name="tipo" value="<%= reserva.getTipo() %>"/>
			        
			        <input type="hidden" name="seleccion_antigua" value="<%= seleccion %>"/>
			        
					<input type="submit" value="Enviar">
		</form>
</div>

</body>
</html>