package es.uco.pw.business.javabeans;

import java.util.ArrayList;
import es.uco.pw.business.usuario.usuarioDTO;



/**
 * Clase de JavaBean para devolver el listado de los usuarios que hay que enseñar el menu del administrador
 */
public class listarUsuariosBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<usuarioDTO> usuarios;
	
	private ArrayList<String> nreservas;

	public ArrayList<usuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<usuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<String> getNreservas() {
		return nreservas;
	}

	public void setNreservas(ArrayList<String> nreservas) {
		this.nreservas = nreservas;
	}
	
	
	
}
