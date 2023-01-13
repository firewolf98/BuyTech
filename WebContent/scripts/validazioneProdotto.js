$(document).ready(function() {
	$(".error").hide();
	$("#form1").submit(function(){
		var ris = true;

		$("#form1").find('input[type="text"]').each(function() {
			if (!validateText($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
			}

		});
				
		$("#form1").find('input[type="radio"]').each(function() {
			if (!validateReso($(this).attr("name"))){
				//$(this).addClass("er").siblings("span:first").show();
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					//$(this).removeClass('er').siblings("span:first").hide();
					$(this).removeClass('er').next().hide();
			}

		});
		
		$("#form1").find('input[type="number"]').each(function() {
			if (!validateNumber($(this).attr("name"))){
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

	function validateText(namef){
		if (namef=="nome")
			return validateName();
		if (namef=="codice")
			return validateCodice();
		if (namef=="cat")
			return validateCat();
		if (namef=="desc")
			return validateDesc();
	}

	function validateName(){
		var nameformat = /^[a-zA-Z0-9 ]{1,20}$/;
		if (document.getElementById("nome").value.match(nameformat))
			return true;
		else{
			document.getElementById("nome").focus();
			return false;
		}
	}

	function validateCodice(){
		var codiceformat = /^[a-zA-Z0-9]{1,20}$/;
		if (document.getElementById("codice").value.match(codiceformat))
			return true;
		else{
			document.getElementById("codice").focus();
			return false;
		}
	}

	function validateCat(){
		var catformat = /^[A-Z]{1}[A-Za-z ]*$/;
		if (document.getElementById("cat").value.match(catformat))
			return true;
		else{
			document.getElementById("cat").focus();
			return false;
		}
	}
	
	function validateDesc(){
		var descformat = /^[a-zA-Z0-9,!. ]*$/;
		if (document.getElementById("desc").value.match(descformat))
			return true;
		else{
			document.getElementById("desc").focus();
			return false;
		}
	}

	function validateReso(){
		var valid = false;
		$('#form1').find(':input:radio').each(function(){
			if($(this).prop('checked'))
				valid = true;
		});

		return valid;
	}

	function validateNumber(namef){
		if (namef=="disp")
			return validateDisp();
		if (namef=="prezzo")
			return validatePrezzo();
		if (namef=="garanzia")
			return validateGaranzia();
	}

	function validateDisp(){
		var val=document.getElementById("disp").value;
		if (val>=1)
			return true;
		else return false;
	}
	
	function validatePrezzo(){
		var val=document.getElementById("prezzo").value;
		if (val>0)
			return true;
		else return false;
	}
	
	function validateGaranzia(){
		var val=document.getElementById("garanzia").value;
		if (val>=2)
			return true;
		else return false;
	}

});