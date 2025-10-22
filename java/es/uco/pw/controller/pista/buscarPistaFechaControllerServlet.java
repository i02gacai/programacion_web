package es.uco.pw.controller.pista;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.javabeans.pistaBean;
import es.uco.pw.business.javabeans.reservaBean;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.dao.pistaDAO;
import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class buscarPistaFechaControllerServlet
 */
@WebServlet("/buscarPistaFechaControllerServlet")
public class buscarPistaFechaControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscarPistaFechaControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Properties sql = new Properties();
        sql.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
        
        String fecha = request.getParameter("fecha");
		
		fecha = String.format(fecha, "yyyy/mm/dd");
		LocalDate fechaInicio = LocalDate.parse(fecha);
		
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		try 
		{
		reservaBean reservas= new reservaBean();
		reservaDAO reservaDao = new reservaDAO(BDdriver, BDurl, BDuser, BDpass);
		pistaDAO pistaDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
		
		reservas.setReservas(reservaDao.obtenerPistas(fechaInicio,sql,pistaDao));
		pistaDAO DatosDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
		ArrayList<pistaDTO> lista = new ArrayList<pistaDTO>();
		ArrayList<pistaDTO> lista_aux=DatosDao.listarPistas(sql);
		
		for (int i = 0; i < reservas.getReservas().size(); i++) {
		    for (int j = 0; j < lista_aux.size(); j++) {
		        if (lista_aux.get(j).getId_pista() == reservas.getReservas().get(i)) {
		            lista.add(lista_aux.get(j));
		        }
		    }
		}

		
		request.setAttribute("lista_pistas", new pistaBean(lista));
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/mvc/view/pista/resultadoBuscarPistaView.jsp");
		rd.forward(request, response);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
