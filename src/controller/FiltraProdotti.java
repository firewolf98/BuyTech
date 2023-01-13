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

import model.ProdottoBeanDAO;
import model.ProdottoBean;

/**
 * Servlet implementation class FiltraProdotti
 */
@WebServlet("/FiltraProdotti")
public class FiltraProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltraProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		
		ProdottoBeanDAO pdao=new ProdottoBeanDAO();
		
		String min=request.getParameter("minPrezzo");
		int minPrezzo;
		if (min==null||min.equals("")) minPrezzo=-1;
		else minPrezzo=Integer.parseInt(min);
		
		String max=request.getParameter("maxPrezzo");
		int maxPrezzo;
		if (max==null||max.equals("")) maxPrezzo=-1;
		else maxPrezzo=Integer.parseInt(max);
		
		String reso=request.getParameter("reso");
		
		try {
			ArrayList<ProdottoBean> p;
					
			p=pdao.doRetrieveByName((String) s.getAttribute("nomeP"));
			
			if (minPrezzo!=-1||maxPrezzo!=-1)
				p=pdao.doRetrievebyCondition(p, minPrezzo, maxPrezzo);
			
			boolean r=false;
			if (reso!=null) {
				if(reso.equals("S")) r=true;
				p=pdao.doRetrievebyCondition(p, r);
			}
				
			request.setAttribute("prod", p);
			s.setAttribute("prod", p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ricercaProdotti.jsp");
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
