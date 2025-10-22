package es.uco.pw.business.reserva;

import java.time.LocalDate;

public class reservaDTO
{
     /*
        ATRIBUTOS DE LA CLASE RESERVA
     */

    protected int id_reserva;
    protected String tipo;
    protected float precio;
    protected int duracion;
    protected float descuento;
    protected String hora;
    protected LocalDate fecha;
    protected String usuario;
    protected int id_pista;
    protected int numero_adultos;
    protected int numero_ninos;
    
	public reservaDTO(int id_reserva, String tipo, float precio, int duracion, float descuento, String hora,
			LocalDate fecha, String usuario, int id_pista, int numero_adultos, int numero_ninos) {

		this.id_reserva = id_reserva;
		this.tipo = tipo;
		this.precio = precio;
		this.duracion = duracion;
		this.descuento = descuento;
		this.hora = hora;
		this.fecha = fecha;
		this.usuario = usuario;
		this.id_pista = id_pista;
		this.numero_adultos = numero_adultos;
		this.numero_ninos = numero_ninos;
		
	}

	public reservaDTO() {
	
	}

	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getId_pista() {
		return id_pista;
	}

	public void setId_pista(int id_pista) {
		this.id_pista = id_pista;
	}

	public int getNumero_adultos() {
		return numero_adultos;
	}

	public void setNumero_adultos(int numero_adultos) {
		this.numero_adultos = numero_adultos;
	}

	public int getNumero_ninos() {
		return numero_ninos;
	}

	public void setNumero_ninos(int numero_ninos) {
		this.numero_ninos = numero_ninos;
	}

	@Override
	public String toString() {
		return "reservaDTO [id_reserva=" + id_reserva + ", tipo=" + tipo + ", precio=" + precio + ", duracion="
				+ duracion + ", descuento=" + descuento + ", hora=" + hora + ", fecha=" + fecha + ", usuario=" + usuario
				+ ", id_pista=" + id_pista + ", numero_adultos=" + numero_adultos + ", numero_ninos=" + numero_ninos
				+ "]";
	}
	
	


}
