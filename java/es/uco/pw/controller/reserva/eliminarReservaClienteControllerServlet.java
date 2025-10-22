package es.uco.pw.controller.reserva;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class eliminarReservaAdminControllerServlet
 */
@WebServlet("/eliminarReservaClienteControllerServlet")
public class eliminarReservaClienteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarReservaClienteControllerServlet() {
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
		
        String id = request.getParameter("id_reserva");
        String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		try 
		{
			reservaDAO reservaDao = new reservaDAO(BDdriver, BDurl, BDuser, BDpass);
				
				
				boolean resultado=reservaDao.cancelarReservaCliente(Integer.parseInt(id), sql);
				if(resultado) {
				RequestDispatcher rd;
				request.setAttribute("mensaje", "&iexclReserva eliminada con &eacutexito!");
				rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewCliente.jsp");
				rd.forward(request, response);
				}
				else {
					RequestDispatcher rd;
					request.setAttribute("mensaje", "Error al eliminar la reserva. Es posible que est&eacute intentando cancelar una reserva con menos de 24 horas de antelación.");
					rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewCliente.jsp");
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
