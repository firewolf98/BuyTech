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

import model.ProdottoBean;
import model.ProdottoBeanDAO;
import model.VotoBean;
import model.VotoBeanDAO;

/**
 * Servlet implementation class InformazioniProdotto
 */
@WebServlet("/InformazioniProdotto")
public class InformazioniProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformazioniProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice=request.getParameter("codice");
		String venditore=request.getParameter("venditore");
		
		ProdottoBeanDAO pdao=new ProdottoBeanDAO();
		
		try {
			ProdottoBean p=pdao.doRetrieveByKey(codice,venditore);
			request.setAttribute("prodotto", p);
						
			VotoBeanDAO votoDAO=new VotoBeanDAO();
			ArrayList<VotoBean> recensioni=votoDAO.doRetrieveByCondition(p);
			request.setAttribute("recensioni", recensioni);
			HttpSession session=request.getSession();
			session.setAttribute("recensioni", recensioni);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("informazioniProdotto.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
