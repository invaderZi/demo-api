package com.zetalabs.mood.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

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
    
	public Mood() {}

	public Mood(Usuario user, int humor, int fome, int tedio, int fisica, int emocional, int falar, int ouvir,
			int companhia, String recado, LocalDateTime timestamp) {
		super();
		this.user = user;
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

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
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

	@Override
	public int hashCode() {
		return Objects.hash(companhia, emocional, falar, fisica, fome, humor, id, ouvir, recado, tedio, timestamp,
				user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mood other = (Mood) obj;
		return companhia == other.companhia && emocional == other.emocional && falar == other.falar
				&& fisica == other.fisica && fome == other.fome && humor == other.humor && Objects.equals(id, other.id)
				&& ouvir == other.ouvir && Objects.equals(recado, other.recado) && tedio == other.tedio
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(user, other.user);
	}

	
	

	
	
   
}