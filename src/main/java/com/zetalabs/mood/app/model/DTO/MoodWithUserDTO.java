package com.zetalabs.mood.app.model.DTO;

import java.time.LocalDateTime;

import com.zetalabs.mood.app.model.Mood;


public class MoodWithUserDTO {
	
	private Long id;
    private Long userId;
    private String name;

    private int humor;
    private int fome;
    private int tedio;
    private int fisica;
    private int emocional;
    private int falar;
    private int ouvir;
    private int companhia;
    private String recado;
    private LocalDateTime timestamp;
    
    
    
    public MoodWithUserDTO(Mood mood, String name) {
    	
        this.name = name;
        
        this.id = mood.getId();
        this.userId = mood.getUserId();
        this.humor = mood.getHumor();
		this.fome = mood.getFome();
		this.tedio = mood.getTedio();
		this.fisica = mood.getFisica();
		this.emocional = mood.getEmocional();
		this.falar = mood.getFalar();
		this.ouvir = mood.getOuvir();
		this.companhia = mood.getCompanhia();
		this.recado = mood.getRecado();
		this.timestamp = mood.getTimestamp();
    }



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getHumor() {
		return humor;
	}



	public void setHumor(int humor) {
		this.humor = humor;
	}



	public int getFome() {
		return fome;
	}



	public void setFome(int fome) {
		this.fome = fome;
	}



	public int getTedio() {
		return tedio;
	}



	public void setTedio(int tedio) {
		this.tedio = tedio;
	}



	public int getFisica() {
		return fisica;
	}



	public void setFisica(int fisica) {
		this.fisica = fisica;
	}



	public int getEmocional() {
		return emocional;
	}



	public void setEmocional(int emocional) {
		this.emocional = emocional;
	}



	public int getFalar() {
		return falar;
	}



	public void setFalar(int falar) {
		this.falar = falar;
	}



	public int getOuvir() {
		return ouvir;
	}



	public void setOuvir(int ouvir) {
		this.ouvir = ouvir;
	}



	public int getCompanhia() {
		return companhia;
	}



	public void setCompanhia(int companhia) {
		this.companhia = companhia;
	}



	public String getRecado() {
		return recado;
	}



	public void setRecado(String recado) {
		this.recado = recado;
	}



	public LocalDateTime getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    

}
