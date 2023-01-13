package model;

public class ClienteBean {
	private String username,password,email,nome,cognome,indirizzo,città,cap,telefono,foto;
	private boolean amministratore;
	
	public ClienteBean(String username,String password,String email,String nome,String cognome,String indirizzo,String città,String cap,String telefono,String foto) {
		this.username=username;
		this.password=password;
		this.email=email;
		this.nome=nome;
		this.cognome=cognome;
		this.indirizzo=indirizzo;
		this.città=città;
		this.cap=cap;
		this.telefono=telefono;
		this.foto=foto;
		this.amministratore=false;
	}
	
	public ClienteBean() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isAmministratore() {
		return amministratore;
	}

	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
	}
	
}
