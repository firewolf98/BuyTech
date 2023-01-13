package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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

import model.ProdottoBean;
import model.ProdottoBeanDAO;
import model.VenditoreBean;

/**
 * Servlet implementation class AggiungiProdotto
 */
@WebServlet("/AggiungiProdotto")
@MultipartConfig
public class AggiungiProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encodstring=""; 	
		Part filePart = request.getPart("foto");
		InputStream content = filePart.getInputStream();

		if (content.available()!=0) {				
			try (FileInputStream imageInFile = (FileInputStream) content) {		
				byte imageData[] = new byte[500000];			
				imageInFile.read(imageData);			
				byte[] asBytes = Base64.getEncoder().encode(imageData);		
				encodstring= new String(asBytes, "utf-8");
			} 					catch (FileNotFoundException e) {	
				System.out.println("Image not found" + e);		
			} catch (IOException ioe) {				
				System.out.println("Exception while reading the Image " + ioe);	
			} 
		}
		
		String codice=request.getParameter("codice");
		String nome=request.getParameter("nome");
		String desc=request.getParameter("desc");
		String cat=request.getParameter("cat");
		String disp=request.getParameter("disp");
		String prezzo=request.getParameter("prezzo");
		String garanzia=request.getParameter("garanzia");
		String reso=request.getParameter("reso");
		boolean r=false;
		if(reso.equals("S")) r=true;
		
		HttpSession session = request.getSession();
		VenditoreBean v=(VenditoreBean) session.getAttribute("utente");
		
		if(validate(codice,nome,cat,desc,disp,prezzo,garanzia,reso)) {
			ProdottoBean p=new ProdottoBean(codice,nome,desc,encodstring,cat,v,Float.parseFloat(prezzo),Integer.parseInt(disp),Integer.parseInt(garanzia),r);
			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
			ArrayList<ProdottoBean> prodotti=(ArrayList<ProdottoBean>) session.getAttribute("prodotti");
			
			boolean inserito;
			try {
				inserito = pdao.doSave(p);
				prodotti.add(p);
				session.setAttribute("prodotti", prodotti);
				request.setAttribute("ris", inserito);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else request.setAttribute("ris", false);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("outputInserimentoProdotto.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean validate(String codice,String nome,String cat,String desc,String disp,String prezzo,String garanzia,String reso) {
		boolean valido=true;
		String expCodice="^[a-zA-Z0-9]{1,20}$";
		String expNome="^[a-zA-Z0-9 ]{1,20}$";
		String expDesc="^[a-zA-Z0-9,!. ]*";
		
		if (!Pattern.matches(expCodice, codice))
			valido=false;
		if (!Pattern.matches(expNome, nome))
			valido=false;
		if (!Pattern.matches(expDesc, desc))
			valido=false;
		if (disp.equals("")||prezzo.equals("")||garanzia.equals("")||reso.contentEquals(""))
			valido=false;
		
		return valido;
	}

}
