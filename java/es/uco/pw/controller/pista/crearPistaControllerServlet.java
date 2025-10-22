package es.uco.pw.controller.pista;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.dao.pistaDAO;



/**
 * Servlet implementation class registerControllerServlet
 */
@WebServlet("/crearPistaControllerServlet")
public class crearPistaControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearPistaControllerServlet() 
    {
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
		
		pistaDTO p = new pistaDTO();
		
		String nombre = request.getParameter("nombre");		
		String estado = request.getParameter("estado");
		String num_max = request.getParameter("num_max");
		
		dificultad Dificultad = dificultad.valueOf(request.getParameter("dificultad"));
		
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		


		
		try 
		{
			pistaDAO DatosDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
				
				boolean registro = DatosDao.existePista(nombre,sql);
			
				if(!registro) 
				{

					p.setNomPista(nombre);
					p.setEstPista(estado);
					p.setNumMax(Integer.parseInt(num_max));
					p.setDificultad(Dificultad);			
	
					DatosDao.crearPista(p,sql);
					
					RequestDispatcher rd;
					request.setAttribute("mensaje", "&iexclPista creada con &eacutexito!");
					rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp");
					rd.forward(request, response);
				}
				else 
				{
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/pista/crearPistaViewFail.jsp");
					rd.forward(request, response);
				}
			
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
