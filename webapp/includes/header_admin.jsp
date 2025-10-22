<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<header>
		<ul>
		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/perfil/perfilViewAdmin.jsp">Inicio</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/pista/crearPistaView.jsp">Crear pista</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/mvc/view/kart/crearKartView.jsp">Crear kart</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/obtenerKartsServlet?asociar=si">Asociar Kart y pistas</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/obtenerKartsServlet">Modificar Kart</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/obtenerPistasServlets">Modificar Pista</a></li>
  	  	  <li><a class="active" href="${pageContext.request.contextPath}/obtenerReservasServlet">Eliminar Reservas</a></li>
  	  	  <li><a class="active" href="${pageContext.request.contextPath}/mvc/control/login/logoutController.jsp">Cerrar Sesi&oacuten</a></li>
		</ul>
</header>