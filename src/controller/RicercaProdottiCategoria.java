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
 * Servlet implementation class RicercaProdottiCategoria
 */
@WebServlet("/RicercaProdottiCategoria")
public class RicercaProdottiCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaProdottiCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat=request.getParameter("cat");
		HttpSession s=request.getSession();

    	synchronized(s) {
    		if(cat.equals("")) {
    			request.setAttribute("message", "Devi inserire la categoria del prodotto per poter effettuare la ricerca");
    			RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
        		requestDispatcher.forward(request, response);
    		} else {
    			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
    			s.setAttribute("categoria", cat);
    			try {
        			ArrayList<ProdottoBean> prodotti;
        			prodotti=pdao.doRetrieveByCategoria(cat);
        			request.setAttribute("prod", prodotti);
        			s.setAttribute("prod", prodotti);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
    			
    			request.setAttribute("start", "0");
        		request.setAttribute("end", "5");
        		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ricercaProdottiCat.jsp");
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
