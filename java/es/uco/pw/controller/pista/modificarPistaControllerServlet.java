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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import es.uco.pw.dao.kartDAO;
import es.uco.pw.dao.pistaDAO;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.kart.estado;

/**
 * Servlet implementation class modificarControllerServlet
 */
@WebServlet("/modificarPistaControllerServlet")
public class modificarPistaControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarPistaControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
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
		
		pistaDTO p = new pistaDTO();
		
		String selectedValue = request.getParameter("id_pista"); // Obtener el valor del formulario
		String[] parts = selectedValue.split("_"); // Dividir en partes usando el separador "_"
		String id_pista = parts[0];
		String nombre = parts[1]; 
		String nombre_nuevo = request.getParameter("nombre_nuevo");
		String num_max = request.getParameter("num_max");
		String estado = request.getParameter("estado");

	
		dificultad Dificultad = dificultad.valueOf(request.getParameter("dificultad"));
		
		try 
		{	
			if(nombre_nuevo.equals("")) {
				pistaDAO DatosDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
				boolean registro = DatosDao.existePista(nombre,sql);
				if(registro)
				{
				p.setNomPista(nombre);
				p.setEstPista(estado);
				p.setNumMax(Integer.parseInt(num_max));
				p.setDificultad(Dificultad);	
				
				DatosDao.updateDatos(p,Integer.parseInt(id_pista), sql);
				
				RequestDispatcher rd;
				request.setAttribute("mensaje", "&iexclPista modificada con &eacutexito!");
				rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp");
				rd.forward(request, response);
				}
				else
				{				
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/register/modificarPistaViewFail.jsp");
					rd.forward(request, response);
				}
			}
			else {
				pistaDAO DatosDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
				boolean registro = DatosDao.existePista(nombre,sql);
				if(!registro)
				{
				p.setNomPista(nombre_nuevo);
				p.setEstPista(estado);
				p.setNumMax(Integer.parseInt(num_max));
				p.setDificultad(Dificultad);	
				
				
				DatosDao.updateDatos(p,Integer.parseInt(id_pista), sql);
				
				RequestDispatcher rd;
				request.setAttribute("mensaje", "&iexclPista modificada con &eacutexito!");
				rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp");
				rd.forward(request, response);
				}
				else
				{				
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/register/modificarPistaViewFail.jsp");
					rd.forward(request, response);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
