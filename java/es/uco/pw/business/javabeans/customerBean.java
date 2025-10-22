package es.uco.pw.business.javabeans;

import java.time.LocalDate;

public class customerBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String correo = "";
	private String rol = "";
	private String nombre= "";
	private LocalDate fecha_nacimiento;
    private LocalDate fecha_inscripcion;
    private LocalDate fecha_reserva;
    private LocalDate fecha_actual;
	
    
    public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	
	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public LocalDate getFecha_inscripcion() {
		return fecha_inscripcion;
	}
	
	public void setFecha_inscripcion(LocalDate fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}
	
	public LocalDate getFecha_actual() {
		return fecha_actual;
	}
	
	public void setFecha_actual(LocalDate fecha_actual) {
		this.fecha_actual = fecha_actual;
	}

	public LocalDate getFecha_reserva() {
		return fecha_reserva;
	}

	public void setFecha_reserva(LocalDate fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}
	
	
}