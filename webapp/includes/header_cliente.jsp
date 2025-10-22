<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   

<header>
		<ul>
		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/perfil/perfilViewCliente.jsp">Inicio</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/reserva/consultarReservasView.jsp">Consultar reservas</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/pista/buscarPistaView.jsp">Buscar pista disponible</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/reserva/realizarReservaIndividualView.jsp">Realizar reserva individual</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/comprobarBonoControllerServlet">Realizar reserva con bono</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/obtenerReservasUsuarioServlet?modificar=si">Modificar reserva</a></li>
  	  	  <li><a class="active" href="${pageContext.request.contextPath}/obtenerReservasUsuarioServlet">Eliminar reserva</a></li>
  	  	  <li><a class="active" href="${pageContext.request.contextPath}/mvc/control/login/logoutController.jsp">Cerrar Sesi&oacuten</a></li>
		</ul>
</header>