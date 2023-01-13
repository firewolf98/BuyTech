package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.VenditoreBean;
import model.VenditoreBeanDAO;

/**
 * Servlet implementation class ServletRegVenditore
 */
@WebServlet("/ServletRegVenditore")
public class ServletRegVenditore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegVenditore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String telefono=request.getParameter("telefono");
		
		if (valido(nome,email,username,password,telefono)) {
			VenditoreBean v=new VenditoreBean(username,password,email,nome,telefono,0);
			VenditoreBeanDAO vdao=new VenditoreBeanDAO();
			
			boolean inserito;
			try {
				inserito = vdao.doSave(v);
				request.setAttribute("ris", inserito);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else request.setAttribute("ris", false);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("outputRegistrazione.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valido(String nome, String email, String username, String password, String telefono ) {
		boolean valido=true;
		String expNome="^[a-zA-Z]+([ ]?[a-zA-Z]+)*$";
		String expEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String expUsername="^[a-zA-Z0-9]{1}[a-z_A-Z0-9]{2,19}$";
		String expPassword="^[a-zA-Z0-9]{8,19}$";
		String expTelefono="^[0-9]{9,10}$";
		
		if (!Pattern.matches(expNome, nome)) 
			valido=false;
		if (!Pattern.matches(expUsername, username))
			valido=false;
		if (!Pattern.matches(expEmail, email))
			valido=false;
		if (!Pattern.matches(expPassword, password)) 
			valido=false;
		if (!Pattern.matches(expTelefono, telefono))
			valido=false;
		return valido;
	}

}
