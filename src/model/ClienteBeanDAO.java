package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (ClienteBean cliente) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Cliente (username, pw, email, nome, cognome, indirizzo, citta, cap,telefono,foto) values (?,?,?,?,?,?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, cliente.getUsername());
		 ps.setString(2, cliente.getPassword());
		 ps.setString(3, cliente.getEmail());
		 ps.setString(4, cliente.getNome());
		 ps.setString(5, cliente.getCognome());
		 ps.setString(6, cliente.getIndirizzo());
		 ps.setString(7, cliente.getCittà());
		 ps.setString(8, cliente.getCap());
		 ps.setString(9, cliente.getTelefono());
		 ps.setString(10, cliente.getFoto());
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
	 
	 public synchronized boolean doUpdate (ClienteBean cliente) throws SQLException {
			conn = DriverManagerConnectionPool.getConnection();
			String sqlUp="Update Cliente set pw=?,email=?,indirizzo=?,citta=?,cap=?,telefono=?,foto=? where username=?";
			PreparedStatement psUp = conn.prepareStatement(sqlUp);
			psUp.setString(1, cliente.getPassword());
			psUp.setString(2, cliente.getEmail());
			psUp.setString(3, cliente.getIndirizzo());
			psUp.setString(4, cliente.getCittà());
			psUp.setString(5, cliente.getCap());
			psUp.setString(6, cliente.getTelefono());
			psUp.setString(7, cliente.getFoto());
			psUp.setString(8, cliente.getUsername());
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
		 String sql="Delete from Cliente where username=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, username);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized ClienteBean doRetrieveByKey(String username)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Cliente where username=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, username); 
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 ClienteBean cliente=new ClienteBean();
			 cliente.setUsername(rs.getString("username"));
			 cliente.setPassword(rs.getString("pw"));
			 cliente.setEmail(rs.getString("email"));
			 cliente.setNome(rs.getString("nome"));
			 cliente.setCognome(rs.getString("cognome"));
			 cliente.setIndirizzo(rs.getString("indirizzo"));
			 cliente.setCittà(rs.getString("citta"));
			 cliente.setCap(rs.getNString("cap"));
			 cliente.setTelefono(rs.getNString("telefono"));
			 cliente.setFoto(rs.getString("foto"));
			 cliente.setAmministratore(rs.getBoolean("amministratore"));
			 return cliente;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<ClienteBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<ClienteBean> clienti=new ArrayList<>();
		 String sql="select * from Cliente";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 ClienteBean cliente=new ClienteBean();
			 cliente.setUsername(rs.getString("username"));
			 cliente.setPassword(rs.getString("pw"));
			 cliente.setEmail(rs.getString("email"));
			 cliente.setNome(rs.getString("nome"));
			 cliente.setCognome(rs.getString("cognome"));
			 cliente.setIndirizzo(rs.getString("indirizzo"));
			 cliente.setCittà(rs.getString("citta"));
			 cliente.setCap(rs.getNString("cap"));
			 cliente.setTelefono(rs.getNString("telefono"));
			 cliente.setFoto(rs.getString("foto"));
			 cliente.setAmministratore(rs.getBoolean("amministratore"));
			 clienti.add(cliente);
		 }
		 conn.close();
		 return clienti;
	 }

}
