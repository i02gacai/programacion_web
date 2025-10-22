package es.uco.pw.controller.pista;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.javabeans.pistaBean;
import es.uco.pw.dao.kartDAO;
import es.uco.pw.dao.pistaDAO;

/**
 * Servlet implementation class buscarPistaDificultadControllerServlet
 */
@WebServlet("/buscarPistaKartControllerServlet")
public class buscarPistaKartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscarPistaKartControllerServlet() {
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
        
        int karts = Integer.parseInt(request.getParameter("karts_disponibles"));
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		try 
		{
		pistaDAO DatosDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
		ArrayList<pistaDTO> lista_aux=DatosDao.listarPistas(sql);
		kartDAO kartDao= new kartDAO(BDdriver, BDurl, BDuser, BDpass);
		
		System.out.println(lista_aux);
		for (int i = lista_aux.size() - 1; i >= 0; i--) {
		    if (kartDao.contarKartsAdultos(lista_aux.get(i).getId_pista(), sql)+kartDao.contarKartsInfantiles(lista_aux.get(i).getId_pista(), sql) < karts) {
		        lista_aux.remove(i);
		    }
		}
		System.out.println(lista_aux);
		request.setAttribute("lista_pistas", new pistaBean(lista_aux));
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
