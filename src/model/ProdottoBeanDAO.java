package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdottoBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (ProdottoBean prodotto) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Prodotto (BTSIN, venditore, nome, descrizione, disponibilita, foto, prezzo, categoria, garanzia, reso) values (?,?,?,?,?,?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, prodotto.getBtsin());
		 ps.setString(2, prodotto.getVenditore().getUsername());
		 ps.setString(3, prodotto.getNome());
		 ps.setString(4, prodotto.getDescrizione());
		 ps.setInt(5, prodotto.getDisponibilita());
		 ps.setString(6, prodotto.getFoto());
		 ps.setFloat(7, prodotto.getPrezzo());
		 ps.setString(8, prodotto.getCategoria());
		 ps.setInt(9, prodotto.getGaranzia());
		 ps.setBoolean(10, prodotto.isReso());
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
	 
	 public synchronized boolean doUpdate (ProdottoBean prodotto) throws SQLException {
			conn = DriverManagerConnectionPool.getConnection();
			String sqlUp="Update Prodotto set nome=?,descrizione=?,disponibilita=?,foto=?,prezzo=?,categoria=?,vendutototvolte=?,garanzia=?,reso=? where BTSIN=? and venditore=?";
			PreparedStatement psUp = conn.prepareStatement(sqlUp);
			psUp.setString(1, prodotto.getNome());
			psUp.setString(2, prodotto.getDescrizione());
			psUp.setInt(3, prodotto.getDisponibilita());
			psUp.setString(4, prodotto.getFoto());
			psUp.setFloat(5, prodotto.getPrezzo());
			psUp.setString(6, prodotto.getCategoria());
			psUp.setInt(7, prodotto.getVendutoTotVolte());
			psUp.setInt(8, prodotto.getGaranzia());
			psUp.setBoolean(9, prodotto.isReso());
			psUp.setString(10, prodotto.getBtsin());
			psUp.setString(11, prodotto.getVenditore().getUsername());
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
	 
	 public synchronized void doDelete (String btsin,String venditore) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Prodotto where BTSIN=? and venditore=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, btsin);
		 ps.setString(2, venditore);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized ProdottoBean doRetrieveByKey(String btsin,String userVenditore)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Prodotto where BTSIN=? and venditore=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, btsin);
		 ps.setString(2, userVenditore); 
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 ProdottoBean prodotto=new ProdottoBean();;
			 prodotto.setBtsin(rs.getString("BTSIN"));
			 venditore.setUsername(rs.getString("venditore"));
			 prodotto.setVenditore(venditore);
			 prodotto.setNome(rs.getString("nome"));
			 prodotto.setDescrizione(rs.getString("descrizione"));
			 prodotto.setDisponibilita(rs.getInt("disponibilita"));
			 prodotto.setFoto(rs.getString("foto"));
			 prodotto.setPrezzo(rs.getFloat("prezzo"));
			 prodotto.setCategoria(rs.getNString("categoria"));
			 prodotto.setVendutoTotVolte(rs.getInt("vendutototvolte"));
			 prodotto.setGaranzia(rs.getInt("garanzia"));
			 prodotto.setReso(rs.getBoolean("reso"));
			 return prodotto;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<ProdottoBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<ProdottoBean> prodotti=new ArrayList<>();
		 String sql="select * from Prodotto";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 ProdottoBean prodotto=new ProdottoBean();;
			 prodotto.setBtsin(rs.getString("BTSIN"));
			 venditore.setUsername(rs.getString("venditore"));
			 prodotto.setVenditore(venditore);
			 prodotto.setNome(rs.getString("nome"));
			 prodotto.setDescrizione(rs.getString("descrizione"));
			 prodotto.setDisponibilita(rs.getInt("disponibilita"));
			 prodotto.setFoto(rs.getString("foto"));
			 prodotto.setPrezzo(rs.getFloat("prezzo"));
			 prodotto.setCategoria(rs.getNString("categoria"));
			 prodotto.setVendutoTotVolte(rs.getInt("vendutototvolte"));
			 prodotto.setGaranzia(rs.getInt("garanzia"));
			 prodotto.setReso(rs.getBoolean("reso"));
			 prodotti.add(prodotto);
		 }
		 conn.close();
		 return prodotti;
	 }
	 
	 public synchronized ArrayList<ProdottoBean> doRetrieveByCondition(VenditoreBean v) throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<ProdottoBean> prodotti=new ArrayList<>();
		 String sql="select * from Prodotto where venditore=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, v.getUsername());
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 ProdottoBean p=new ProdottoBean();
			 VenditoreBean v1=new VenditoreBean();
			 p.setBtsin(rs.getString("BTSIN"));
			 VenditoreBeanDAO vdao=new VenditoreBeanDAO();
			 v1=vdao.doRetrieveByKey(rs.getString("venditore"));
			 p.setVenditore(v1);
			 p.setNome(rs.getString("nome"));
			 p.setDescrizione(rs.getString("descrizione"));
			 p.setDisponibilita(rs.getInt("disponibilita"));
			 p.setFoto(rs.getString("foto"));
			 p.setPrezzo(rs.getFloat("prezzo"));
			 p.setCategoria(rs.getString("categoria"));
			 p.setVendutoTotVolte(rs.getInt("vendutototvolte"));
			 p.setGaranzia(rs.getInt("garanzia"));
			 p.setReso(rs.getBoolean("reso"));
			 prodotti.add(p);
		 }
		 conn.close();
		 return prodotti;
	 }
	 
	 public synchronized ArrayList<ProdottoBean> doRetrieveByCondition() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<ProdottoBean> prodotti=new ArrayList<>();
		 String sql="select * from Prodotto order by vendutototvolte desc";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 ProdottoBean prodotto=new ProdottoBean();;
			 prodotto.setBtsin(rs.getString("BTSIN"));
			 venditore.setUsername(rs.getString("venditore"));
			 prodotto.setVenditore(venditore);
			 prodotto.setNome(rs.getString("nome"));
			 prodotto.setDescrizione(rs.getString("descrizione"));
			 prodotto.setDisponibilita(rs.getInt("disponibilita"));
			 prodotto.setFoto(rs.getString("foto"));
			 prodotto.setPrezzo(rs.getFloat("prezzo"));
			 prodotto.setCategoria(rs.getNString("categoria"));
			 prodotto.setVendutoTotVolte(rs.getInt("vendutototvolte"));
			 prodotto.setGaranzia(rs.getInt("garanzia"));
			 prodotto.setReso(rs.getBoolean("reso"));
			 prodotti.add(prodotto);
		 }
		 conn.close();
		 return prodotti;
	 }
	 
	 public synchronized ArrayList<ProdottoBean> doRetrieveByNome(String nome) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 PreparedStatement ps = conn.prepareStatement(
				 "SELECT nome FROM Prodotto WHERE nome like ?");
		 ps.setString(1, nome);
		 ArrayList<ProdottoBean> prodotti = new ArrayList<>();
		 ResultSet rs = ps.executeQuery();
		 while (rs.next()) {
			 ProdottoBean p = new ProdottoBean();
			 p.setNome(rs.getString(1));
			 prodotti.add(p);
		 }
		 conn.close();
		 return prodotti;
	 }
	 
	 public synchronized ArrayList<ProdottoBean> doRetrieveByName(String nome) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 PreparedStatement ps = conn.prepareStatement(
				 "SELECT * FROM Prodotto WHERE nome=?");
		 ps.setString(1, nome);
		 ArrayList<ProdottoBean> prodotti = new ArrayList<>();
		 ResultSet rs = ps.executeQuery();
		 while (rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 ProdottoBean prodotto=new ProdottoBean();;
			 prodotto.setBtsin(rs.getString("BTSIN"));
			 venditore.setUsername(rs.getString("venditore"));
			 prodotto.setVenditore(venditore);
			 prodotto.setNome(rs.getString("nome"));
			 prodotto.setDescrizione(rs.getString("descrizione"));
			 prodotto.setDisponibilita(rs.getInt("disponibilita"));
			 prodotto.setFoto(rs.getString("foto"));
			 prodotto.setPrezzo(rs.getFloat("prezzo"));
			 prodotto.setCategoria(rs.getNString("categoria"));
			 prodotto.setVendutoTotVolte(rs.getInt("vendutototvolte"));
			 prodotto.setGaranzia(rs.getInt("garanzia"));
			 prodotto.setReso(rs.getBoolean("reso"));
			 prodotti.add(prodotto);
		 }
		 conn.close();
		 return prodotti;
	 }
	 
	 public synchronized ArrayList<ProdottoBean> doRetrievebyCondition(ArrayList<ProdottoBean> p,int minPrezzo, int maxPrezzo){
			ArrayList<ProdottoBean> prodotti=new ArrayList<>();
			for (ProdottoBean x:p) {
				if (minPrezzo!=-1&&maxPrezzo!=-1) { //ricerca per prezzo minimo e massimo
					if (x.getPrezzo()>=minPrezzo&&x.getPrezzo()<=maxPrezzo)
						prodotti.add(x);
				}
				else if (minPrezzo==-1) { //prezzo massimo non inserito, ricerca per prezzo minimo
					if (x.getPrezzo()<=maxPrezzo)
						prodotti.add(x);
				}
				else if (maxPrezzo==-1) { //prezzo minimo non inserito, ricerca per prezzo massimo
					if (x.getPrezzo()>=minPrezzo)
						prodotti.add(x);
				}
			}
			return prodotti;	 
		}
	 
	 public synchronized ArrayList<ProdottoBean> doRetrievebyCondition(ArrayList<ProdottoBean> p,boolean reso){
			ArrayList<ProdottoBean> prodotti=new ArrayList<>();
			for (ProdottoBean x:p) {
				if (x.isReso()==reso)
					prodotti.add(x);
			}
			return prodotti;	 
		}
	 
	 public synchronized ArrayList<ProdottoBean> doRetrieveByCategoria(String cat) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 PreparedStatement ps = conn.prepareStatement(
				 "SELECT * FROM Prodotto WHERE categoria=?");
		 ps.setString(1, cat);
		 ArrayList<ProdottoBean> prodotti = new ArrayList<>();
		 ResultSet rs = ps.executeQuery();
		 while (rs.next()) {
			 VenditoreBean venditore=new VenditoreBean();
			 ProdottoBean prodotto=new ProdottoBean();;
			 prodotto.setBtsin(rs.getString("BTSIN"));
			 venditore.setUsername(rs.getString("venditore"));
			 prodotto.setVenditore(venditore);
			 prodotto.setNome(rs.getString("nome"));
			 prodotto.setDescrizione(rs.getString("descrizione"));
			 prodotto.setDisponibilita(rs.getInt("disponibilita"));
			 prodotto.setFoto(rs.getString("foto"));
			 prodotto.setPrezzo(rs.getFloat("prezzo"));
			 prodotto.setCategoria(rs.getNString("categoria"));
			 prodotto.setVendutoTotVolte(rs.getInt("vendutototvolte"));
			 prodotto.setGaranzia(rs.getInt("garanzia"));
			 prodotto.setReso(rs.getBoolean("reso"));
			 prodotti.add(prodotto);
		 }
		 conn.close();
		 return prodotti;
	 }

}
