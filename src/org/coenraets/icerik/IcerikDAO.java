package org.coenraets.icerik;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.coenraets.kullanici.DbBaglanti;


public class IcerikDAO {
	
	public List<Icerik> icerikListele() {
        List<Icerik> list = new ArrayList<Icerik>();
        Connection c = null;
    	String sql = "SELECT * FROM tbl_icerik";
        try {
            c = DbBaglanti.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(icerikDoldur(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			DbBaglanti.close(c);
		}
        return list;
    }
	
	public Icerik icerikBul(Integer id){
		String sql = "SELECT * FROM tbl_icerik WHERE id=?";
		Icerik icerik = null;
		Connection c = null;
		
		try {
			c=DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				icerik = icerikDoldur(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbBaglanti.close(c);
		}
		return icerik;
	}
	
	public Icerik icerikKayit(Icerik icerik){
		String sql = "INSERT INTO tbl_icerik (baslik,detay,resim) VALUES (?,?,?)";
		Connection c = null;
		try {
			c = DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql,new String[] { "ID" });
			ps.setString(1, icerik.getBaslik());
			ps.setString(2, icerik.getDetay());
			ps.setString(3, icerik.getResim());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			icerik.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbBaglanti.close(c);
		}
		
		return icerik;
	}
	
	public Icerik icerikGuncelle(Icerik icerik){
		String sql = "UPDATE tbl_icerik SET baslik=?, detay=?, resim=? WHERE id=?";
		Connection c = null;
		try {
			c = DbBaglanti.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, icerik.getBaslik());
			ps.setString(2, icerik.getDetay());
			ps.setString(3, icerik.getResim());
			ps.setInt(4, icerik.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DbBaglanti.close(c);
		}
		
		return icerik;
	}
	
	public boolean icerikSil(Integer id){
		String sql = "DELETE FROM tbl_icerik WHERE id=?";
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
	
	
	private Icerik icerikDoldur(ResultSet rs) {
		Icerik icerik = new Icerik();
		try {
			icerik.setId(rs.getInt("id"));
			icerik.setBaslik(rs.getString("baslik"));
			icerik.setDetay(rs.getString("detay"));
			icerik.setResim(rs.getString("resim"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return icerik;
	}
	
	
}
