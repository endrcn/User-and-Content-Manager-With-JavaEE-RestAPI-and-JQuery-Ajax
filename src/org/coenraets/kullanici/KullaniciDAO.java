package org.coenraets.kullanici;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class KullaniciDAO {
	
	public List<Kullanici> kullaniciListele() {
        List<Kullanici> list = new ArrayList<Kullanici>();
        Connection c = null;
    	String sql = "SELECT * FROM tbl_kullanici";
        try {
            c = DbBaglanti.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(kullaniciDoldur(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			DbBaglanti.close(c);
		}
        return list;
    }
	
	public Kullanici kullaniciBul(Integer id){
		String sql = "SELECT * FROM tbl_kullanici WHERE id=?";
		Kullanici kullanici = null;
		Connection c = null;
		
		try {
			c=DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				kullanici = kullaniciDoldur(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbBaglanti.close(c);
		}
		return kullanici;
	}
	
	public Kullanici kullaniciBul(Kullanici user){
		String sql = "SELECT * FROM tbl_kullanici WHERE email=? AND sifre=?";
		Kullanici kullanici = null;
		Connection c = null;
		
		try {
			c=DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,user.getEmail());
			ps.setString(2,user.getSifre());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				kullanici = kullaniciDoldur(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbBaglanti.close(c);
		}
		return kullanici;
	}
	
	public Kullanici kullaniciKayit(Kullanici user){
		String sql = "INSERT INTO tbl_kullanici (adi,soyadi,email,sifre) VALUES (?,?,?,?)";
		Connection c = null;
		try {
			c = DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql,new String[] { "ID" });
			ps.setString(1, user.getAdi());
			ps.setString(2, user.getSoyadi());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getSifre());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			user.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbBaglanti.close(c);
		}
		
		return user;
	}
	
	public Kullanici kullaniciGuncelle(Kullanici user){
		String sql = "UPDATE tbl_kullanici SET adi=?, soyadi=?, email=?, sifre=? WHERE id=?";
		Connection c = null;
		try {
			c = DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, user.getAdi());
			ps.setString(2, user.getSoyadi());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getSifre());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbBaglanti.close(c);
		}
		
		return user;
	}
	
	public boolean kullaniciSil(Integer id){
		String sql = "DELETE FROM tbl_kullanici WHERE id=?";
		Connection c = null;
		int sonuc = 0;
		try {
			c = DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			sonuc = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbBaglanti.close(c);
		}
		if(sonuc>0)
			return true;
		return false;
		
	}
	
	
	private Kullanici kullaniciDoldur(ResultSet rs) {
		Kullanici kullanici = new Kullanici();
		try {
			kullanici.setId(rs.getInt("id"));
			kullanici.setAdi(rs.getString("adi"));
			kullanici.setSoyadi(rs.getString("soyadi"));
			kullanici.setEmail(rs.getString("email"));
			kullanici.setSifre(rs.getString("sifre"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kullanici;
	}
	
	
}
