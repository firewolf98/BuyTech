package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VenditoreBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (VenditoreBean venditore) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Venditore (username, pw, email, nome, telefono) values (?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, venditore.getUsername());
		 ps.setString(2, venditore.getPassword());
		 ps.setString(3, venditore.getEmail());
		 ps.setString(4, venditore.getNome());
		 ps.setString(5, venditore.getTelefono());
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
	 
	 public synchronized boolean doUpdate (VenditoreBean venditore) throws SQLException {
			conn = DriverManagerConnectionPool.getConnection();
			String sqlUp="Update Venditore set pw=?,email=?,nome=?,telefono=?,segnalazioni=? where username=?";
			PreparedStatement psUp = conn.prepareStatement(sqlUp);
			psUp.setString(1, venditore.getPassword());
			psUp.setString(2, venditore.getEmail());
			psUp.setString(3, venditore.getNome());
			psUp.setString(4, venditore.getTelefono());
			psUp.setInt(5, venditore.getSegnalazioni());
			psUp.setString(6, venditore.getUsername());
			boolean modificato=false;
			int ris;
			try {
				ris=psUp.executeUpdate();
				if (ris==1)
					modificato=true;
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			conn.close();
			return modificato;
		}
	 
	 public synchronized void doDelete (String username) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Venditore where username=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, username);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized VenditoreBean doRetrieveByKey(String username)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Venditore where username=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, username); 
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 venditore.setUsername(rs.getString("username"));
			 venditore.setPassword(rs.getString("pw"));
			 venditore.setEmail(rs.getString("email"));
			 venditore.setNome(rs.getString("nome"));
			 venditore.setTelefono(rs.getString("telefono"));
			 venditore.setSegnalazioni(rs.getInt("segnalazioni"));
			 venditore.setAmministratore(rs.getBoolean("amministratore"));
			 return venditore;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<VenditoreBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<VenditoreBean> venditori=new ArrayList<>();
		 String sql="select * from Venditore";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 venditore.setUsername(rs.getString("username"));
			 venditore.setPassword(rs.getString("pw"));
			 venditore.setEmail(rs.getString("email"));
			 venditore.setNome(rs.getString("nome"));
			 venditore.setTelefono(rs.getString("telefono"));
			 venditore.setSegnalazioni(rs.getInt("segnalazioni"));
			 venditore.setAmministratore(rs.getBoolean("amministratore"));
			 venditori.add(venditore);
		 }
		 conn.close();
		 return venditori;
	 }
}
