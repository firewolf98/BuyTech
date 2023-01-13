package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.ClienteBean;
import model.OrdineBeanDAO;
import model.OrdineBean;
import model.ProdottoBean;


/**
 * Servlet implementation class InserisciCarrello
 */
@WebServlet("/InserisciCarrello")
public class InserisciCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quant=request.getParameter("quant");
		HttpSession session=request.getSession();
		ArrayList<OrdineBean> ordini=(ArrayList<OrdineBean>) session.getAttribute("ordini");
		if(ordini==null)  ordini=new ArrayList<OrdineBean>();
		Date data=new Date(System.currentTimeMillis());
		int max=0;
		
		synchronized(session) {
		if(!quant.contentEquals("")) {
			ProdottoBean p=(ProdottoBean) session.getAttribute("prodotto");
			ClienteBean c=(ClienteBean) session.getAttribute("utente");
			if (c==null) {
				c=new ClienteBean();
			}
				OrdineBeanDAO odao=new OrdineBeanDAO();
				try {
					max=odao.doRetrieveByCondition();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(Integer.parseInt(quant)<=p.getDisponibilita()) {
				OrdineBean o=new OrdineBean(max+1,p,p.getVenditore(),data,p.getPrezzo()*Integer.parseInt(quant),c,"presoincarico",Integer.parseInt(quant));
				ordini.add(o);
				session.setAttribute("ordini", ordini);
				Carrello carrello=(Carrello)session.getAttribute("carrello");
				if (carrello==null) {
					carrello=new Carrello();
				}
				
				carrello.aggiungiProdotto(p);
				session.setAttribute("carrello", carrello);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Cart.jsp");
				requestDispatcher.forward(request, response);}else {
					request.setAttribute("message", "La quantità inserita supera la disponibilità!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
					requestDispatcher.forward(request, response);
				}
			
		} else {
			request.setAttribute("message", "Devi inserire la quantità per poter aggiungerlo al carrello!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
			requestDispatcher.forward(request, response);
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
