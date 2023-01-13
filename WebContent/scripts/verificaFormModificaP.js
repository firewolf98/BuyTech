$(document).ready(function() {
	$(".error").hide();
	var formValido=true;
	
	$("#form1").submit(function(){
			return (validateName()&&validateDesc()&&validateDisp()&&validatePrezzo()&&validateGaranzia()&&validateReso());
	});
	
	
	function validateName(){
		var nameformat = /^[a-zA-Z0-9 ]{1,20}$/;
		if (document.getElementById("nome").value.match(nameformat) || document.getElementById("nome").value.equals(""))
			return true;
		else{
			document.getElementById("nome").focus();
			return false;
		}
	}
	
	function validateDesc(){
		var descformat = /^[a-zA-Z0-9,!. ]*$/;
		if (document.getElementById("desc").value.match(descformat) || document.getElementById("desc").equals(""))
			return true;
		else{
			document.getElementById("desc").focus();
			return false;
		}
	}
	
	function validateDisp(){
		var val=document.getElementById("disp").value;
		if (val>=1 || val==null)
			return true;
		else return false;
	}
	
	function validatePrezzo(){
		var val=document.getElementById("prezzo").value;
		if (val>0 || val==null)
			return true;
		else return false;
	}
	
	function validateGaranzia(){
		var val=document.getElementById("garanzia").value;
		if (val>=2 || val==null)
			return true;
		else return false;
	}
	
	function validateReso(){
		var valid = false;
		$('#form1').find(':input:radio').each(function(){
			if($(this).prop('checked'))
				valid = true;
		});

		return valid;
	}
	
});