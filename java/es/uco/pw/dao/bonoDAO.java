package es.uco.pw.dao;

import es.uco.pw.business.bono.bonoDTO;
import es.uco.pw.connection.DBConnection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;

public class bonoDAO
{
    private static bonoDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private bonoDTO entidades;
    int status = 0;

    protected Properties sql;
    
    public bonoDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}
    
    public bonoDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    public boolean existeBono(int IDbono,Properties sql)
    {
        boolean bonoExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql.getProperty("existeBono"));
            ps.setInt(1, IDbono);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                bonoExist = true;
            }
            else
            {
                bonoExist = false;
            }
            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }

    public int crearBono(String correo,String tipo,Properties sql)
    {

        int status=0;

        try 
        {
        	con.DBConnection();
        	connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("crearBono"));

                ps.setString(1, correo);
                ps.setInt(2, 5);
                ps.setString(3, tipo);
                ps.setDate(4, Date.valueOf(LocalDate.now().plusYears(1)));

                status = ps.executeUpdate();
                con.desconectar();
                ps.close();

        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }


    public bonoDTO obtenerBono(String correo_,Properties sql)
    {
        bonoDTO bono = new bonoDTO();

        try
        {
            con.DBConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("tieneBono"));
            ps.setString(1, correo_);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int Id_bono=rs.getInt("id_bono");
                String correo=rs.getString("correo_usuario");
                int num_sesiones=rs.getInt("num_sesiones");
                String tipo=rs.getString("tipo_bono");
                LocalDate caducidad=rs.getDate("caducidad").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                bono = new bonoDTO(Id_bono,correo,num_sesiones,tipo,caducidad);
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return bono;
    }
    
    
    public ArrayList<bonoDTO> listarBonos(Properties sql)
    {
        ArrayList<bonoDTO> lista_bonos = new ArrayList<bonoDTO>();

        try
        {
            con.DBConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("listarBono"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int Id_bono=rs.getInt("id_bono");
                String correo=rs.getString("correo_usuario");
                int num_sesiones=rs.getInt("num_sesiones");
                String tipo=rs.getString("tipo_bono");
                LocalDate caducidad=rs.getDate("caducidad").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                double descuento=rs.getDouble("desc");

                lista_bonos.add(new bonoDTO(Id_bono,correo,num_sesiones,tipo,caducidad,descuento));
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_bonos;
    }

    public int eliminarUsuario(int id,Properties sql) throws SQLException
    {
        int status=0;
        try
        {
            con.DBConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("borrarBono"));
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
        return status;
    }

    public boolean tieneBono(String correo,Properties sql) throws SQLException
    {		
    	    boolean bonoExist=false;
            con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("tieneBono"));
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
          
            if(rs.next())
            {
            	int numero=rs.getInt(1);
            	if(numero>0) {
            		bonoExist=true;
            	}
            	
            }
            con.desconectar();
            ps.close();
        

        return bonoExist;
    }
    
    public String tipoBono(String correo,Properties sql)
    {
        String tipo="";
        try
        {
        	con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("tieneBono"));
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {	
            	tipo=rs.getString("tipo"); 
            }

            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return tipo;
    }
    
    public int idBono(String correo,Properties sql)
    {
        int id=0;
        try
        {
        	con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("tieneBono"));
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {	
            	id=rs.getInt("id_bono"); 
            }

            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return id;
    }
    
    public int sesionesBono(int id_bono,Properties sql)
    {
        int id=0;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("sesionesBono"));
            ps.setInt(1, id_bono);
            ResultSet rs = ps.executeQuery();


            while(rs.next())
            {	
            	id=rs.getInt("n_sesiones");
            }

            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return id;
    }
    
    public void actualizarSesionesBono(int n_sesiones,int id_bono,Properties sql)
    {
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("actualizarSesionesBono"));
            ps.setInt(1, n_sesiones);
            ps.setInt(2, id_bono);
            ps.executeUpdate();

            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }

    }
}
