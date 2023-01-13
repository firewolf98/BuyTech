package model;

import java.util.ArrayList;

public class Carrello {
	private ArrayList<ProdottoBean> prodotti;
	
	public Carrello() {
		prodotti=new ArrayList<>();
	}

	public ArrayList<ProdottoBean> getProdotti() {
		return prodotti;
	}

	public void setProdotti(ArrayList<ProdottoBean> prodotti) {
		this.prodotti = prodotti;
	}
	
	public void aggiungiProdotto(ProdottoBean prodotto) {
		prodotti.add(prodotto);
	}
	
	public void cancellaProdotto(ProdottoBean prodotto) {
		prodotti.remove(prodotto);
	}
	
}
