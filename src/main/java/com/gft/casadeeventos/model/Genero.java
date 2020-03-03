package com.gft.casadeeventos.model;

public enum Genero {
	
	Rap("Rap"),
	Rock("Rock"),
	Sertanejo("Sertanejo"),
	Pop("Pop"),
	Samba("Samba"),
	Convenção("Convenção"),
	StandUP("StandUP");
	
	private String musical;
	
	Genero(String musical){
		this.musical = musical;
	}

	public String getMusical() {
		return musical;
	}

	public void setMusical(String musical) {
		this.musical = musical;
	}
	
	
	

}
