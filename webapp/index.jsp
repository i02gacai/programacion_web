<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Karting El "Nano" Alonso</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    
</head>
<body>
	<% String messageNextPage = request.getParameter("message");
	if (messageNextPage == null) messageNextPage = ""; %>
	<div  class="container">
    <h1>Karting El "Nano" Alonso</h1>
    <h3 style="color: #FF0000; text-align:center;"><%= messageNextPage %></h3>
    <button onclick="window.location.href = '${pageContext.request.contextPath}/mvc/view/register/registerView.jsp';">Registrarse</button>
	<button onclick="window.location.href = '${pageContext.request.contextPath}/mvc/view/login/loginView.jsp';">Acceder</button>
    </div>
    
    <footer class="footer">
		 	<p> &copy; 2023 - i02gacai, Realizado por estudiantes de la UCO  </p>
	</footer>  
</body>
</html>
