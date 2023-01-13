<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
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
<style>
#id1 {
	border-radius: 50%;
	width:100px;
	height: 100px;
	
}

.desc {
	text-align: center;
}



#text {
	min-height: 150px;
	padding-top: 60px;
}
</style>
<body>

	<%@ include file="header.jsp"%>
	<div style="height:20px"></div>
	
	<%
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		ArrayList<OrdineBean> ordini=(ArrayList<OrdineBean>)session.getAttribute("ordini");
	%>
	<%
		if (carrello != null) {
			float costoTot = 0;
			ArrayList<ProdottoBean> prodotti = carrello.getProdotti();
	%>
	<div class="container">
	
		<h3 class="text-center">Il mio carrello</h3><br>
		
		<%
		
			if (prodotti.size()>0&&ordini.size()>0){
				
			for (int i = 0; i < prodotti.size(); i++) {
					ProdottoBean x=prodotti.get(i);
					costoTot += ordini.get(i).getCostoTotale();
		%>

		<div class="row">
		<div class="col-md-5">

			<div>
				<%
					String foto = "data:image/jpg;base64," + x.getFoto();
				%>

				<img src=<%=foto%> width="130" height="130" id="id1"
					style="float: left; padding-right: 3px;">

				<h4 class="center"><%=x.getNome()%></h4>

				<p>
					Quantità: <b><%=ordini.get(i).getQuantita()%></b>
				</p>
				<%String descrizione=x.getDescrizione(); 
				if (descrizione==null) descrizione="";%>
				<p><%=descrizione%></p>
			</div>

		</div>

		<div class="col-md-4">

			<h4 style="border: 4px solid grey; padding: 2px;">
				Costo Prodotto/i:
				<%=x.getPrezzo()*ordini.get(i).getQuantita()%>&#8364
			</h4>
			
		</div>
		</div>
		<div style="height: 20px"><hr></div>
		<%
			}
		%>
		<div class="col-md-12" align="right">

			<h1>
				Totale:
				<%=costoTot%>&#8364
			</h1>

			<h2>
			<a href="RimuoviCarrelloProdotti"><input type="button"
					class="btn btn-primary" value="Rimuovi i prodotti"></a>
				<a href="ControlloLog"><input type="button"
					class="btn btn-primary" value="Procedi all'ordine"></a>
				
			</h2>
		</div>
		<%
		} else {
	%>
	<div align="center">
		<h2>
		<span class="glyphicon glyphicon-info-sign"></span><br>
			<i>Il tuo carrello &egrave; vuoto</i>
		</h2>
	</div>
	<%}} else{%>
	<div align="center">
		<h2>
			<span class="glyphicon glyphicon-info-sign"></span><br>
			<i>Il tuo carrello &egrave; vuoto</i>
		</h2>
	</div>
	<%}%>
	</div>
	
	<%@include file="footer.jsp"%>

</body>
</html>