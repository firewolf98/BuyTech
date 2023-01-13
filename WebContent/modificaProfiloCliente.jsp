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
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="height:20px"></div>
	
	<%ClienteBean cliente = (ClienteBean) session.getAttribute("utente"); %>
	
	<div class="container">
	<div align="center"><h3>Qui puoi modificare i tuoi dati...</h3></div>
		<div class="row">
			<form action="ModificaDatiPersonali" method="post" enctype="multipart/form-data" id="form1">
			<div class="col-md-12">
			<h3>Dati personali</h3>
			</div>
				<div class="col-md-6">
					<p>Email:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="email" id="email"
							placeholder=<%=cliente.getEmail() %>>
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Nuova Password:</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordN" id="passwordN"
							placeholder="Inserisci nuova password">
							<span class="error">Formato errato</span><br>
							<input type="checkbox" onclick="showPassword()">Show Password
					</div>
				</div>
				<div class="col-md-6">
					<p>Foto:</p>
					<div class="form-group">
						<input type="file" class="form-control" name="foto"
							 placeholder="Foto">
					</div>
				</div>
				<div class="col-md-6">
					<p>Indirizzo:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="indirizzo" id="indirizzo"
							placeholder=<%=cliente.getIndirizzo() %>>
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Città:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="citta" id="city" placeholder="<%=cliente.getCittà()%>">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Cap:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="cap" id="cap" placeholder="<%=cliente.getCap()%>">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Telefono:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="telefono" id="telefono" placeholder="<%=cliente.getTelefono()%>">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Modifica
						dati</button>
				</div>
			</form>
		</div>
	</div>
	
	<div style="height:20px"></div>
	<%@include file="footer.jsp"%> 
	
<script src="scripts/verificaFormModifica.js"></script>	
<script src="scripts/showPasswordModifica.js"></script>	
</body>
</html>