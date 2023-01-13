package model;

public class ProdottoBean {
	private String btsin,nome,descrizione,foto,categoria;
	private VenditoreBean venditore;
	private float prezzo;
	private int disponibilita,vendutoTotVolte,garanzia;
	private boolean reso;
	
	public ProdottoBean(String btsin,String nome,String descrizione,String foto,String categoria,VenditoreBean venditore,float prezzo,int disponibilita,int garanzia,boolean reso) {
		this.btsin=btsin;
		this.nome=nome;
		this.descrizione=descrizione;
		this.foto=foto;
		this.categoria=categoria;
		this.venditore=venditore;
		this.prezzo=prezzo;
		this.disponibilita=disponibilita;
		this.vendutoTotVolte=0;
		this.garanzia=garanzia;
		this.reso=reso;
	}
	
	public ProdottoBean() {
		
	}

	public String getBtsin() {
		return btsin;
	}

	public void setBtsin(String btsin) {
		this.btsin = btsin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public VenditoreBean getVenditore() {
		return venditore;
	}

	public void setVenditore(VenditoreBean venditore) {
		this.venditore = venditore;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public int getVendutoTotVolte() {
		return vendutoTotVolte;
	}

	public void setVendutoTotVolte(int vendutoTotVolte) {
		this.vendutoTotVolte = vendutoTotVolte;
	}

	public int getGaranzia() {
		return garanzia;
	}

	public void setGaranzia(int garanzia) {
		this.garanzia = garanzia;
	}

	public boolean isReso() {
		return reso;
	}

	public void setReso(boolean reso) {
		this.reso = reso;
	}
	
}
