package es.uco.pw.business.javabeans;

import java.util.ArrayList;
import es.uco.pw.business.reserva.reservaDTO;

public class reservaBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> reservas;
	private ArrayList<reservaDTO> lista_reservas;

	public ArrayList<Integer> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Integer> reservas) {
		this.reservas = reservas;
	}

	public ArrayList<reservaDTO> getLista_reservas() {
		return lista_reservas;
	}

	public void setLista_reservas(ArrayList<reservaDTO> lista_reservas) {
		this.lista_reservas = lista_reservas;
	}

}
