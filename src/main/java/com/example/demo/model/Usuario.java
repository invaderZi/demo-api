package com.example.demo.model;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	private Boolean isAdmin;
    private String name;
    private String email;

    
     public Usuario() {}
     
     public Usuario(Boolean isAdmin, String name, String email) {
 		super();
 		this.isAdmin = isAdmin;
 		this.name = name;
 		this.email = email;
 	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, isAdmin, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && isAdmin == other.isAdmin
				&& Objects.equals(name, other.name);
	}

     
    
    

   
}
