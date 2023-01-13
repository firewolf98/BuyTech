package model;

import java.util.Date;

public class OrdineBean {
	private int idOrdine,quantita;
	private Date dataOrdine;
	private float costoTotale;
	private ClienteBean cliente;
	private ProdottoBean prodotto;
	private VenditoreBean venditore;
	private String stato;
	
	public OrdineBean(int idOrdine,ProdottoBean prodotto,VenditoreBean venditore,Date dataOrdine,float costoTotale,ClienteBean cliente,String stato,int quantita) {
		this.idOrdine=idOrdine;
		this.prodotto=prodotto;
		this.venditore=venditore;
		this.dataOrdine=dataOrdine;
		this.costoTotale=costoTotale;
		this.cliente=cliente;
		this.stato=stato;
		this.quantita=quantita;
	}
	
	public OrdineBean() {
		
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public float getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(float costoTotale) {
		this.costoTotale = costoTotale;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public VenditoreBean getVenditore() {
		return venditore;
	}

	public void setVenditore(VenditoreBean venditore) {
		this.venditore = venditore;
	}
	
	
	
}
