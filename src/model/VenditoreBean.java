package model;

public class VenditoreBean {
	private String username,password,email,nome,telefono;
	private int segnalazioni;
	private boolean amministratore;
	
	public VenditoreBean(String username,String password,String email,String nome,String telefono,int segnalazioni) {
		this.username=username;
		this.password=password;
		this.email=email;
		this.nome=nome;
		this.telefono=telefono;
		this.segnalazioni=segnalazioni;
		this.amministratore=false;
	}
	
	public VenditoreBean() {
		
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getSegnalazioni() {
		return segnalazioni;
	}

	public void setSegnalazioni(int segnalazioni) {
		this.segnalazioni = segnalazioni;
	}

	public boolean isAmministratore() {
		return amministratore;
	}

	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
	}
	
}
