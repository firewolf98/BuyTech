<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati a BuyTech</title>
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
	<%@ include file="header.jsp" %>
<div style="height:20px"></div>
<div class="container bg-white margin-30-top">
        <ol class="breadcrumb" itemscope="" itemtype="http://schema.org/BreadcrumbList">
            <li><a href="index.jsp">Home</a></li>
    <li class="active">Registrati</li>
    </ol>
    
    <div class="row">
            <div class="col-md-12">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="well margin-30-top">
                
                 
                    <h2>Come ti vuoi registrare?</h2>
                    <hr>


                    <div class="bottoni-top padding-10-top-bott">
                        <a href="registrazioneCliente.jsp" class="btn btn-info btn-block butt-cliente"><small><label>Pronto ad acquistare i migliori prodotti al miglior prezzo?</label></small><br>REGISTRATI COME CLIENTE</a><hr>

                    </div>

                    <div class="bottoni-top padding-10-top-bott">
                        <a href="registrazioneVenditore.jsp" class="btn btn-primary btn-block butt-venditore"><small><label>Vuoi vendere?</label></small><br>REGISTRATI COME VENDITORE</a>
                    </div>

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