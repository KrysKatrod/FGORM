package fgopkg;

import java.io.Serializable;

public class Ressource implements Serializable {
	private String name;
	private int needed;
	private int stock;
	private int lack;
	private String bestlocjp;
	private String bestlocna;
	
	public Ressource() {
		this.name = "NONE";
		this.needed=0;
		this.stock=0;
		this.lack=0;
		this.bestlocjp = "NONE";
		this.bestlocna = "NONE";
	}
	
	
	
	public Ressource(String name, int needed, int stock,int lack, String bestlocjp, String bestlocna) {
		this.name = name;
		this.needed = needed;
		this.stock = stock;
		this.lack = lack;
		this.bestlocjp = bestlocjp;
		this.bestlocna = bestlocna;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNeeded() {
		return needed;
	}
	public void setNeeded(int needed) {
		this.needed = needed;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getLack() {
		return lack;
	}



	public void setLack(int lack) {
		this.lack = lack;
	}



	public String getBestlocjp() {
		return bestlocjp;
	}



	public void setBestlocjp(String bestlocjp) {
		this.bestlocjp = bestlocjp;
	}



	public String getBestlocna() {
		return bestlocna;
	}



	public void setBestlocna(String bestlocna) {
		this.bestlocna = bestlocna;
	}


	
	
}
