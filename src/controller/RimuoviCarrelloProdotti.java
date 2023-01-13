package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.OrdineBean;
import model.ProdottoBean;

/**
 * Servlet implementation class RimuoviCarrelloProdotti
 */
@WebServlet("/RimuoviCarrelloProdotti")
public class RimuoviCarrelloProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviCarrelloProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		synchronized (session) {
			Carrello cart=(Carrello) session.getAttribute("carrello");
			ArrayList<OrdineBean> ordini=(ArrayList<OrdineBean>)session.getAttribute("ordini");
			ArrayList<ProdottoBean> prodotti=cart.getProdotti();
			prodotti.removeAll(prodotti);
			ordini.removeAll(ordini);
			cart.setProdotti(prodotti);
			session.setAttribute("carrello", cart);
			response.sendRedirect("Cart.jsp");
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
