package es.uco.pw.controller.bono;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.javabeans.customerBean;
import es.uco.pw.dao.bonoDAO;

/**
 * Servlet implementation class obtenerTipoBonoControllerServlet
 */
@WebServlet("/obtenerTipoBonoControllerServlet")
public class obtenerTipoBonoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerTipoBonoControllerServlet() {
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
		
        customerBean customerBean = (customerBean) request.getSession().getAttribute("customerBean");
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		try 
		{
			bonoDAO bonoDao = new bonoDAO(BDdriver, BDurl, BDuser, BDpass);
				
				String tipo=bonoDao.tipoBono(customerBean.getCorreo(), sql);
				request.setAttribute("tipo", tipo);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/mvc/view/bono/realizarReservaBonoView.jsp");
				rd.forward(request, response);	
				

		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
