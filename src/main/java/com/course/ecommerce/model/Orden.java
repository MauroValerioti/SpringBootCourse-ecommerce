package com.course.ecommerce.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orden {

	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;
	private double total;
	
	public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
		this.id = id;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
	}

	public Orden() {
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + "]";
	}
	
	
}
