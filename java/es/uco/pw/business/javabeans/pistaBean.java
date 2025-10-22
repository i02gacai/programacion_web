package es.uco.pw.business.javabeans;
import java.util.ArrayList;
import es.uco.pw.business.pista.pistaDTO;


public class pistaBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<pistaDTO> pistas;
	
	
	
	public pistaBean() {
		super();
	}

	public pistaBean(ArrayList<pistaDTO> pistas) {
		super();
		this.pistas = pistas;
	}

	public ArrayList<pistaDTO> getPistas() {
		return pistas;
	}

	public void setPistas(ArrayList<pistaDTO> pistas) {
		this.pistas = pistas;
	}
}
