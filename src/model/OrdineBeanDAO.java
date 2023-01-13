package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdineBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (OrdineBean ordine) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Ordine (idordine,prodotto,venditore, dataordine, costototale, stato ,cliente,quantita) values (?,?,?,?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1,ordine.getIdOrdine());
		 ps.setString(2, ordine.getProdotto().getBtsin());
		 ps.setString(3, ordine.getProdotto().getVenditore().getUsername());
		 ps.setDate(4, (Date)ordine.getDataOrdine());
		 ps.setFloat(5, ordine.getCostoTotale());
		 ps.setNString(6, ordine.getStato());
		 ps.setString(7, ordine.getCliente().getUsername());
		 ps.setInt(8, ordine.getQuantita());
		 int ris;
		 boolean inserito=false;
		 try {
			 ris=ps.executeUpdate();
			 if (ris==1)
				inserito=true;
		 }
		 catch(SQLException ex) {
			 ex.printStackTrace();
		 }
		 conn.close();
		 return inserito;
	 }
	 	 
	 public synchronized void doDelete (int idOrdine,String prodotto,String venditore,String cliente,Date data) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Ordine where idordine=? and prodotto=? and venditore=? and cliente=? and dataordine=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, idOrdine);
		 ps.setString(2,prodotto);
		 ps.setString(3,venditore);
		 ps.setString(4,cliente);
		 ps.setDate(5, data);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized OrdineBean doRetrieveByKey(int idOrdine,String prodotto,String venditore,String cliente,Date data)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Ordine where idordine=? and prodotto=? and venditore=? and cliente=? and dataordine=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, idOrdine);
		 ps.setString(2,prodotto);
		 ps.setString(3,venditore);
		 ps.setString(4,cliente);
		 ps.setDate(5, data);
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			ProdottoBean p=new ProdottoBean();
			ProdottoBeanDAO pdao=new ProdottoBeanDAO();
			VenditoreBean v=new VenditoreBean();
			VenditoreBeanDAO vdao=new VenditoreBeanDAO();
			ClienteBean c=new ClienteBean();
			ClienteBeanDAO cdao=new ClienteBeanDAO();
			OrdineBean ordine=new OrdineBean();
			ordine.setIdOrdine(rs.getInt("idordine"));
			String codice=rs.getString("prodotto");
			String user=rs.getString("venditore");
			p=pdao.doRetrieveByKey(codice, user);
			v=vdao.doRetrieveByKey(user);
			ordine.setProdotto(p);
			ordine.setVenditore(v);
			ordine.setDataOrdine(rs.getDate("dataordine"));
			ordine.setCostoTotale(rs.getFloat("costototale"));
			ordine.setStato(rs.getString("stato"));
			c=cdao.doRetrieveByKey(rs.getString("cliente"));
			ordine.setCliente(c);
			ordine.setQuantita(rs.getInt("quantita"));
			 return ordine;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<OrdineBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<OrdineBean> ordini=new ArrayList<>();
		 String sql="select * from Ordine";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 ProdottoBean p=new ProdottoBean();
				ProdottoBeanDAO pdao=new ProdottoBeanDAO();
				VenditoreBean v=new VenditoreBean();
				VenditoreBeanDAO vdao=new VenditoreBeanDAO();
				ClienteBean c=new ClienteBean();
				ClienteBeanDAO cdao=new ClienteBeanDAO();
				OrdineBean ordine=new OrdineBean();
				ordine.setIdOrdine(rs.getInt("idordine"));
				String codice=rs.getString("prodotto");
				String user=rs.getString("venditore");
				p=pdao.doRetrieveByKey(codice, user);
				v=vdao.doRetrieveByKey(user);
				ordine.setProdotto(p);
				ordine.setVenditore(v);
				ordine.setDataOrdine(rs.getDate("dataordine"));
				ordine.setCostoTotale(rs.getFloat("costototale"));
				ordine.setStato(rs.getString("stato"));
				c=cdao.doRetrieveByKey(rs.getString("cliente"));
				ordine.setCliente(c);
				ordine.setQuantita(rs.getInt("quantita"));
			 ordini.add(ordine);
		 }
		 conn.close();
		 return ordini;
	 }
	 
	 public synchronized int doRetrieveByCondition() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 int max=0;
		 String sql="select max(idOrdine) as massimo from Ordine";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 max=rs.getInt("massimo");
		 }
		 conn.close();
		 return max;
	 }
	 
	 public synchronized ArrayList<OrdineBean> doRetrieveByConditions(String cliente) throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<OrdineBean> ordini=new ArrayList<>();
		 String sql="Select * from Ordine where cliente=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, cliente);
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()) {
			 ProdottoBean p=new ProdottoBean();
				ProdottoBeanDAO pdao=new ProdottoBeanDAO();
				VenditoreBean v=new VenditoreBean();
				VenditoreBeanDAO vdao=new VenditoreBeanDAO();
				ClienteBean c=new ClienteBean();
				ClienteBeanDAO cdao=new ClienteBeanDAO();
				OrdineBean ordine=new OrdineBean();
				ordine.setIdOrdine(rs.getInt("idordine"));
				String codice=rs.getString("prodotto");
				String user=rs.getString("venditore");
				p=pdao.doRetrieveByKey(codice, user);
				v=vdao.doRetrieveByKey(user);
				ordine.setProdotto(p);
				ordine.setVenditore(v);
				ordine.setDataOrdine(rs.getDate("dataordine"));
				ordine.setCostoTotale(rs.getFloat("costototale"));
				ordine.setStato(rs.getString("stato"));
				c=cdao.doRetrieveByKey(rs.getString("cliente"));
				ordine.setCliente(c);
				ordine.setQuantita(rs.getInt("quantita"));
			 ordini.add(ordine);
		 }
		 conn.close();
		 return ordini;
	 }
}
