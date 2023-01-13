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

import model.ProdottoBean;
import model.ProdottoBeanDAO;

/**
 * Servlet implementation class ProdottiVenduti
 */
@WebServlet("/ProdottiVenduti")
public class ProdottiVenduti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottiVenduti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ArrayList<ProdottoBean> slide=new ArrayList<>();
		synchronized (session) {
			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
			try {
				ArrayList<ProdottoBean> prodotti=pdao.doRetrieveByCondition();
				int x=0;
				for (int i=0;i<3;i++) {
					if(prodotti.size()<3) x=1; else x=i;
					ProdottoBean prod=prodotti.get(x);
					slide.add(prod);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

			session.setAttribute("venduti", slide);
		response.sendRedirect("index.jsp");
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
