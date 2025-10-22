<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "es.uco.pw.dao.usuarioDAO" %>
<%@page import = "es.uco.pw.business.usuario.usuarioDTO"%>
<%@page import = "es.uco.pw.business.usuario.rol"%>
<%@page import = "java.time.LocalDate"%>
<%@page import = "java.sql.SQLException"%>



<% 

	String BDdriver = application.getInitParameter("BDdriver");
	String BDurl = application.getInitParameter("BDurl");
	String BDuser = application.getInitParameter("BDuser");
	String BDpass = application.getInitParameter("BDpass");
	
	usuarioDTO u = new usuarioDTO();
	
	String nombre = request.getParameter("nombre");
	String correo = request.getParameter("correo");
	String password = request.getParameter("password");
	
	String fecha_nacimiento = request.getParameter("fecha_nacimiento");
	
	fecha_nacimiento = String.format(fecha_nacimiento, "yyyy/mm/dd");
	LocalDate fecha_nac = LocalDate.parse(fecha_nacimiento);
	
	
	LocalDate fecha_inscripcion = LocalDate.now();
	
	rol Rol = rol.valueOf(request.getParameter("rol"));
	
	
	try 
	{
		usuarioDAO DatosDao = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
		
		boolean registro = DatosDao.comprobarRegistro(correo);
		
		if(!registro) 
		{
			u.setNombre(nombre);
			u.setCorreo(correo);
			u.setPassowrd(password);
			u.setFechaNacimiento(fecha_nac);
			u.setRol(Rol);
			u.setFechaInscripcion(fecha_inscripcion);
			
			
			DatosDao.save(u);
			DatosDao.save_info(u);
			
			response.sendRedirect("../../view/login/loginView.jsp");
		}else {
			
			response.sendRedirect("../../register/registerViewFail.jsp");
			
		}
		
		
	}
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



%>