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
	<div style="height:20px"></div>
	
	<div class="container">
	<div align="center"><h3>Modifica prodotto</h3></div>
		<div class="row">
			<form action="ModificaProdotto" method="post" enctype="multipart/form-data" id="form1">
			<div class="col-md-12">
			<h3>Dati prodotto</h3>
			</div>
			<%ArrayList<ProdottoBean> p=(ArrayList<ProdottoBean>)session.getAttribute("prodotti"); %>
			<div class="col-md-12">
			<h3>Prodotto da modificare:</h3>
			<div class="form-group">
										<select name="pid" id="pid" class="form-control">
											<%ProdottoBean x=new ProdottoBean();
											for(int i=0;i<p.size();i++) {
												x=(ProdottoBean) p.get(i);%>
											<option value=<%=x.getBtsin() %>><%=x.getBtsin() %> (<%=x.getNome()%>)</option>
										<%} %>
										</select>
										<span class="error">Categoria obbligatoria</span><br>
									</div>
									<hr>
			</div>
				<div class="col-md-6">
					<p>Foto:</p>
					<div class="form-group">
						<input type="file" class="form-control" name="foto"
							 placeholder="Foto">
					</div>
				</div>
				<div class="col-md-6">
					<p>Nome:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="nome" id="nome">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Descrizione:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="desc" id="desc">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Disponibilità:</p>
					<div class="form-group">
						<input type="number" class="form-control" name="disp" id="disp">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Prezzo:</p>
					<div class="form-group">
						<input type="number" step="0.01" class="form-control" name="prezzo" id="prezzo">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Garanzia (min 2 anni):</p>
					<div class="form-group">
						<input type="number" class="form-control" name="garanzia" id="garanzia">
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
									<div class="form-group">
										Possibilità di reso: <input type="radio" name="reso" value="S">
										Si <input type="radio" name="reso" value="N">
										No <br>
										<span class="error" id="resoError">Scelta obbligatoria</span><br>
									</div>
				</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Modifica
						prodotto</button>
				</div>
			</form>
		</div>
	</div>
	
	<div style="height:20px"></div>
	<%@include file="footer.jsp"%>
	
<script src="scripts/jquery.js"></script>
<script src="scripts/verificaFormModificaP.js"></script>
</body>
</html>