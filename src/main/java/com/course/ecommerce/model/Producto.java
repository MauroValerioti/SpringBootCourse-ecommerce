package com.course.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
	private Integer id;
	private String nombre;
	private String Descripcion;
	private String imagen;
	private double precio;
	private int cantidad;
	
	public Producto(Integer id, String nombre, String descripcion, String imagen, double precio, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		Descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public Producto() {
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", Descripcion=" + Descripcion + ", imagen=" + imagen
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	
	
	
}
