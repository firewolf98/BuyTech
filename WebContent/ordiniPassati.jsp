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

<%	
		ArrayList<OrdineBean> ordinip = (ArrayList<OrdineBean>) session.getAttribute("ordinip");
			
		%>
	
	<div class=container>
	
		<h1>I tuoi ultimi acquisti:</h1>
	
	
		<div class="col-md-7">
					<%if (ordinip.size()!=0){ 
					for (int  i = 0; i < ordinip.size(); i++) {
						OrdineBean y = (OrdineBean) ordinip.get(i);
				%>
	
				<div class="row">

				<div class="col-md-6">
					<br> Nome: <b><i><%=y.getProdotto().getNome()%></i></b> 
					<br> Quantità: <b><i><%=y.getQuantita()%></i></b>
					<br> Prezzo: <b><i><%=y.getCostoTotale()%></i></b><br>
					<%
							String foto1 = "data:image/jpg;base64," + y.getProdotto().getFoto();
						%>
					<img src=<%=foto1%> class="img-responsive img-circle"
						height="200" width="200">

				</div>
				
				</div>
				<div style="height: 20px"><hr></div>
			

			<%
					}
				%>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-4">
				<br>
				<div class="panel">
					<div class="panel-heading">
						<h4 style="text-align:center">Aggiungi recensione per un prodotto</h4>
					</div>
					<div class="panel-body">
						<form action="AggiungiVoto" method="post">

							<%if (ordinip.size()>0){ %>


							<div class="col-md-6">
								<h5>Prodotto:</h5>
								<div class="form-group">
									<select name="prod" class="form-control">
										<%
							String name = "";
							for(int k=0;k<ordinip.size();k++) {
								OrdineBean obv=(OrdineBean)ordinip.get(k);
								String ins = obv.getProdotto().getNome();
								String ins2=obv.getProdotto().getBtsin();
								name = obv.getVenditore().getUsername();
						%>

										<option value=<%=ins2%>,<%=name %>><%=ins%></option>

										<%
							}
						%>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<h5>Voto:</h5>
								<div class="form-group">
									<input type="number" class="form-control" name="votod"
										placeholder="voto 1-5" min="1" max="5" required>
								</div>
							</div>
							<div class="col-md-6">
								<h5>Commento:</h5>
								<div class="form-group">
									<textarea class="form-control rounded-0"
										id="exampleFormControlTextarea1" rows="10" name="commento"
										style="width: 300px; resize: none; height: 70px;" required>
							</textarea>
								</div>
							</div>
							<div class="col-md-12" align="center">
								<button type="submit" class="btn btn-default">Vota</button>
							</div>



						</form>
					</div>

				</div>
			</div>
	

		<%}}else{ %>
		<h3 class="text-center">Non ci sono ordini da mostrare</h3>
		<%} %>

	</div>

</body>
</html>