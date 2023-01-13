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

/**
 * Servlet implementation class RicercaProdotti
 */
@WebServlet("/RicercaProdotti")
public class RicercaProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		HttpSession s=request.getSession();

    	synchronized(s) {
    		if(nome.equals("")) {
    			request.setAttribute("message", "Devi inserire il nome del prodotto per poter effettuare la ricerca");
    			RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
        		requestDispatcher.forward(request, response);
    		} else {
    			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
    			s.setAttribute("nomeP", nome);
    			try {
        			ArrayList<ProdottoBean> prodotti;
        			prodotti=pdao.doRetrieveByName(nome);
        			request.setAttribute("prod", prodotti);
        			s.setAttribute("prod", prodotti);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
    			
    			request.setAttribute("start", "0");
        		request.setAttribute("end", "5");
        		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ricercaProdotti.jsp");
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
