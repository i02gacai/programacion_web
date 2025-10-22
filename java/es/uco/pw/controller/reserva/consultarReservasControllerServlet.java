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

import es.uco.pw.business.javabeans.reservaBean;
import es.uco.pw.business.javabeans.customerBean;
import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class consultarReservasControllerServlet
 */
@WebServlet("/consultarReservasControllerServlet")
public class consultarReservasControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultarReservasControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);;
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
		
		reservaBean lista= new reservaBean();
		
		String fecha_inicio = request.getParameter("fecha_inicio");
		String fecha_fin = request.getParameter("fecha_fin");
		
		fecha_inicio = String.format(fecha_inicio, "yyyy/mm/dd");
		LocalDate fechaInicio = LocalDate.parse(fecha_inicio);
		
		fecha_fin = String.format(fecha_fin, "yyyy/mm/dd");
		LocalDate  fechaFin = LocalDate.parse( fecha_fin);
		
		try 
		{
			reservaDAO reservaDao = new reservaDAO(BDdriver, BDurl, BDuser, BDpass);
				
				lista.setLista_reservas(reservaDao.obtenerReservasEntreDosFechas(fechaInicio,fechaFin,customerBean.getCorreo(),sql));
				request.setAttribute("lista_reservas", lista);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/mvc/view/reserva/resultadoConsultarReservasView.jsp");
				rd.forward(request, response);
			
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
