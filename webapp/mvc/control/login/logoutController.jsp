<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.javabeans.customerBean"></jsp:useBean>
<jsp:useBean id = "listarUsuariosBean" scope = "session" class = "es.uco.pw.business.javabeans.listarUsuariosBean"></jsp:useBean>

<%
	// Para desconectar al usuario, se accede al método removeAttribute() de la sesión)
	request.getSession().removeAttribute("customerBean");
	request.getSession().removeAttribute("listarUsuariosBean");
	String nextPage = "../../../index.jsp";
	String mensajeNextPage = request.getParameter("message");
	if (mensajeNextPage == null) mensajeNextPage = "";

%>

 <jsp:forward page="<%=nextPage%>">
 	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
 </jsp:forward>
