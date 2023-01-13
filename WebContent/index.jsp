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
<body>
<%@include file="header.jsp" %>

	<div class="jumbotron" >
		<div id="contentj">
			<div align="center">
				<h2>Trova i migliori prodotti al miglior prezzo</h2>
				<div class="col-xs-11 col-md-12">
				<form class="form-inline" action="RicercaProdotti" style="align:center">
					<div class="input-group">
						<input type="text"
							class="form-control" id="nome" placeholder="Cerca per nome"
							name="nome" required list="ricerca-datalist" onkeyup="ricercaNome(this.value)">
							<datalist id="ricerca-datalist"></datalist>
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-search"></span> Cerca
					</button>
				</form>
				</div>
				<br><br>
				<div class="col-xs-11 col-md-12">
				<form class="form-inline" action="RicercaProdottiCategoria" style="align:center">
					<div class="input-group">
						<select name="cat" id="cat" class="form-control">
											<option value="computer">Computer</option>
											<option value="telefono">Telefono</option>
											<option value="tablet">Tablet</option>
											<option value="componenti">Componenti</option>
											<option value="software">Software</option>
										</select>
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-search"></span> Cerca
					</button>
				</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">	
	<h1 style="text-align:center;">I prodotti più venduti:</h1>
	<div class="col-md-5 riga"></div>
		<%ArrayList<ProdottoBean> slide=(ArrayList<ProdottoBean>) session.getAttribute("venduti");
		if (slide==null) response.sendRedirect("ProdottiVenduti"); 
		else{%>
		<div class="row" >
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<div class="col-md-3"></div>
						<div class="col-md-3 text-center">
						<%ProdottoBean slide1=slide.get(0); %>
							<%
							String foto1 = "data:image/jpg;base64," + slide1.getFoto();
							%>
						<img src=<%=foto1%> class="img-circle">
						</div>
						<div class="col-md-6">
							<h3><%=slide1.getNome()%> :  <%=slide1.getPrezzo() %> euro</h3>
							<blockquote><%=slide1.getDescrizione()%><br>Disponibilita': <%=slide1.getDisponibilita() %><br>
							<%String disp=""; if(slide1.isReso()) disp="Disponibile"; else disp="Non disponibile"; %>Possibilita' di reso: <%=disp %></blockquote>
						</div>
					</div>

					<div class="item">
						<div class="col-md-3"></div>
						<div class="col-md-3 text-center">
						<%ProdottoBean slide2=slide.get(1); %>
							<%
							String foto2 = "data:image/jpg;base64," + slide2.getFoto();
							%>
						<img src=<%=foto2%> class="img-circle">
						</div>
						<div class="col-md-6">
							<h3><%=slide2.getNome()%> :  <%=slide2.getPrezzo() %> euro</h3>
							<blockquote><%=slide2.getDescrizione()%><br>Disponibilita': <%=slide2.getDisponibilita() %><br>
							<%String disp1=""; if(slide2.isReso()) disp1="Disponibile"; else disp1="Non disponibile"; %>Possibilita' di reso: <%=disp1 %></blockquote>
						</div>
					</div>

					<div class="item" >
						<div class="col-md-3"></div>
						<div class="col-md-3 text-center">
						<%ProdottoBean slide3=slide.get(2); %>
							<%
							String foto3 = "data:image/jpg;base64," + slide3.getFoto();
							%>
						<img src=<%=foto3%> class="img-circle">
						</div>
						<div class="col-md-6">
							<h3><%=slide3.getNome()%> :  <%=slide3.getPrezzo() %> euro</h3>
							<blockquote><%=slide3.getDescrizione()%><br>Disponibilita': <%=slide3.getDisponibilita() %><br>
							<%String disp2=""; if(slide3.isReso()) disp2="Disponibile"; else disp2="Non disponibile"; %>Possibilita' di reso: <%=disp2 %></blockquote>
						</div>
					</div> 
					
					<!-- Left and right controls -->
				<a class="left carousel-control" style="background:none;" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" style="background:none;" href="#myCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
					
					<%} %>
				</div></div></div></div>
				
	<div style="height:20px"></div>
	<%@include file="footer.jsp"%>


<script src="scripts/jquery.js"></script>
<script src="scripts/ricercaNome.js"></script>
</body>
</html>