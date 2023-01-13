package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ClienteBean;
import model.ClienteBeanDAO;
import model.VenditoreBean;
import model.VenditoreBeanDAO;

/**
 * Servlet implementation class ModificaDatiPersonali
 */
@WebServlet("/ModificaDatiPersonali")
@MultipartConfig
public class ModificaDatiPersonali extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiPersonali() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String tipoUtente=(String) session.getAttribute("tipoUtente");
		
		if(tipoUtente.equals("venditore")) {
			VenditoreBean v=(VenditoreBean)session.getAttribute("utente");
			
			String email=request.getParameter("email");
			
			if (email==null||email.equals(""))
				email=v.getEmail();
			
			String pass=request.getParameter("passwordN");
			if (pass==null||pass.equals(""))
				pass=v.getPassword();
			
			String tel=request.getParameter("telefono");
			if (tel==null||tel.equals(""))
				tel=v.getTelefono();
			
			if(validate(email,pass,tel)) {
			VenditoreBeanDAO vdao=new VenditoreBeanDAO();
			
			if (!pass.equals(v.getPassword()))
				v.setPassword(pass);
			if (!email.equals(v.getEmail()))
				v.setEmail(email);
			if (!tel.equals(v.getTelefono()))
				v.setTelefono(tel);
			
			try {
				vdao.doUpdate(v);
				session.setAttribute("utente", v);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("profiloVenditore.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("message", "Formato errato");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
			requestDispatcher.forward(request, response);
		}
		} else if (tipoUtente.equals("cliente")) {
			ClienteBean c=(ClienteBean)session.getAttribute("utente");
			
			String email=request.getParameter("email"); 
			
			if (email==null||email.equals(""))
				email=c.getEmail();
			
			String pass=request.getParameter("passwordN");
			
			if (pass==null||pass.equals(""))
				pass=c.getPassword();
			
			String tel=request.getParameter("telefono");
			
			if (tel==null||tel.equals(""))
				tel=c.getTelefono();
			
			String ind=request.getParameter("indirizzo");
			
			if (ind==null||ind.equals(""))
				ind=c.getIndirizzo();
			
			String città=request.getParameter("citta");
			
			if (città==null||città.equals(""))
				città=c.getCittà();
			;
			String cap=request.getParameter("cap");
			
			if (cap==null||cap.equals(""))
				cap=c.getCap();
			
			if (valido(email,pass,tel,ind,città,cap)) {
				
				Part filePart = request.getPart("foto");
				InputStream content = filePart.getInputStream();
				if (content.available()!=0) {				
					String encodstring=""; 	
					try {		
						FileInputStream imageInFile = (FileInputStream) content;
						byte imageData[] = new byte[500000];			
						imageInFile.read(imageData);			
						byte[] asBytes = Base64.getEncoder().encode(imageData);		
						encodstring= new String(asBytes, "utf-8");
						c.setFoto(encodstring);
					} 					catch (FileNotFoundException e) {	
						System.out.println("Image not found" + e);		
					} catch (IOException ioe) {				
						System.out.println("Exception while reading the Image " + ioe);	
					} 
				}
				
				if (!pass.equals(c.getPassword()))
					c.setPassword(pass);
				if (!email.equals(c.getEmail()))
					c.setEmail(email);
				if (!tel.equals(c.getTelefono()))
					c.setTelefono(tel);
				if (!ind.equals(c.getIndirizzo()))
					c.setIndirizzo(ind);
				if (!cap.equals(c.getCap()))
					c.setCap(cap);
				if (!città.equals(c.getCittà()))
					c.setCittà(città);
				
				ClienteBeanDAO cdao=new ClienteBeanDAO();
				
				try {
					cdao.doUpdate(c);
					session.setAttribute("utente", c);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("profiloCliente.jsp");
					requestDispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				
			} else {
				request.setAttribute("message", "Formato errato");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
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
	
	private boolean validate(String email, String pass, String telefono) {
		boolean valido=true;
		String expPassword="^[a-zA-Z0-9]{8,19}$";
		String expEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String expTelefono="^[0-9]{9,10}$";
		if (!Pattern.matches(expEmail, email))
			valido=false;
		if (!Pattern.matches(expPassword, pass))
			valido=false;
		if (!Pattern.matches(expTelefono, telefono))
			valido=false;
		return valido;
	}
	
	private boolean valido(String email, String pass, String telefono,String ind,String city,String cap) {
		boolean valido=true;
		String expPassword="^[a-zA-Z0-9]{8,19}$";
		String expEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String expTelefono="^[0-9]{9,10}$";
		String expCitta="^[a-zA-Z]+([ '-]?[a-zA-Z]+)*$";
		String expIndirizzo="^[A-Za-z ']{2,}[, ]+[0-9]{1,4}$";
		String expCap="^[0-9]{5}$";
		if (!Pattern.matches(expEmail, email))
			valido=false;
		if (!Pattern.matches(expPassword, pass))
			valido=false;
		if (!Pattern.matches(expTelefono, telefono))
			valido=false;
		if (!Pattern.matches(expIndirizzo, ind))
			valido=false;
		if (!Pattern.matches(expCap, cap))
			valido=false;
		if (!Pattern.matches(expCitta, city))
			valido=false;
		return valido;
	}

}
