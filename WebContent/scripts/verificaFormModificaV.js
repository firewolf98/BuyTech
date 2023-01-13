$(document).ready(function() {
	$(".error").hide();
	var formValido=true;
	
	$("#form1").submit(function(){
			return (validatePassword()&&validateEmail()&&validateTelefono());
	});
	
	
	function validatePassword(){
		var passformat = /^[a-zA-Z0-9]{8,19}$/;
		var valido=true;
		if ($("#passwordN").val()){
			if ($("#passwordN").val().match(passformat)){
				if($("#passwordN").hasClass("er"))
					$("#passwordN").removeClass('er').next().hide();
			}
			else{
				$("#passwordN").focus();
				$("#passwordN").addClass("er").next().show();
				valido=false;
			}
		}
		return valido;
	}

	function validateEmail(){
		var emailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/;
		var field=document.getElementById("email");
		var valido=true;
		if ($("#email").val()){
			if (field.value.match(emailformat)){
				if($(field).hasClass("er"))
					$(field).removeClass('er').next().hide();
			}

			else{
				document.getElementById("email").focus();
				$(field).addClass("er").next().show();
				valido=false;
			}
			return valido;
	}}

	function validateTelefono(){
		var telformat = /^[0-9]{9,10}$/;
		var field=document.getElementById("telefono");
		var valido=true;
		if ($("#telefono").val()){
			if (field.value.match(telformat)){
				if($(field).hasClass("er"))
					$(field).removeClass('er').next().hide();
			}

			else{
				document.getElementById("telefono").focus();
				$(field).addClass("er").next().show();
				valido=false;
			}
			return valido;
	}}
	
});