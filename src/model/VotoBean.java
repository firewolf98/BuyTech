package model;

import java.util.Date;

public class VotoBean {
	private ClienteBean cliente;
	private ProdottoBean prodotto;
	private VenditoreBean venditore;
	private int voto;
	private String commento;
	private Date dataVoto;
	
	public VotoBean(ClienteBean cliente,ProdottoBean prodotto,VenditoreBean venditore,int voto,String commento,Date dataVoto) {
		this.cliente=cliente;
		this.prodotto=prodotto;
		this.venditore=venditore;
		this.voto=voto;
		this.commento=commento;
		this.dataVoto=dataVoto;
	}
	
	public VenditoreBean getVenditore() {
		return venditore;
	}

	public void setVenditore(VenditoreBean venditore) {
		this.venditore = venditore;
	}

	public VotoBean() {
		
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Date getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(Date dataVoto) {
		this.dataVoto = dataVoto;
	}
	
}
