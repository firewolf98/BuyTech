package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.VenditoreBean;
import model.VenditoreBeanDAO;

/**
 * Servlet implementation class EliminaVenditore
 */
@WebServlet("/EliminaVenditore")
@SuppressWarnings("unchecked") 
public class EliminaVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaVenditore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		HttpSession session=request.getSession();
		
		synchronized (session) {
			VenditoreBeanDAO daoI=new VenditoreBeanDAO();
			try {
				daoI.doDelete(username);
				ArrayList<VenditoreBean> venditori=(ArrayList<VenditoreBean>) session.getAttribute("venditoriAdmin");
				for (int i=0;i<venditori.size();i++) {
					VenditoreBean x=venditori.get(i);
					if (x.getUsername().equals(username))
						venditori.remove(i);
				}
				session.setAttribute("venditoriAdmin", venditori);
				response.sendRedirect("profiloAdmin.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
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
