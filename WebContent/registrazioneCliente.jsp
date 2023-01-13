<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" type="image/x-icon" href="img/icona.ico">
</head>
<body>
<%@include file="header.jsp"%>
	<div style="height:20px"></div>
	<div class="container bg-white margin-30-top">
		<ol class="breadcrumb" itemscope=""
			itemtype="http://schema.org/BreadcrumbList">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">Registrati Cliente</li>
		</ol>

		<div class="row margin-30-top">
					<div  id="left-box" class="panel panel-default panel-white col-md-12 col-md-6 col-md-offset-3 bg-light">
							<h1 align="center">Inserisci i tuoi dati</h1>
							<form action="ServletRegCliente" method="post" id="form1">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="nome" id="nome"
											placeholder="Nome" required>
										<span class="error">Il nome deve contenere solo lettere dell'alfabeto</span><br>	
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="cognome" id="cognome"
											placeholder="Cognome" required>
										<span class="error">Il cognome deve contenere solo lettere dell'alfabeto</span><br>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" name="email" id="email"
											placeholder="E-mail" required>
											<span class="error">E-mail non disponibile</span><br>
										<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="username" id="username"
											placeholder="Crea username" required>
											<span class="error">Username non disponibile o formato non corretto (solo caratteri alfanumerici,lunghezza 3-20)</span><br>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<input type="password" class="form-control" name="password" id="password"
											placeholder="Crea password" required>
											<span class="error">Non sono ammessi caratteri speciali, inoltre la password deve essere lunga almeno 8 caratteri</span>
										<br>
										<br>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="indirizzo" id="indirizzo"
											placeholder="Via/Viale Indirizzo n." required>
										<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="citta" id="citta"
											placeholder="Città" required>
										<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="cap" id="cap"
											placeholder="Cap" required>
										<span class="error">Formato non corretto o lunghezza errata (5 numeri)</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="telefono" id="telefono"
											placeholder="Telefono" required>
										<span class="error">Formato non corretto o lunghezza errata (9 o 10 numeri)</span><br>
									</div>
								</div>
								<div class="col-sm-offset-5 col-md-8">
									<button type="submit" class="btn btn-primary">Registrati</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<div style="height:20px"></div>
	<%@include file="footer.jsp"%>
				
<script src="scripts/jquery.js"></script>
<script src="scripts/verificaUsername.js"></script>
<script src="scripts/verificaEmail.js"></script>
<script src="scripts/validazioneCliente.js"></script>
</body>
</html>