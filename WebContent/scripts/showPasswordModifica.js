function showPassword() {
	  var x = document.getElementById("passwordN");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
}