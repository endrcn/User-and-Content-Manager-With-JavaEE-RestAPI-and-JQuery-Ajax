package org.coenraets.icerik;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/icerik")
public class IcerikResources {
	
	IcerikDAO dao = new IcerikDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Icerik> icerikListele() {
		System.out.println("içerik findAll");
		return dao.icerikListele();
	}
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Icerik icerikListele(@PathParam("id") String id) {
		System.out.println("Ýçerik Listele : "+id);
		return dao.icerikBul(Integer.parseInt(id));
	}
	
	/*
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Kullanici icerikBul(Kullanici icerik){
		System.out.println("Ýçerik Bul : "+ icerik.getEmail());
		return dao.icerikBul(icerik);
	}*/
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Icerik icerikKayit(Icerik icerik){
		
		System.out.println("Ýçerik Kayit");
		return dao.icerikKayit(icerik);
	}
	
	/*
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.MULTIPART_FORM_DATA})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Icerik icerikKayit(Icerik icerik,
			@FormDataParam("resim") InputStream uploadedInputStream,
			@FormDataParam("resim") FormDataContentDisposition fileDetail){
		
		String newFileLocation = "ProjeGorev/pics/" + fileDetail.getFileName();
		icerik.setResim(fileDetail.getFileName());
		resimUpload(uploadedInputStream, newFileLocation);
			
		System.out.println("Ýçerik Kayit");
		return dao.icerikKayit(icerik);
	}
	
	private void resimUpload(InputStream uploadedInputStream, String newFileLocation){
		try {
			OutputStream out = new FileOutputStream(new File(newFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			//out = new FileOutputStream(new File(newFileLocation));
			while((read = uploadedInputStream.read(bytes)) != -1){
				out.write(bytes,0,read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Icerik icerikGuncelle(Icerik icerik){
		System.out.println("Ýçerik Güncelle : " + icerik.getId());
		return dao.icerikGuncelle(icerik);
	}
	
	@DELETE @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean icerikSil(@PathParam("id") String id){
		System.out.println("Ýçerik Sil : " + id);
		return dao.icerikSil(Integer.parseInt(id));
	}
	
}
