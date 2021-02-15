package com.example.ad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.lang.Nullable;

@Entity
public class Dummy {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Nullable
	private String mamasita;
	
	@Lob
	@Nullable
	private byte[] image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Dummy() {
		super();
	}

	public String getMamasita() {
		return mamasita;
	}

	public void setMamasita(String mamasita) {
		this.mamasita = mamasita;
	}
	
	

}
