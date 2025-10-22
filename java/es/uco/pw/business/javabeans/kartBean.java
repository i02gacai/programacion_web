package es.uco.pw.business.javabeans;

import java.util.ArrayList;
import es.uco.pw.business.kart.kartDTO;

public class kartBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> karts;

	public ArrayList<Integer> getKarts() {
		return karts;
	}

	public void setKarts(ArrayList<Integer> karts) {
		this.karts = karts;
	}
}
