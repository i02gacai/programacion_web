package es.uco.pw.controller.bono;

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

import es.uco.pw.business.javabeans.customerBean;
import es.uco.pw.dao.bonoDAO;
import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class realizarReservaIndividualControllerServlet
 */
@WebServlet("/realizarReservaBonoControllerServlet")
public class realizarReservaBonoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public realizarReservaBonoControllerServlet() {
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
		

        customerBean customerBean = (customerBean) request.getSession().getAttribute("customerBean");
		
		String tipo=request.getParameter("seleccion");
		
		int numero_adultos=0;
		int numero_ninos=0;
		if(tipo.equals("infantil")) {
			numero_ninos=Integer.parseInt(request.getParameter("numero_ninos"));
		}
		else if(tipo.equals("familiar")) {
			numero_adultos=Integer.parseInt(request.getParameter("numero_adultos"));
			numero_ninos=Integer.parseInt(request.getParameter("numero_ninos"));

		}
		else {
			numero_adultos=Integer.parseInt(request.getParameter("numero_adultos"));

		}
		
		String fecha = request.getParameter("fecha");
		String hora = request.getParameter("hora");
		
		fecha = String.format(fecha, "yyyy/mm/dd");
		LocalDate fecha_ = LocalDate.parse(fecha);
		
		int id_pista=Integer.parseInt(request.getParameter("id_pista"));
		int duracion=Integer.parseInt(request.getParameter("duracion"));
		
		float precio = 0;

        if(duracion == 60)
        {
            precio = 20;
        }
        else if(duracion == 90)
        {
            precio = 30;
        }
        else if(duracion == 120)
        {
            precio = 40;
        }
		
		try {
			reservaDAO reservaDao = new reservaDAO(BDdriver, BDurl, BDuser, BDpass);
			bonoDAO bonoDao = new bonoDAO(BDdriver, BDurl, BDuser, BDpass);
			
			int id_bono=bonoDao.idBono(customerBean.getCorreo(), sql);
			int n_sesiones=bonoDao.sesionesBono(id_bono, sql);
			
			float descuento=(float) 0.05;

			
			reservaDao.reservaBono(customerBean.getCorreo(), id_pista, fecha_, duracion, hora, precio, descuento, numero_adultos, numero_ninos, sql);
			bonoDao.actualizarSesionesBono(n_sesiones-1, id_bono, sql);
			RequestDispatcher rd;
			request.setAttribute("mensaje", "&iexclReserva con bono realizada con &eacutexito!");
			rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewCliente.jsp");
			rd.forward(request, response);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
