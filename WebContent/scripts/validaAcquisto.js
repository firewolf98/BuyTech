$(document).ready(function() {
	$("#form1").submit(function(){
		var ris = true;

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
	
	function validateNumber(){
		var val=document.getElementById("quant").value;
		if (val>=1)
			return true;
		else return false;
	}
	
});