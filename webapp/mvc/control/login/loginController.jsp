<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "es.uco.pw.dao.usuarioDAO" %>
<%@page import = "es.uco.pw.business.usuario.usuarioDTO"%>
<%@page import = "java.time.LocalDate"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Properties"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<jsp:useBean id = "listarUsuariosBean" scope = "session" class = "es.uco.pw.business.javabeans.listarUsuariosBean"></jsp:useBean>

<%
// Posibles flujos:
//	1) customerBean esta logado (!= null && != "") -> Se redirige al index.jsp
//	2) No hay parametros en el request -> procede de otra funcionalidad o index.jsp



// Caso 2
if(customerBean == null || customerBean.getCorreo().equals(""))
{
	String correo = request.getParameter("correo");
	String password = request.getParameter("password");
	
	String BDdriver = application.getInitParameter("BDdriver");
	String BDurl = application.getInitParameter("BDurl");
	String BDuser = application.getInitParameter("BDuser");
	String BDpass = application.getInitParameter("BDpass");
	
	Properties sql = new Properties();
    sql.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
	
	//Caso 2.a: Hay parametros -> procede de la VISTA
	if(correo != null)
	{
		//Se accede a la BD para obtener el usuario
		usuarioDAO userDAO = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
		
		boolean userValidate = userDAO.validarUsuario(correo, password,sql); // Comprueba si el usuario y la contraseña son correctos
				
		//Se realizan todas las comprobaciones necesarias del dominio
		//Aqui comprobamos que exista el usuario
		
		if(userValidate == true)
		{
			//Usuario valido
%>
<jsp:setProperty property = "correo" value = "<%=correo%>" name="customerBean"/>	
<%
			usuarioDTO usuario = new usuarioDTO();
			usuario =	userDAO.datosUsuarioPorEmail(correo,sql); //Recupera todos los datos del usuario
			LocalDate fechaHoy = LocalDate.now();
			 
			String Rol = String.valueOf(usuario.getRol());
			
			%>
			<jsp:setProperty property = "nombre" value = "<%=usuario.getNombre()%>" name="customerBean"/>	
			<jsp:setProperty property = "fecha_nacimiento" value = "<%=usuario.getFechaNacimiento()%>" name="customerBean"/>	
			<jsp:setProperty property = "fecha_inscripcion" value = "<%=usuario.getFechaInscripcion()%>" name="customerBean"/>
			<jsp:setProperty property = "rol" value = "<%=Rol%>" name="customerBean"/>
			<jsp:setProperty property = "fecha_actual" value = "<%=fechaHoy%>" name="customerBean"/>
			
			<%
			if(customerBean.getRol().equals("cliente"))
			{	
				LocalDate fecha = userDAO.getFecha_proxima_reserva(correo,sql);
				%><jsp:setProperty property = "fecha_reserva" value = "<%=fecha%>" name="customerBean"/><% 
				response.sendRedirect("../../view/perfil/perfilViewCliente.jsp"); // Puede ser una URL absoluta o relativa
				
			}
			else
			{	
				ArrayList<String> reservas = new ArrayList(); 
				ArrayList<usuarioDTO> users = userDAO.listarUsuariosRegistrados(sql); //Obtiene una lista de todos los usuarios registrados
				for(int i=0;i<users.size();i++){
					reservas.add(userDAO.getNReservas(users.get(i).getCorreo(),LocalDate.now(),sql)); } //Obtiene el numero de reservas de un usuario
				
			%>	<jsp:setProperty property = "usuarios" value = "<%=users%>" name="listarUsuariosBean"/>
				<jsp:setProperty property = "nreservas" value = "<%=reservas%>" name="listarUsuariosBean"/> <%
				
				response.sendRedirect("../../view/perfil/perfilViewAdmin.jsp");
			}
		}
		else
		{
			// Usuario no valido
			response.sendRedirect("../../view/login/loginViewFail.jsp");
		}
		//Caso 2.b - Se debe ir a la vista por primera vez
		

	}
	else
	{
		response.sendRedirect("../../view/login/loginViewFail.jsp");
	}
}
%>
