package es.uco.pw.controller.pista;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import es.uco.pw.business.javabeans.pistaBean;
import es.uco.pw.dao.pistaDAO;

/**
 * Servlet implementation class obtenerPistasServlets
 */
@WebServlet("/obtenerPistasServlets")
public class obtenerPistasServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerPistasServlets() {
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
		
		pistaBean lista= new pistaBean();

		
		try 
		{
			pistaDAO pistaDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
				
				lista.setPistas(pistaDao.listarPistas(sql));
				request.setAttribute("lista_pistas", lista);
				
				if(request.getParameter("asociar") == null) {
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/pista/modificarPistaView.jsp");
					rd.forward(request, response);
				}
				else {
					request.setAttribute("lista_kart", request.getAttribute("lista_kart"));
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/kart/asociarKartPista.jsp");
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
