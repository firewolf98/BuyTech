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
import model.ClienteBean;
import model.ClienteBeanDAO;

/**
 * Servlet implementation class ServletRegCliente
 */
@WebServlet("/ServletRegCliente")
public class ServletRegCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encodstring=""; 			
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String citta=request.getParameter("citta");
		String indirizzo=request.getParameter("indirizzo");
		String cap=request.getParameter("cap");
		String telefono=request.getParameter("telefono");

		if (valido(nome,cognome,email,username,password,citta,indirizzo,cap,telefono)) {
			ClienteBean cliente= new ClienteBean(username,password,email,nome,cognome,indirizzo,citta,cap,telefono,encodstring);
			ClienteBeanDAO c=new ClienteBeanDAO();
			
			boolean inserito;
			try {
				inserito = c.doSave(cliente);
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
		
	private boolean valido(String nome, String cognome, String email, String username, String password, String citta, String indirizzo, String cap, String telefono ) {
		boolean valido=true;
		String expNome="^[a-zA-Z]+([ ]?[a-zA-Z]+)*$";
		String expCognome="^[a-zA-Z]+([ ']?[a-zA-Z]+)*$";
		String expEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String expUsername="^[a-zA-Z0-9]{1}[a-z_A-Z0-9]{2,19}$";
		String expPassword="^[a-zA-Z0-9]{8,19}$";
		String expCitta="^[a-zA-Z]+([ '-]?[a-zA-Z]+)*$";
		String expIndirizzo="^[A-Za-z ']{2,}[, ]+[0-9]{1,4}$";
		String expCap="^[0-9]{5}$";
		String expTelefono="^[0-9]{9,10}$";
		
		if (!Pattern.matches(expNome, nome)) 
			valido=false;
		if (!Pattern.matches(expCognome, cognome)) 
			valido=false;
		if (!Pattern.matches(expUsername, username))
			valido=false;
		if (!Pattern.matches(expPassword, password)) 
			valido=false;
		if (!Pattern.matches(expCitta, citta))
			valido=false;
		if (!Pattern.matches(expEmail, email))
			valido=false;
		if (!Pattern.matches(expIndirizzo, indirizzo))
			valido=false;
		if (!Pattern.matches(expCap, cap))
			valido=false;
		if (!Pattern.matches(expTelefono, telefono))
			valido=false;
		return valido;
	}

}
