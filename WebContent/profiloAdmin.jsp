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
<%@include file="header.jsp"%>
	<div style="height:20px"></div>
	
	<div class="container">
		<%
			ArrayList venditori = (ArrayList) session.getAttribute("venditoriAdmin");
			
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
			if (venditori.size()<end)
				end=venditori.size();

			int p;
			if (start == 0)
				p = 0;
			else
				p = Integer.parseInt(request.getParameter("page"));
			
			session.setAttribute("pagina","profiloAdmin.jsp");
			
		%>
		<div class="row">
			<div class="col-md-7">
				<h3 text-align="center">Venditori iscritti al sito:</h3>
				<%
					if (venditori != null) {
						for (int i = start; i < end; i++) {
							VenditoreBean x = (VenditoreBean) venditori.get(i);
							
				%>

				<div class="row">
					<div class="col-md-7">
						<h4><b>Username: <%=x.getUsername()%></b></h4>
						<p>Nome: <%=x.getNome() %></p>
						<p>
							<b>Numero segnalazioni</b>:<%=x.getSegnalazioni()%></p>
						
						<p>Email: <%=x.getEmail()%></p>
						<p>Telefono: <%=x.getTelefono() %></p>
						<a href="EliminaVenditore?username=<%=x.getUsername()%>">
							<button type="button" class="btn btn-primary">Elimina
								Account</button>
						</a>
					</div>
				</div>
				<div style="height: 20px"><hr></div>

				<%
					}
					}
				%>


				<!-- paginazione -->
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
							int l = venditori.size();
							for (int j = 0; l > 0; j++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=j%>"><%=j + 1%></a></li>
						<%
							}
							if (end < venditori.size()) {
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

			</div></div></div>

<div style="height:20px"></div>
	<%@include file="footer.jsp"%>
</body>
</html>