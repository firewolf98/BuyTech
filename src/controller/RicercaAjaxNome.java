package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import model.ProdottoBean;
import model.ProdottoBeanDAO;

/**
 * Servlet implementation class RicercaAjaxNome
 */
@WebServlet("/RicercaAjaxNome")
public class RicercaAjaxNome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaAjaxNome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray matJson = new JSONArray();
		String query = request.getParameter("q");
		
		if (query != null) {
			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
			List<ProdottoBean> prodotti;
			try {
				prodotti = pdao.doRetrieveByNome(query + "%");
				for (ProdottoBean p : prodotti) {
					matJson.put(p.getNome());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.getWriter().append(matJson.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
