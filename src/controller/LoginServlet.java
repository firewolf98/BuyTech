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

import model.ClienteBean;
import model.ClienteBeanDAO;
import model.OrdineBean;
import model.OrdineBeanDAO;
import model.ProdottoBean;
import model.ProdottoBeanDAO;
import model.VenditoreBean;
import model.VenditoreBeanDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ClienteBeanDAO cliente=new ClienteBeanDAO();
		VenditoreBeanDAO venditore=new VenditoreBeanDAO();
		ClienteBean c;
		VenditoreBean v;
		boolean cnull=false;
		boolean vnull=false;
		
		HttpSession session=request.getSession();
		String redirect="";
		
		try {
			c=cliente.doRetrieveByKey(username);
			if(c!=null) {
				if(c.getPassword().equals(password)) {
					if(c.isAmministratore()) {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", c);
						redirect="profiloAdmin.jsp";
						session.setAttribute("tipoUtente", "admin");
						
						VenditoreBeanDAO vdao=new VenditoreBeanDAO();
						ArrayList<VenditoreBean> venditori=vdao.doRetrieveAll();
						session.setAttribute("venditoriAdmin", venditori);
					}
					else {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", c);
						redirect="index.jsp";
						session.setAttribute("tipoUtente", "cliente");
						
						OrdineBeanDAO o=new OrdineBeanDAO();
						ArrayList<OrdineBean> ordinip=o.doRetrieveByConditions(c.getUsername());
						session.setAttribute("ordinip", ordinip);
					}
				}
				else {
					redirect="login.jsp";
				}
			}
			else {
				cnull=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			v=venditore.doRetrieveByKey(username);
			if(v!=null) {
				if(v.getPassword().equals(password)) {
					if(v.isAmministratore()) {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", v);
						redirect="profiloAdmin.jsp";
						session.setAttribute("tipoUtente", "admin");
						
						VenditoreBeanDAO vdao=new VenditoreBeanDAO();
						ArrayList<VenditoreBean> venditori=vdao.doRetrieveAll();
						for(int k=0;k<venditori.size();k++) {
							if(username.equals(venditori.get(k).getUsername()))
									venditori.remove(k);
						}
						session.setAttribute("venditoriAdmin", venditori);
					}
					else {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", v);
						redirect="profiloVenditore.jsp";
						session.setAttribute("tipoUtente", "venditore");
						
						ProdottoBeanDAO p=new ProdottoBeanDAO();
						ArrayList<ProdottoBean> prodotti=p.doRetrieveByCondition(v);
						session.setAttribute("prodotti", prodotti);
					}
				}
				else {
					redirect="login.jsp";
				}
			}
			else {
				vnull=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (cnull&& vnull) {
			redirect="registrati.jsp";
		}
		
		if (redirect.equals("login.jsp")) {
			request.setAttribute("message", "Username o password errati");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
			requestDispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(redirect);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
