package es.uco.pw.controller.reserva;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.javabeans.reservaBean;
import es.uco.pw.business.reserva.reservaDTO;
import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class eliminarReservaAdminControllerServlet
 */
@WebServlet("/modificarReservaClienteControllerServlet")
public class modificarReservaClienteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarReservaClienteControllerServlet() {
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
			
				
				reservaDTO reserva = reservaDao.obtenerReserva(Integer.parseInt(id), sql);			
				
				LocalDate fecha= reserva.getFecha();
            	String hora= reserva.getHora();

            	LocalTime hora_=LocalTime.parse(hora);
            	LocalDateTime fecha1 = LocalDate.now().atTime(LocalTime.now()); // Primera fecha
                LocalDateTime fecha2 = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), hora_.getHour(), hora_.getMinute()); // Segunda fecha

                // Obtener la diferencia en horas entre las dos fechas
                long horasDeDiferencia = ChronoUnit.HOURS.between(fecha1, fecha2);
				
                if(horasDeDiferencia>24) {
				request.setAttribute("reserva", reserva);	
				RequestDispatcher rd;				
				rd = request.getRequestDispatcher("/mvc/view/reserva/modificarReservaViewCliente.jsp");	
				rd.forward(request, response);
                }
                else {
                	RequestDispatcher rd;
					request.setAttribute("mensaje", "Error al modificar la reserva. Es posible que est&eacute intentando modificar una reserva con menos de 24 horas de antelación.");
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
