package es.uco.pw.controller.kart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.dao.kartDAO;

/**
 * Servlet implementation class asociarKartPistaControllerServlet
 */
@WebServlet("/asociarKartPistaControllerServlet")
public class asociarKartPistaControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public asociarKartPistaControllerServlet() {
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
		
		try 
		{
			kartDAO kartDao = new kartDAO(BDdriver, BDurl, BDuser, BDpass);
			
			String id_kart= request.getParameter("id_kart");
			String id_pista= request.getParameter("id_pista");
			
			boolean correcto= kartDao.listaKartPista(Integer.parseInt(id_kart), Integer.parseInt(id_pista), sql);
			System.out.println(correcto);
					
					if(correcto) {
					RequestDispatcher rd;
					request.setAttribute("mensaje", "&iexclKart y pista asociados con &eacutexito!");
					rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp");
					rd.forward(request, response);
					}
			
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/mvc/view/register/crearKartViewFail.jsp");
			rd.forward(request, response);
		}
	}

}
