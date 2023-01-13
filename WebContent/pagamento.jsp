<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,model.*"%>
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

<style>
input.er{
	border-style: solid;
	border-color:red;
}
</style>
</head>

<body>

<%@ include file="header.jsp" %>
<div style="height:20px"></div>
<div class="container" id="text">
    <div class="row">
    <div style="height:20px"></div>
            <div class="col-md-12">
            <div class="col-md-6 col-md-offset-3">
                <div class="well margin-30-top;">
                <h2><strong>Inserisci i tuoi dati di pagamento:</strong></h2>
                
                <div class="payment">
               
                 <form action="EffettuaAcquisto" method="post" id="form1">
                 
                 	
                    <div class="form-group">
                   
                        <label for="owner">Nome del proprietario:</label>
                        <input type="text" class="form-control" name="nome" pattern="[A-Z]{1}[a-zA-Z ]{1,40}">
                    </div>
                    
                    <div class="form-group">
                        <label for="cvv">CVV:</label>
                        <input type="text" class="form-control" maxlength="3" style="width: 60px;" id="cvv" name="cvv" pattern="[0-9]{3}">
   
                    </div>
                    
                    <div class="form-group">
                        <label for="cardNumber">Numero della carta:</label>
                        <input type="text" class="form-control" maxlength="16" minlength="16" id="cardNumber" name="cardNumber" pattern="[0-9]{16}"> 
 
                    </div>
                    <div class="form-group">
                        <label>Data di Scadenza: </label>
                        <select>
                            <option>Luglio</option>
                            <option>Agosto</option>
                            <option>Settembre</option>
                            <option>Ottobre</option>
                            <option>Novembre</option>
                            <option>Dicembre</option>
                            <option>Gennaio</option>
                            <option>Febbraio </option>
                            <option>Marzo</option>
                            <option>Aprile</option>
                            <option>Maggio</option>
                            <option>Giugno</option>
                        </select>
                        <select>
                            <option> 2020</option>
                            <option> 2021</option>
                            <option> 2022</option>
                            <option> 2023</option>
                            <option> 2024</option>
                            
                        </select>
                    </div>
                    <div class="form-group">
                        <img src="img/cartac.png" style="width:200px">
                        
                    </div>
                    <div class="form-group" >
                        <button type="submit" class="btn btn-default" >Conferma</button>
                    </div>
                </form>
            </div>
                
                 </div>
            
             
                </div>
            </div>
        </div>
    </div>
              
<%@include file="footer.jsp"%>                  
</body>
</html>