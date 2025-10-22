package es.uco.pw.controller.reserva;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.javabeans.customerBean;
import es.uco.pw.business.javabeans.reservaBean;
import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class obtenerReservasServlet
 */
@WebServlet("/obtenerReservasUsuarioServlet")
public class obtenerReservasUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerReservasUsuarioServlet() {
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
		
		reservaBean lista= new reservaBean();

		
		try 
		{
			reservaDAO reservaDao = new reservaDAO(BDdriver, BDurl, BDuser, BDpass);
				
				lista.setLista_reservas(reservaDao.obtenerReservasUsuarios(LocalDate.now(),customerBean.getCorreo(),sql));
				request.setAttribute("lista_reservas", lista);
				
				if(request.getParameter("modificar")==null) {
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/reserva/eliminarReservaViewCliente.jsp");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("/mvc/view/reserva/elegirReservaModificarViewCliente.jsp");
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
