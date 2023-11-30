package com.course.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class usuario {

	private Integer id;
	private String nombre;
	private String username;
	private String email;
	private String direccion;
	private String telefono;
	
	public usuario(Integer id, String nombre, String username, String email, String direccion, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public usuario() {
	}

	@Override
	public String toString() {
		return "usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
				+ ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	
}
