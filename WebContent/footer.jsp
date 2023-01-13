<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.*"
	%>
	
<div style="height: 30px"></div>
<footer class="page-footer">

	<%
	ArrayList<String> categorie=new ArrayList<String>();
	categorie.add("Tablet");
	categorie.add("Telefono");
	categorie.add("Computer");
	categorie.add("Componenti");
	categorie.add("Software");
	%>
	<div class="footer-container">
		<div style="height: 10px"></div>
		<div class="col-xs-12  col-sm-9 col-md-9 center-column hidden-sm hidden-xs">
			<div class="col-md-12">
				<div class="title"><strong>Categorie di prodotti</strong></div>
				<hr>
				<%
					if (categorie != null) {
						for (int i = 0; i < categorie.size(); i++) {
							String cat = categorie.get(i);
				%>
				<ul class="list-unstyled col-xs-4 col-md-2">
					<li><%=cat%></li>
				</ul>

				<%
					}
				}
				%>
			</div>
		
		</div>
	
		<div class="col-xs-12 col-sm-12 col-md-3">
			<div class="col-xs-12">
				<div class="title"><strong>Hai bisogno di aiuto?</strong></div>
				<hr>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-11">
				<p class="phone">
					<i class="fa fa-phone fa-lg"></i>3889532837
				</p>
				<p>Lun-Ven (9-13 / 14-18)</p>
			</div>
		</div>
	</div>
</footer>