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
		if (namef=="username")
			return validateUsername();
		if (namef=="email")
			return validateEmail();
		if (namef=="password")
			return validatePassword();
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