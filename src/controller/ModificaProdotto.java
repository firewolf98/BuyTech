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

/**
 * Servlet implementation class ModificaProdotto
 */
@WebServlet("/ModificaProdotto")
@MultipartConfig
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice=request.getParameter("pid");
		String nome=request.getParameter("nome");
		String desc=request.getParameter("desc");
		String disp=request.getParameter("disp");
		String prezzo=request.getParameter("prezzo");
		String garanzia=request.getParameter("garanzia");
		String reso=request.getParameter("reso");
		boolean r=false;
		if(reso.equals("S")) r=true;
		
		HttpSession session = request.getSession();
		ProdottoBean p=new ProdottoBean();
		ArrayList<ProdottoBean> prodotti=(ArrayList<ProdottoBean>) session.getAttribute("prodotti");
		for(int i=0;i<prodotti.size();i++)
			if(prodotti.get(i).getBtsin().equals(codice))
				p=prodotti.get(i);
		
		prodotti.remove(p);
		
		if(nome==null||nome.equals(""))
			nome=p.getNome();
		if(desc==null||desc.equals(""))
			desc=p.getDescrizione();
		if(disp==null||disp.equals(""))
			disp=p.getDisponibilita()+"";
		if(prezzo==null||prezzo.equals(""))
			prezzo=p.getPrezzo()+"";
		if(garanzia==null||garanzia.equals(""))
			garanzia=p.getGaranzia()+"";
		
		
		if(validate(nome,desc,disp,prezzo,garanzia,reso)) {
			p.setNome(nome);
			p.setDescrizione(desc);
			p.setDisponibilita(Integer.parseInt(disp));
			p.setPrezzo(Float.parseFloat(prezzo));
			p.setGaranzia(Integer.parseInt(garanzia));
			p.setReso(r);
			
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
					p.setFoto(encodstring);
				} 					catch (FileNotFoundException e) {	
					System.out.println("Image not found" + e);		
				} catch (IOException ioe) {				
					System.out.println("Exception while reading the Image " + ioe);	
				} 
			}
			
			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
			
			try {
				pdao.doUpdate(p);
				prodotti.add(p);
				session.setAttribute("prodotti", prodotti);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("profiloVenditore.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean validate(String nome,String desc,String disp,String prezzo,String garanzia,String reso) {
		boolean valido=true;
		String expNome="^[a-zA-Z0-9 ]{1,20}$";
		String expDesc="^[a-zA-Z0-9,!. ]*";
		
		if (!Pattern.matches(expNome, nome))
			valido=false;
		if (!Pattern.matches(expDesc, desc))
			valido=false;
		if (disp.equals("")||prezzo.equals("")||garanzia.equals("")||reso.contentEquals(""))
			valido=false;
		
		return valido;
	}

}
