package controller;
 
import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClienteBean;
import model.ClienteBeanDAO;
import model.VenditoreBean;
import model.VenditoreBeanDAO;

/**
 * Servlet implementation class EliminaAccount
 */
@WebServlet("/EliminaAccount")
public class EliminaAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String utente= (String) session.getAttribute("tipoUtente");
		ClienteBeanDAO cliente=new ClienteBeanDAO();
		VenditoreBeanDAO venditore=new VenditoreBeanDAO();
		ClienteBean c=null;
		VenditoreBean v=null;
		
		if(utente.equals("cliente")) {
			c=(ClienteBean) session.getAttribute("utente");
		} else {
			v=(VenditoreBean) session.getAttribute("utente");
		}
		
		if(utente.equals("cliente")) {
			session.setAttribute("isLogged", false);
			session.invalidate();
			
			try {
				cliente.doDelete(c.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			session.setAttribute("isLogged", false);
			session.invalidate();
			
			try {
				venditore.doDelete(v.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
