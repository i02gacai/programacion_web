package es.uco.pw.controller.kart;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import es.uco.pw.dao.kartDAO;
import es.uco.pw.business.kart.kartDTO;
import es.uco.pw.business.kart.estado;

/**
 * Servlet implementation class modificarControllerServlet
 */
@WebServlet("/modificarKartControllerServlet")
public class modificarKartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarKartControllerServlet() {
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
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		kartDTO kart = new kartDTO();
		String id = request.getParameter("id_kart");
		String tipo = request.getParameter("tipo");

	
		estado Estado = estado.valueOf(request.getParameter("estado"));
		try 
		{
			//Salta el warning porque no se usa el objeto pero es necesario crearlo para pasarle las variables de la base de datos.
			kartDAO DatosDao = new kartDAO(BDdriver, BDurl, BDuser, BDpass);
			
			kart.setIdKart(Integer.valueOf(id));
			kart.setTipo(tipo);
			kart.setEstado(Estado);
			
			DatosDao.modificarKart(kart,sql);
			RequestDispatcher rd;
			request.setAttribute("mensaje", "&iexclKart modificado con &eacutexito!");
			rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
