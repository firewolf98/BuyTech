package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VotoBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (VotoBean voto) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Voto (cliente, prodotto, venditore, voto, commento, datavoto) values (?,?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, voto.getCliente().getUsername());
		 ps.setString(2, voto.getProdotto().getBtsin());
		 ps.setNString(3, voto.getVenditore().getUsername());
		 ps.setInt(4, voto.getVoto());
		 ps.setString(5, voto.getCommento());
		 ps.setDate(6, (Date)voto.getDataVoto());
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
	 	 
	 public synchronized void doDelete (String cliente,String prodotto,String venditore,Date dataVoto) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Voto where cliente=? and prodotto=? and venditore=? and datavoto=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, cliente);
		 ps.setString(2, prodotto);
		 ps.setString(3, venditore);
		 ps.setDate(4, dataVoto);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized VotoBean doRetrieveByKey(String cliente,String prodotto,String venditore,Date dataVoto)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Voto where cliente=? and prodotto=? and datavoto=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, cliente);
		 ps.setString(2, prodotto);
		 ps.setNString(3, venditore);
		 ps.setDate(4, dataVoto);
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			ClienteBean c=new ClienteBean();
			ProdottoBean p=new ProdottoBean();
			VenditoreBean v=new VenditoreBean();
			VotoBean voto=new VotoBean();
			c.setUsername(rs.getString("cliente"));
			p.setBtsin(rs.getString("prodotto"));
			v.setUsername(rs.getString("venditore"));
			voto.setCliente(c);
			voto.setProdotto(p);
			voto.setVoto(rs.getInt("voto"));
			voto.setCommento(rs.getString("commento"));
			voto.setDataVoto(rs.getDate("datavoto"));
			 return voto;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<VotoBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<VotoBean> voti=new ArrayList<>();
		 String sql="select * from Voto";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 ClienteBean c=new ClienteBean();
				ProdottoBean p=new ProdottoBean();
				VenditoreBean v=new VenditoreBean();
				VotoBean voto=new VotoBean();
				c.setUsername(rs.getString("cliente"));
				p.setBtsin(rs.getString("prodotto"));
				v.setUsername("venditore");
				voto.setCliente(c);
				voto.setProdotto(p);
				voto.setVoto(rs.getInt("voto"));
				voto.setCommento(rs.getString("commento"));
				voto.setDataVoto(rs.getDate("datavoto"));
			 voti.add(voto);
		 }
		 conn.close();
		 return voti;
	 }
	 
	 public synchronized ArrayList<VotoBean> doRetrieveByCondition(ProdottoBean prodotto)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<VotoBean> voti=new ArrayList<>();
		 String sql="Select * from Voto where prodotto=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, prodotto.getBtsin());
		 ResultSet rs=ps.executeQuery();
			
		 while(rs.next()) {
			 VotoBean voto=new VotoBean();
			 ClienteBean c=new ClienteBean();
			 ProdottoBean p=new ProdottoBean();
			 VenditoreBean v=new VenditoreBean();
			 ClienteBeanDAO cdao=new ClienteBeanDAO();
			 c=cdao.doRetrieveByKey(rs.getString("cliente"));
			 ProdottoBeanDAO pdao=new ProdottoBeanDAO();
			 String prod=rs.getString("prodotto");
			 String vend=rs.getString("venditore");
			 p=pdao.doRetrieveByKey(prod,vend);
			 VenditoreBeanDAO vdao=new VenditoreBeanDAO();
			 v=vdao.doRetrieveByKey(vend);			 
			 voto.setCliente(c);
			 voto.setProdotto(p);
			 voto.setVenditore(v);
			 voto.setDataVoto(rs.getDate("dataVoto"));
			 voto.setVoto(rs.getInt("voto"));
			 voto.setCommento(rs.getString("commento"));
			 voti.add(voto);
		 }
		 conn.close();
		 return voti;
	 }
}
