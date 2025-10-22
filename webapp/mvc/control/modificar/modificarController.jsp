<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "es.uco.pw.dao.usuarioDAO" %>
<%@page import = "es.uco.pw.business.usuario.usuarioDTO"%>
<%@page import = "java.time.LocalDate"%>

<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>


<% 

String BDdriver = application.getInitParameter("BDdriver");
String BDurl = application.getInitParameter("BDurl");
String BDuser = application.getInitParameter("BDuser");
String BDpass = application.getInitParameter("BDpass");

usuarioDTO usuario = new usuarioDTO();

String nombre = request.getParameter("nombre");
String password = request.getParameter("password");

String fecha_nacimiento = request.getParameter("fecha_nacimiento");


fecha_nacimiento = String.format(fecha_nacimiento, "yyyy/mm/dd");
LocalDate fecha_nac = LocalDate.parse(fecha_nacimiento);

try 
{
	//Salta el warning porque no se usa el objeto pero es necesario crearlo para pasarle las variables de la base de datos.
	usuarioDAO DatosDao = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
	
	usuario.setNombre(nombre);
	usuario.setPassowrd(password);
	usuario.setFechaNacimiento(fecha_nac);
	usuario.setFechaInscripcion(customerBean.getFecha_inscripcion());
	
	
	DatosDao.updatePassword(usuario, customerBean.getCorreo());
	DatosDao.updateDatos(usuario, customerBean.getCorreo());
	
	//response.sendRedirect("../../control/login/logoutController.jsp");
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

%>

<jsp:forward page="../../control/login/logoutController.jsp">
 	<jsp:param value="&iexclDatos modificados con &eacutexito! Inicie sesi&oacuten de nuevo" name="message"/>
 </jsp:forward>