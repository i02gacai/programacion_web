package es.uco.pw.controller.pista;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.javabeans.pistaBean;
import es.uco.pw.business.javabeans.reservaBean;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.reserva.reservaDTO;
import es.uco.pw.dao.kartDAO;
import es.uco.pw.dao.pistaDAO;
import es.uco.pw.dao.reservaDAO;

/**
 * Servlet implementation class obtenerPistaReservaControllerServlet
 */
@WebServlet("/obtenerPistaModificarReservaControllerServlet")
public class obtenerPistaModificarReservaControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public obtenerPistaModificarReservaControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		
		int id_reserva =Integer.parseInt(request.getParameter("id_reserva"));
		   
		
		pistaBean lista= new pistaBean();
		reservaBean reservas= new reservaBean();
		
		request.setAttribute("id_reserva", id_reserva);
		String tipo= request.getParameter("tipo");
		String seleccion="";
		if(tipo.equals("bono")) {
			seleccion=request.getParameter("seleccion_antigua");
		}
		else {
			seleccion=request.getParameter("seleccion");
		}
		
		request.setAttribute("seleccion", seleccion);
		int numero_ninos=0;
		int numero_adultos=0;
		if(seleccion.equals("infantil")) {
			numero_ninos=Integer.parseInt(request.getParameter("numero_ninos"));
			request.setAttribute("numero_ninos",request.getParameter("numero_ninos"));
		}
		else if(seleccion.equals("familiar")) {
			numero_adultos=Integer.parseInt(request.getParameter("numero_adultos"));
			numero_ninos=Integer.parseInt(request.getParameter("numero_ninos"));
			request.setAttribute("numero_adultos",request.getParameter("numero_adultos"));
			request.setAttribute("numero_ninos",request.getParameter("numero_ninos"));
		}
		else {
			numero_adultos=Integer.parseInt(request.getParameter("numero_adultos"));
			request.setAttribute("numero_adultos",request.getParameter("numero_adultos"));
		}
		
		
		String fecha = request.getParameter("fecha");
		
		fecha = String.format(fecha, "yyyy/mm/dd");
		LocalDate fecha_ = LocalDate.parse(fecha);
		
		try 
		{
			pistaDAO pistaDao = new pistaDAO(BDdriver, BDurl, BDuser, BDpass);
			kartDAO kartDao= new kartDAO(BDdriver, BDurl, BDuser, BDpass);
			reservaDAO reservaDao = new reservaDAO(BDdriver, BDurl, BDuser, BDpass);
			
			ArrayList<Integer> reserva_=reservaDao.obtenerPistas(fecha_,sql,pistaDao); //Se obtienen las pistas libres en la fecha y se le añade la pista de la reserva a modificar
			reserva_.add(Integer.parseInt(request.getParameter("id_pista")));
			
			reservas.setReservas(reserva_);
				
				lista.setPistas(pistaDao.listarPistasDificultad(seleccion,sql));//Se obtienen las pistas segun el tipo de la reserva
				
				//Descartamos las pistas que no tengan suficientes karts
				for (int i = lista.getPistas().size() - 1; i >= 0; i--) {
				    if (kartDao.contarKartsAdultos(lista.getPistas().get(i).getId_pista(), sql) < numero_adultos ||
				        kartDao.contarKartsInfantiles(lista.getPistas().get(i).getId_pista(), sql) < numero_ninos) {
				        lista.getPistas().remove(i);
				    }
				}
				
				//Nos quedamos con las pistas que esten libres y que tienen suficientes karts
				ArrayList<pistaDTO> lista_aux = new ArrayList<pistaDTO>();
				for(int i=0;i<reservas.getReservas().size();i++) {
					if(lista.getPistas().get(i).getId_pista()==reservas.getReservas().get(i)) {
						lista_aux.add(lista.getPistas().get(i));
					}
				}
				
				//Si no queda ninguna pista se muestra un mensaje de error
				if(lista_aux.size()==0) {
					RequestDispatcher rd;
					request.setAttribute("mensaje", "Error al realizar la reserva. Es posible que no haya hueco en la fecha seleccionada o que no existan pistas para tantos participantes .");
					rd = request.getRequestDispatcher("/mvc/view/perfil/perfilViewCliente.jsp");
					rd.forward(request, response);
				}
				//Si hay pistas se mostrara al usuario para que elija una de ellas
				else {
				lista.setPistas(lista_aux);
				request.setAttribute("lista_pistas", lista);
				request.setAttribute("fecha", request.getParameter("fecha"));
				request.setAttribute("tipo",seleccion);
				
				if(tipo.equals("individual")) {
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("mvc/view/reserva/elegirPistaModificarReservaIndividualView.jsp");
					rd.forward(request, response);
					}
				else {
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("mvc/view/bono/elegirPistaModificarReservaBonoView.jsp");
					rd.forward(request, response);
					}
				}

		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
