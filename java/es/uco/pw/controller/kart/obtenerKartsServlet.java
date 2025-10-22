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
import es.uco.pw.business.javabeans.kartBean;

/**
 * Servlet implementation class obtenerKartsServlet
 */
@WebServlet("/obtenerKartsServlet")
public class obtenerKartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerKartsServlet() {
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
		
		kartBean lista= new kartBean();

		
		try 
		{
			kartDAO kartDao = new kartDAO(BDdriver, BDurl, BDuser, BDpass);
				
				lista.setKarts(kartDao.listarKartsId(sql));
				request.setAttribute("lista_kart", lista);
				if(request.getParameter("asociar")==null) {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/mvc/view/kart/modificarKartView.jsp");
				rd.forward(request, response);
				}
				else {
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/obtenerPistasServlets?asociar=si");
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
