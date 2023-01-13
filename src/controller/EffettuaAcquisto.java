package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrdineBean;
import model.OrdineBeanDAO;
import model.ProdottoBean;
import model.ProdottoBeanDAO;
import model.VenditoreBean;
import model.VenditoreBeanDAO;
import model.Carrello;
import model.ClienteBean;

/**
 * Servlet implementation class EffettuaAcquisto
 */
@WebServlet("/EffettuaAcquisto")
@SuppressWarnings("unchecked") 
public class EffettuaAcquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EffettuaAcquisto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ClienteBean c=(ClienteBean)session.getAttribute("utente");
		Carrello carrello=(Carrello) session.getAttribute("carrello");
		ArrayList<ProdottoBean> prodotti=carrello.getProdotti();
		ArrayList<OrdineBean> ordini=(ArrayList<OrdineBean>) session.getAttribute("ordini");
		OrdineBeanDAO odao=new OrdineBeanDAO();
		ProdottoBeanDAO pdao=new ProdottoBeanDAO();
		ProdottoBean prod=new ProdottoBean();
		OrdineBean ord=new OrdineBean();
		VenditoreBean vendit=new VenditoreBean();
		VenditoreBeanDAO vdao=new VenditoreBeanDAO();
		
		if (carrello!=null) {
			float costoTot=0;
			for (int i = 0; i < ordini.size(); i++)
				costoTot+=ordini.get(i).getCostoTotale();
			
			
			try {
				int max=odao.doRetrieveByCondition()+1;
				int q=0;
				String codice="";
				String vend="";
				for(int i=0;i<ordini.size();i++) {
					q=ordini.get(i).getQuantita();
					codice=prodotti.get(i).getBtsin();
					vend=prodotti.get(i).getVenditore().getUsername();
					prod=pdao.doRetrieveByKey(codice, vend);
					prod.setDisponibilita(prod.getDisponibilita()-q);
					prod.setVendutoTotVolte(prod.getVendutoTotVolte()+1);
					pdao.doUpdate(prod);
					vendit=vdao.doRetrieveByKey(vend);
					ord.setIdOrdine(max);
					ord.setProdotto(prod);
					ord.setVenditore(vendit);
					ord.setCliente(c);
					ord.setDataOrdine(ordini.get(i).getDataOrdine());
					ord.setQuantita(q);
					ord.setStato("presoincarico");
					ord.setCostoTotale(ordini.get(i).getCostoTotale());
					odao.doSave(ord);
				}
				ordini.removeAll(ordini);
				prodotti.removeAll(prodotti);
				carrello.setProdotti(prodotti);
				session.setAttribute("carrello",carrello );
				
				OrdineBeanDAO o=new OrdineBeanDAO();
				ArrayList<OrdineBean> ordinip=o.doRetrieveByConditions(c.getUsername());
				session.setAttribute("ordinip", ordinip);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Cart.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
