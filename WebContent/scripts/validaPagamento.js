$(document).ready(function() {
	$("#form1").submit(function(){
		var ris = true;

		if (!validateRadio()){
			ris=false;
			alert("Devi selezionare un luogo per la tua lezione!");
		}
		
		if(!$('#txtDate').datepicker('getDate')){
			ris=false;
			alert("Devi selezionare un giorno per la tua lezione!");
		}
		
		
		if(!$("#oraInizio").val() || !$("#oraFine").val() ){
			ris=false;
			alert("Devi selezionare gli orari!");
	
		}
			
		return ris;
	});
	
	function validateRadio(){
		var valid=true;
		 if ($('input[name="luogo"]:checked').length == 0) {
			 valid=false;
		 }
		return valid;
	}

});