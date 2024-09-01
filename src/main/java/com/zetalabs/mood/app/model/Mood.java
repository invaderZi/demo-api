package com.zetalabs.mood.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "mood")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private int humor;

    @Column(nullable = false)
    private int fome;

    @Column(nullable = false)
    private int tedio;

    @Column(nullable = false)
    private int fisica;

    @Column(nullable = false)
    private int emocional;

    @Column(nullable = false)
    private int falar;

    @Column(nullable = false)
    private int ouvir;

    @Column(nullable = false)
    private int companhia;

    @Column(length = 200)
    private String recado;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
    
	public Mood() {}

	public Mood(Long userId, int humor, int fome, int tedio, int fisica, int emocional, int falar, int ouvir,
			int companhia, String recado, LocalDateTime timestamp) {
		super();
		this.userId = userId;
		this.humor = humor;
		this.fome = fome;
		this.tedio = tedio;
		this.fisica = fisica;
		this.emocional = emocional;
		this.falar = falar;
		this.ouvir = ouvir;
		this.companhia = companhia;
		this.recado = recado;
		this.timestamp = timestamp;
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