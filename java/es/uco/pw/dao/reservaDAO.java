package es.uco.pw.dao;

import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.reserva.reservaDTO;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Properties;

public class reservaDAO
{
    private static reservaDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private reservaDTO reserva;

    private usuarioDTO usuario;

    int status = 0;

    protected Properties sql;

    
    public reservaDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}
    
    public reservaDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES


    public boolean existeReserva(int idReserva, Properties sql)
    {
        boolean resertExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("existeReserva");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                resertExist = true;
            }
            else
            {
                resertExist = false;
            }
            con.desconectar();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return resertExist;
    }



    public boolean comprobarAntiguedad(String correo, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();
        boolean control = false;
        
        String query = sql.getProperty("comprobarAntiguedad");
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, correo);
        
        ResultSet rs = ps.executeQuery();
        int years=0;
        if(rs.next()) {
        	LocalDate fecha_inscripcion=rs.getDate("fecha_inscripcion").toLocalDate();
        	Period period = Period.between(fecha_inscripcion, LocalDate.now());
            years = period.getYears();
        }
        
        if(years>=2) {
        	control=true;
        }
        

        return control;
    }


    public void reservaIndividual(String correo, int nom_pista, LocalDate fecha, int duracion, String hora, float precio, float descuento,int numero_adultos,int numero_ninos, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        String query = sql.getProperty("reservaBono");

        PreparedStatement ps = connection.prepareStatement(query);

        try
        {	
        	ps.setString(1, "individual");
            ps.setDouble(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setDate(6, java.sql.Date.valueOf(fecha));
            ps.setString(7, correo);
            ps.setInt(8, nom_pista);
            ps.setInt(9, numero_adultos);
            ps.setInt(10, numero_ninos);
            
            
            ps.executeUpdate();

            con.desconectar();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }
    
    public void modificarReserva(String correo, int nom_pista, LocalDate fecha, int duracion, String hora, float precio, float descuento,int numero_adultos,int numero_ninos,int id_reserva, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        String query = sql.getProperty("modificarReservaIndividual");

        PreparedStatement ps = connection.prepareStatement(query);

        try
        {
            ps.setFloat(1, precio);
            ps.setInt(2, duracion);
            ps.setFloat(3, descuento);
            ps.setString(4, hora);
            ps.setDate(5, java.sql.Date.valueOf(fecha));
            ps.setString(6, correo);
            ps.setInt(7, nom_pista);
            ps.setInt(8, numero_adultos);
            ps.setInt(9, numero_ninos);
            ps.setInt(10, id_reserva);
            
            //System.out.println(precio+","+duracion+","+descuento+","+hora+","+fecha+","+correo+","+nom_pista+","+numero_adultos+","+numero_ninos+","+id_reserva);
            
            ps.executeUpdate();

            con.desconectar();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }

    public void reservaBono(String correo, int nom_pista, LocalDate fecha, int duracion, String hora, float precio, float descuento,int numero_adultos,int numero_ninos, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        String query = sql.getProperty("reservaBono");

        PreparedStatement ps = connection.prepareStatement(query);

        try
        {	
        	ps.setString(1, "bono");
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setDate(6, java.sql.Date.valueOf(fecha));
            ps.setString(7, correo);
            ps.setInt(8, nom_pista);
            ps.setInt(9, numero_adultos);
            ps.setInt(10, numero_ninos);
            
            
            ps.executeUpdate();

            con.desconectar();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }

    public reservaDTO obtenerReserva(int id_reserva, Properties sql)
    {
        reservaDTO reserva = new reservaDTO();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("existeReserva");

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id_reserva);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                String tipo= rs.getString("tipo");
                float precio = rs.getFloat("precio");
                int duracion = rs.getInt("duracion");
                float descuento = rs.getFloat("descuento");
                String hora = rs.getString("hora");
                LocalDate fecha= rs.getDate("fecha").toLocalDate();
                String usuario = rs.getString("usuario");
                int id_pista= rs.getInt("id_pista");
                int numeros_adultos= rs.getInt("numero_adultos");
                int numero_ninos= rs.getInt("numero_ninos");
                
                reserva = new reservaDTO(idReserva, tipo, precio, duracion, descuento, hora, fecha, usuario, id_pista, numeros_adultos, numero_ninos);
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return reserva;
    }


    public ArrayList<reservaDTO> listarReservasFuturas(LocalDate fecha, String pista, Properties sql)
    {
        ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("listarReservasFuturas");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                float precio = rs.getFloat("precio");
                int duracion = rs.getInt("duracion");
                float descuento = rs.getFloat("descuento");
                String hora = rs.getString("hora");
                String usuario = rs.getString("usuario");
                lista_reservas.add(new reservaDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }



    public void cancelarReserva(int idRes, Properties sql) throws SQLException
    {
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("cancelarReserva");
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idRes);
            status = ps.executeUpdate();
            con.desconectar();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }
    
    public boolean cancelarReservaCliente(int idRes, Properties sql) throws SQLException
    {
    	boolean retorno=false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("fechaReserva");
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idRes);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
            	LocalDate fecha= rs.getDate("fecha").toLocalDate();
            	String hora= rs.getString("hora");
            	LocalTime hora_=LocalTime.parse(hora);
            	LocalDateTime fecha1 = LocalDate.now().atTime(LocalTime.now()); // Primera fecha
                LocalDateTime fecha2 = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), hora_.getHour(), hora_.getMinute()); // Segunda fecha

                // Obtener la diferencia en horas entre las dos fechas
                long horasDeDiferencia = ChronoUnit.HOURS.between(fecha1, fecha2);

            	
            	if(horasDeDiferencia>24) {
                    query = sql.getProperty("cancelarReserva");
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, idRes);
                    status = ps.executeUpdate();
                    retorno=true;
                    }
            }
         
            con.desconectar();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
        return retorno;
    }


    public void actualizarReserva(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        try
        {
            String query = sql.getProperty("actualizarReserva");
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, idRes);
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setString(6, String.valueOf(fecha));
            ps.setString(7, correo);
            ps.setString(8, nom_pista);

            status = ps.executeUpdate();
            con.desconectar();
            System.out.print("\n --- RESERVA ACTUALIZADA CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }
    
    public ArrayList<Integer> obtenerReservas(LocalDate fecha, Properties sql)
    {
    	ArrayList<Integer> lista_reservas = new ArrayList<Integer>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("obtenerReservas");

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                lista_reservas.add(idReserva);
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }
    
    public ArrayList<reservaDTO> obtenerReservasEntreDosFechas(LocalDate fecha1,LocalDate fecha2,String correo, Properties sql)
    {
    	ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("obtenerReservasEntreDosFechas");

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(fecha1));
            ps.setString(2, String.valueOf(fecha2));
            ps.setString(3, correo);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                String tipo = rs.getString("tipo");
                float precio = (float) rs.getDouble("precio");
                int duracion= rs.getInt("duracion");
                float descuento = (float) rs.getDouble("descuento");
                String hora = rs.getString("hora");
                LocalDate fecha= rs.getDate("fecha").toLocalDate();
                String usuario= rs.getString("usuario");
                int id_pista= rs.getInt("id_pista");
                int numero_adultos = rs.getInt("numero_adultos");
                int numero_ninos = rs.getInt("numero_ninos");
                
                
                lista_reservas.add(new reservaDTO(idReserva, tipo, precio, duracion, descuento, hora, fecha, usuario, id_pista, numero_adultos, numero_ninos));
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }  
    
    public ArrayList<Integer> obtenerPistas(LocalDate fecha,Properties sql,pistaDAO pistaDao){
	    	ArrayList<Integer> lista_pistas = new ArrayList<Integer>();
	    	ArrayList<Integer> lista_aux = new ArrayList<Integer>();
	    	ArrayList<pistaDTO> lista;
	    	try
	        {
	            con.DBConnection();
	            connection = con.getJdbcConnection();
	
	            String query = sql.getProperty("obtenerPistas");
	
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, String.valueOf(LocalDate.now()));
	            ps.setString(2, String.valueOf(fecha));
	            
	            ResultSet rs = ps.executeQuery();
	            
	            while(rs.next())
	            {
	                lista_aux.add(rs.getInt("id_pista"));
	            }
	            con.desconectar();
	            ps.close();
	            
	            lista = pistaDao.listarPistas(sql);
	            for(int i=0;i<lista.size();i++) {
	            	for(int j=0;j<lista_aux.size();j++) {
	            		if(lista.get(i).getId_pista()==lista_aux.get(j)) {
	            			lista.remove(i);
	            		}
	            	}
	            }
	            for(int i=0;i<lista.size();i++) {
	            	lista_pistas.add(lista.get(i).getId_pista());
	            }
	        }
	        catch (Exception e)
	        {
	            System.err.println(e);
	
	        }
	        return lista_pistas;
    }
    
    public ArrayList<reservaDTO> obtenerReservasUsuarios(LocalDate fecha,String correo, Properties sql)
    {
    	ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("obtenerReservasUsuario");

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(fecha));
            ps.setString(2, correo);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
            	int idReserva = rs.getInt("idReserva");
                String tipo = rs.getString("tipo");
                float precio = (float) rs.getDouble("precio");
                int duracion= rs.getInt("duracion");
                float descuento = (float) rs.getDouble("descuento");
                String hora = rs.getString("hora");
                LocalDate fecha_= rs.getDate("fecha").toLocalDate();
                String usuario= rs.getString("usuario");
                int id_pista= rs.getInt("id_pista");
                int numero_adultos = rs.getInt("numero_adultos");
                int numero_ninos = rs.getInt("numero_ninos");
                
                
                lista_reservas.add(new reservaDTO(idReserva, tipo, precio, duracion, descuento, hora, fecha_, usuario, id_pista, numero_adultos, numero_ninos));
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }
}
