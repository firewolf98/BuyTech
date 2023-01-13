<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=((ClienteBean)session.getAttribute("utente")).getNome() %> <%=((ClienteBean)session.getAttribute("utente")).getCognome() %></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/profili.css">
<link rel="shortcut icon" type="image/x-icon" href="img/icona.ico">
</head>
<body>

<%@include file="header.jsp"%>

	<br>
	
	<div class="container">
		
		<%
			ClienteBean cliente = (ClienteBean) session.getAttribute("utente");
			String foto="img/profilo.jpg";
			if(!cliente.getFoto().equals("") && cliente.getFoto()!=null && !cliente.getFoto().equals(" ")) {
				foto = "data:image/jpg;base64," + cliente.getFoto();
			} 
			session.setAttribute("cliente", cliente);
		%>
		
		<div class="row">
		
			<div class="col-md-5">
					<img src=<%=foto%> class="img-responsive img-thumbnail">
			</div>
			
			<div class="col-md-5">
				<h1><%=cliente.getNome()+" "+cliente.getCognome() %></h1>
				<h4><%=cliente.getIndirizzo()+","%></h4>
				<h4><%=cliente.getCittà()+", "+cliente.getCap() %></h4>
				<h6>Email: <%=cliente.getEmail() %></h6>
				<h6>Telefono: <%=cliente.getTelefono() %></h6>
			</div>
			
			<div class="col-md-2" style="text-align:center">
			<br><br>
				<a class="btn btn-primary" href="modificaProfiloCliente.jsp">Modifica il tuo profilo</a>
				<br><br><br>
				<a class="btn btn-primary" href="ordiniPassati.jsp">I miei ordini</a>
				<br><br><br>
				<a href="EliminaAccount"><input type="button"
					class="btn btn-primary" value="Elimina Account"></a>
			</div>
			
		</div>
		
	</div>
	

<div style="height:20px"></div>
	<%@include file="footer.jsp"%>
</body>
</html>