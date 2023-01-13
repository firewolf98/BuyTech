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
<link rel="stylesheet" href="css/visProd.css">
<link rel="shortcut icon" type="image/x-icon" href="img/icona.ico">

<style>
.Highlighted a{
   background-color : Green !important;
   background-image :none !important;
   color: White !important;
   font-weight:bold !important;
   font-size: 12pt;
}

select {
	color:black;
}

</style>
</head>
<body>
	<%@include file="header.jsp"%>

	<%ArrayList<VotoBean> voti = (ArrayList<VotoBean>) session.getAttribute("recensioni");
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
	if (voti.size() < end)
		end = voti.size();

	int p;
	if (start == 0)
		p = 0;
	else
		p = Integer.parseInt(request.getParameter("page")); %>
	<div style="height:20px"></div>
	<div class="container">
		<%
			ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
			session.setAttribute("pagina", "InformazioniProdotto?codice="+prodotto.getBtsin()+"&venditore="+prodotto.getVenditore());
		%>
		<%
			String foto = "data:image/jpg;base64," + prodotto.getFoto();
			session.setAttribute("prodotto", prodotto);
			
			int s1=0,s2=0,s3=0,s4=0,s5=0;
			int sum=0,media=0;
			if (voti.size()>0) {
				for (int f = 0; f < voti.size(); f++) 
					sum=sum+voti.get(f).getVoto();
				media=sum/(voti.size());
			}
			
		%>
		
		<div class="row">
			<h2 class="text-center">
				Acquista <%=prodotto.getNome()%> di <%=prodotto.getVenditore().getUsername() %></h2>
			<div class="col-md-7">
				
				<div class="col-md-5">
					<img src=<%=foto%> class="img-responsive img-thumbnail">
					
					
					
				</div>
				<div class="col-md-7">
					<h4><%=prodotto.getNome()%>
												
						<%
							for (int i = 0; i < media; i++) {
						%>
						<i class="fa fa-star"></i>
						<%
							}
						%></h4>
					<%
						String descrizione = prodotto.getDescrizione();
						if (descrizione == null)
							descrizione = "";
					%>
					<p><%=descrizione%></p>
					<p>
						<b><%=prodotto.getPrezzo()%>&euro;</b>
					</p>	
					<%String disp="Attualmente non disponibile"; if(prodotto.getDisponibilita()>0) disp=prodotto.getDisponibilita()+""; %>
					<p>
						Disponibilità: <b><%=disp%></b>
					</p>	
				</div>	
				</div>
						
			
			
			<div style="height:20px"></div>
			
		<div class="col-md-1"></div>

			<div class="col-md-3">
				<%
					if (tipoUtente == null || tipoUtente.equals("cliente")) {
				%>
				<div class="panel">
					<div class="panel-heading">
						<b><i>Acquista prodotto</i></b>
					</div>
					<div class="panel-body">

						<form action="InserisciCarrello" method="post" id="form1">

							
							<br> <i><b>Quantità:</b></i>
					
								<input type="number" class="form-control" name="quant" id="quant">
							

							<br>
							 <input type="submit" class="btn btn-default"
								value="Inserisci nel carrello"></input>
						</form>
					</div>
					</div>
					<%
						}
					%>
			</div>
			</div></div>
		
			<div style="height:20px"></div>
			
			
			<%
				
				VotoBean voto;
			%>
			<div class=container>
			<div class="row" id="row-voto">
				<h2 class="text-center">
					Ecco cosa dicono di
					<%=prodotto.getNome()%></h2>
					<%if (voti.size()>0){ %>
				<div class="col-md-6 col-sm-12">

						<%
							for (int f = 0; f < voti.size(); f++) {
								voto = voti.get(f);
								if(voto.getVoto()==1) s1++;
								else if(voto.getVoto()==2) s2++;
								else if(voto.getVoto()==3) s3++;
								else if(voto.getVoto()==4) s4++;
								else s5++;
						%>
						<div class="row">
						<p><%=voto.getCliente().getNome()%>:
						</p>
						
						<%	
							for (int i = 0; i < voto.getVoto(); i++) {
								
						%>
						<i class="fa fa-star"></i>
						<%
							}
						%>
						<p><%=voto.getCommento()%></p>
						</div>
						<div style="height: 20px"><hr></div>
						<%
							}
						%>

					</div>
					
					<div class="col-md-1 col-sm-4 hidden-xs">
					<p>5 stelle</p><div style="height:11px"></div>
					<p>4 stelle</p><div style="height:11px"></div>
					<p>3 stelle</p><div style="height:11px"></div>
					<p>2 stelle</p><div style="height:11px"></div>
					<p>1 stella</p>
					</div>
					
					
					<div class="col-md-4 col-sm-6 hidden-xs ">
						<div class="progress">
 						 <div class="progress-bar" role="progressbar" style="width:<%=s5*100/voti.size()%>%" ></div>
						</div>
						<div class="progress">
 						 <div class="progress-bar" role="progressbar" style="width:<%=s4*100/voti.size()%>%" ></div>
						</div>
						<div class="progress">
 						<div class="progress-bar" role="progressbar" style="width:<%=s3*100/voti.size()%>%"></div>
						</div>
						<div class="progress">
 						 <div class="progress-bar" role="progressbar" style="width:<%=s2*100/voti.size()%>%"></div>
						</div>
						<div class="progress">
  						<div class="progress-bar" role="progressbar" style="width:<%=s1*100/voti.size()%>%"></div>
						</div>
					</div>
					<div class="col-md-1 col-sm-2 hidden-xs">
					<p><%=s5 %></p><div style="height:11px"></div>
					<p><%=s4 %></p><div style="height:11px"></div>
					<p><%=s3 %></p><div style="height:11px"></div>
					<p><%=s2 %></p><div style="height:11px"></div>
					<p><%=s1 %></p>
					</div>
					<%} else{%>
					<p>Non ci sono ancora recensioni per questo prodotto.</p>
					<%} %>
					
					<!-- paginazione -->
					<div class="col-md-12 col-sm-12 col-xs-12">
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
							href="Paginazione?page=<%=p - 1%>">Previous</a></li>
						<%
							}
							int l = voti.size();
							for (int z = 0; l > 0; z++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=z%>"><%=z + 1%></a></li>
						<%
							}
							if (end < voti.size()) {
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
			</div></div>
			
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="scripts/validaAcquisto.js"></script>
		
<div style="height:20px"></div>		
<%@include file="footer.jsp"%>
</body>
</html>