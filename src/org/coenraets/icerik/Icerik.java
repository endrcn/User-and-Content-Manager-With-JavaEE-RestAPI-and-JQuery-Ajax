package org.coenraets.icerik;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Icerik {
	private int id;
	private String baslik;
	private String detay;
	private String resim;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaslik() {
		return baslik;
	}
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	public String getDetay() {
		return detay;
	}
	public void setDetay(String detay) {
		this.detay = detay;
	}
	public String getResim() {
		return resim;
	}
	public void setResim(String resim) {
		this.resim = resim;
	}
	
	
	
	
}
