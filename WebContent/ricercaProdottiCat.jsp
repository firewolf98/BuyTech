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
.btn-yellow {
    background-color: #ffe700;
    color: #007dc3;
    border: 2px solid #ffe700;
    font-weight: 700;
    font-size: 16px;
}
.btn-block {
    display: block;
    width: 100%;
}
.btn-group-lg>.btn, .btn-lg {
    padding: 10px 16px;
    font-size: 18px;
    line-height: 1.3333333;
    border-radius: 6px;
}
</style>
<body>
<%@include file="header.jsp"%>
	<div style="height:20px"></div>
	<div class="container">
		<h2>Risultato della ricerca</h2>

		<%
			ArrayList prodotti = (ArrayList) session.getAttribute("prod");
		%>
		<p>	Abbiamo trovato <b><%=prodotti.size()%></b> 
			<%if (prodotti.size()==1){ %>prodotto <%}else{  %> prodotti <%} %></p>
			
			<%//paginazione
			String s = (String) request.getAttribute("start");
			int start, end;
			if (s == null)
				start = 0;
			else
				start = Integer.parseInt(s);

			String e = (String) request.getAttribute("end");
			if (e == null)
				end = start + 5;
			else
				end = Integer.parseInt(e);
			if (prodotti.size() < end)
				end = prodotti.size();

			int p;
			if (start == 0)
				p = 0;
			else
				p = Integer.parseInt(request.getParameter("page"));
			
			session.setAttribute("pagina","ricercaProdottiCat.jsp");
			%>
		<p>
		<div class="row">
			<div class="col-md-8">
				<%
						for (int i=start;i<end;i++){
						ProdottoBean x = (ProdottoBean) prodotti.get(i);
				%>

				<div class="row">
					<div class="col-md-5">
						<%
							String foto = "data:image/jpg;base64," + x.getFoto();
						%>
						<img src=<%=foto%> class="img-responsive img-circle relative loading">
					</div>
					<div class="col-md-7">
						<h4><b><%=x.getNome()%></b></h4>
						<%
							String descrizione = x.getDescrizione();
								if (descrizione == null)
									descrizione = "";
						%>
						<p><%=descrizione%></p>
						
						<%String disp="Attualmente non disponibile"; if (x.getDisponibilita()>0) disp=x.getDisponibilita()+"";%>
						
						<p>
							<b>Disponibilità: <%=disp %></b>
						</p>
						<p>
							<b><%=x.getPrezzo() %>&euro;</b>
						</p>
						
						<a href="InformazioniProdotto?codice=<%=x.getBtsin()%>&venditore=<%=x.getVenditore().getUsername()%>">
							<button type="button" class="btn btn-primary">Acquista</button>
						</a>
						<%
							 if (tipoUtente != null && tipoUtente.equals("admin")) {
						%>
						<a href="EliminaProdotto?codice=<%=x.getBtsin()%>&venditore=<%=x.getVenditore().getUsername()%>">
							<button type="button" class="btn btn-primary">Elimina
								Prodotto</button>
						</a>
						<%
							}
						%>
					</div>
				</div>

				<hr>
				<%
					}
				%>


				<nav aria-label="...">
					<ul class="pagination">
						<%
							if (start == 0) {
						%>
						<li class="page-item disabled"><a class="page-link">Previous</a>
							<%
								} else {
							%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=p - 1%>">Previous</a></li> <%
 						}				
							int l = prodotti.size();
							for (int j = 0; l>0; j++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=j%>"><%=j + 1%></a></li>
						<%
							}
							if (end < prodotti.size() ) {
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=p + 1%>">Next</a></li>
						<%
							} else {
						%>
						<li class="page-item disabled"><a class="page-link">Next</a>
						</li>
						<%
							}
						%>
					</ul>
				</nav>

			</div>

			<%
				if (prodotti.size() != 0) {
			%>
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<div class="panel">
					<div class="panel-heading"><span class="entry-title bb-1px padding-bottom-10 header-block-sidebar-search">
            <i class="fa fa-search-plus txt-primary"></i> AFFINA LA TUA RICERCA
        </span></div>
					<div class="panel-body">
						<form action="FiltraProdottiCat" method="get">
							<div class="form-group">
							<b>Prezzo:</b><br>
							<div class="col-xs-6">
								 <input type="number" class="form-control" id="minPrezzo" min="1" name="minPrezzo" value="${param.minPrezzo}"
								 title="Inserisci un prezzo" placeholder="Da">
							</div>
							<div class="col-xs-2"></div>
							<div class="col-xs-6">	
								<input type="number" class="form-control" id="maxPrezzo" name="maxPrezzo" value="${param.maxPrezzo}"
								 title="Inserisci un prezzo" placeholder="A"><br>
							</div>
							</div>
							<b>Reso:</b><br>
							<div class="form-check">
								<input type="radio" class="form-check-input" id="resoS"
									name="reso" value="S"> <label class="form-check-label"
									for="resoS">Disponibile</label><br> <input type="radio"
									class="form-check-input" id="resoN" name="reso" value="N">
								<label class="form-check-label" for="resoN">Non disponibile</label><br>
							</div><br>
							<button type="submit" class=" btn btn-lg btn-block btn-yellow">
							<i class="fa fa-search"></i>CERCA PRODOTTO
							</button>
						</form>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
<%@include file="footer.jsp"%> 

<script>
		$(document).ready(
				function() {
					$(function() {
						$('input[name="reso"]').click(
								function() {
									var $radio = $(this);

									if ($radio.data('waschecked') == true) {
										$radio.prop('checked', false);
										$radio.data('waschecked', false);
									} else
										$radio.data('waschecked', true);

								});
					});
				});
	</script>
</body>
</html>