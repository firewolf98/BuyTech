$(document).ready(function() {
	$(".error").hide();
	$("#form1").submit(function(){
		var ris = true;
		$("#form1").find('input').each(function() {
			if (!validate($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
			}

		});
		return ris;
	});

	function validate(namef){
		if (namef=="nome")
			return validateName();
		if (namef=="cognome")
			return validateSurname();
		if (namef=="citta")
			return validateCity();
		if (namef=="username")
			return validateUsername();
		if (namef=="email")
			return validateEmail();
		if (namef=="password")
			return validatePassword();
		if (namef=="indirizzo")
			return validateIndirizzo();
		if (namef=="cap")
			return validateCap();
		if (namef=="telefono")
			return validateTelefono();
	}

	function validateName(){
		var nameformat = /^[a-zA-Z]+([ ]?[a-zA-Z]+)*$/;
		if (document.getElementById("nome").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
			
		else{
			document.getElementById("nome").focus();
			console.log("non corretto");
			return false;
		}
	}

	function validateSurname(){
		var surnameformat = /^[a-zA-Z]+([ ']?[a-zA-Z]+)*$/;
		if (document.getElementById("cognome").value.match(surnameformat))
			return true;
		else{
			document.getElementById("cognome").focus();
			return false;
		}
	}

	function validateCity(){
		var cityformat = /^[a-zA-Z]+([ '-]?[a-zA-Z]+)*$/;
		if (document.getElementById("citta").value.match(cityformat))
			return true;
		else{
			document.getElementById("citta").focus();
			return false;
		}
	}

	function validateUsername(){
		var usernameformat = /^[a-zA-Z0-9]{1}[a-z_A-Z0-9]{2,19}$/;
		if (document.getElementById("username").value.match(usernameformat))
			return true;
		else{
			document.getElementById("username").focus();
			return false;
		}
	}

	function validateEmail(){
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/;
		if (document.getElementById("email").value.match(mailformat))
			return true;
		else{
			document.getElementById("email").focus();
			return false;
		}
	}

	function validatePassword(){
		var passformat = /^[a-zA-Z0-9]{8,19}$/;
		if (document.getElementById("password").value.match(passformat)){
			return true;
		}
		else{
			document.getElementById("password").focus();
			return false;
		}
	}
	
	function validateIndirizzo(){
		var indformat = /^[A-Za-z ']{2,}[, ]+[0-9]{1,4}$/;
		if (document.getElementById("indirizzo").value.match(indformat)){
			return true;
		}
		else{
			document.getElementById("indirizzo").focus();
			return false;
		}
	}
	
	function validateCap(){
		var capformat = /^[0-9]{5}$/;
		if (document.getElementById("cap").value.match(capformat)){
			return true;
		}
		else{
			document.getElementById("cap").focus();
			return false;
		}
	}
	
	function validateTelefono(){
		var telformat = /^[0-9]{9,10}$/;
		if (document.getElementById("telefono").value.match(telformat)){
			return true;
		}
		else{
			document.getElementById("telefono").focus();
			return false;
		}
	}
	


});