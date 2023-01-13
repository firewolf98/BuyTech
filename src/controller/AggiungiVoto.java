package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClienteBean;
import model.ProdottoBean;
import model.ProdottoBeanDAO;
import model.VenditoreBean;
import model.VenditoreBeanDAO;
import model.VotoBean;
import model.VotoBeanDAO;

/**
 * Servlet implementation class AggiungiVoto
 */
@WebServlet("/AggiungiVoto")
public class AggiungiVoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiVoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prod=request.getParameter("prod");
		ProdottoBean p=new ProdottoBean();
		ProdottoBeanDAO pdao=new ProdottoBeanDAO();
		VenditoreBean v=new VenditoreBean();
		VenditoreBeanDAO vdao=new VenditoreBeanDAO();
		String[] aus=prod.split(",");
		String codice=aus[0];
		String vend=aus[1];
		try {
			p=pdao.doRetrieveByKey(codice,vend);
			v=vdao.doRetrieveByKey(vend);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int vot=Integer.parseInt(request.getParameter("votod"));
		String des=request.getParameter("commento");
		HttpSession session=request.getSession();
		
		ClienteBean x=(ClienteBean)session.getAttribute("utente");
		Date data=new Date(System.currentTimeMillis());
		
		VotoBean insVoto=new VotoBean(x,p,v,vot,des,data);
		VotoBeanDAO vDAO=new VotoBeanDAO();
		
		try {
			vDAO.doSave(insVoto);
			response.sendRedirect("ordiniPassati.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
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
