<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BuyTech</title>
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
<%@ include file="header.jsp"%>
	<div class="container bg-white margin-30-top">
		<ol class="breadcrumb" itemscope=""
			itemtype="http://schema.org/BreadcrumbList">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">Inserimento Prodotto</li>
		</ol>

		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<div class="well margin-30-top">
						<%boolean ris=(boolean)request.getAttribute("ris");
						if (ris){%>
							<h2 class="alert alert-success">
								<i>Prodotto inserito con successo!</i>
							</h2>
							<%}else{%>
							<h2 class="alert alert-danger">
								<i>Prodotto non inserito!</i>
							</h2>
							<% }%>
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div style="height:20px"></div>
	<%@include file="footer.jsp"%> 
</body>
</html>