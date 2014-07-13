package org.coenraets.kullanici;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/kullanicilar")
public class KullaniciResources {
	
	KullaniciDAO dao = new KullaniciDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Kullanici> kullaniciListele() {
		System.out.println("findAll");
		return dao.kullaniciListele();
	}
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Kullanici kullaniciListele(@PathParam("id") String id) {
		System.out.println("Kullanýcý Listele : "+id);
		return dao.kullaniciBul(Integer.parseInt(id));
	}
	
	/*
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Kullanici kullaniciBul(Kullanici user){
		System.out.println("Kullanýcý Bul : "+ user.getEmail());
		return dao.kullaniciBul(user);
	}*/
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Kullanici kullaniciKayit(Kullanici user){
		System.out.println("Kullanýcý Kayit");
		return dao.kullaniciKayit(user);
	}
	
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Kullanici kullaniciGuncelle(Kullanici user){
		System.out.println("Kullanýcý Güncelle : " + user.getId());
		return dao.kullaniciGuncelle(user);
	}
	
	@DELETE @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean kullaniciSil(@PathParam("id") String id){
		System.out.println("Kullanýcý Sil : " + id);
		return dao.kullaniciSil(Integer.parseInt(id));
	}
	
}
