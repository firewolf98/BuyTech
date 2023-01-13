<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=((VenditoreBean)session.getAttribute("utente")).getNome() %></title>
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
			VenditoreBean venditore = (VenditoreBean) session.getAttribute("utente");
		%>
		<div class="col-md-2"></div>
		<div class="col-md-5">
				<h1><%=venditore.getNome()%></h1>
				<h5>Email: <%=venditore.getEmail() %></h5>
				<h5>Telefono: <%=venditore.getTelefono() %></h5>
			</div>
			
		<div class="col-md-2" style="text-align:center">
			<br><br>
				<a class="btn btn-primary" href="modificaProfiloVenditore.jsp">Modifica il tuo profilo</a>
				<br><br><br>
				<a href="EliminaAccount"><input type="button"
					class="btn btn-primary" value="Elimina Account"></a>
			</div>
		
	</div>
	
		<br>
	
	<div class=container>
	
		
		<div class="col-md-6" style="text-align:center">
			<br>
				<a class="btn btn-primary" href="aggiungiProdotto.jsp">Aggiungi un nuovo prodotto</a>
			</div>
		<div class="col-md-6" style="text-align:center">
			<br>
				<a class="btn btn-primary" href="modificaProdotto.jsp">Modifica un prodotto</a>
			</div>
	
		<br><br><br><h1>I tuoi prodotti in vendita:</h1>
		
		<%
			ArrayList prodotti = (ArrayList) session.getAttribute("prodotti");
		%>
		
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
			
			session.setAttribute("pagina","profiloVenditori.jsp");
			%>
		
		
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
							String reso;
							if(x.isReso()) reso="disponibile"; else reso="non disponibile";
						%>
						<img src=<%=foto%> class="img-responsive img-circle relative loading">
					</div>
					<div class="col-md-7">
						<h4><b><%=x.getNome()%></b></h4>
						<p><%=x.getDescrizione()%></p>
						<p>Disponibilita': <%=x.getDisponibilita() %></p>
						<p>Venduto <%=x.getVendutoTotVolte() %> volte</p>
						<p>Categoria: <%=x.getCategoria() %></p>
						<p>Garanzia di <%=x.getGaranzia() %> anni</p>
						<p>Reso <%=reso %>
						<p>Prezzo: <%=x.getPrezzo() %> euro</p>
						
						
						
				</div>
				
				</div>
				<hr>
						<%
							}
						%>
					
				
				
	
	</div></div>


<div style="height:20px"></div>
	<%@include file="footer.jsp"%>
</body>
</html>