package es.uco.pw.dao;

import es.uco.pw.business.kart.estado;
import es.uco.pw.business.kart.kartDTO;
import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class pistaDAO
{
    private static pistaDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private pistaDTO pista;
    int status = 0;

    protected Properties sql;
    
    public pistaDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}

    public pistaDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    public void crearPista(pistaDTO pista, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();
        try
        {
            String query = sql.getProperty("crearPista");
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, pista.getNomPista());
            ps.setString(2, pista.getEstPista());
            ps.setString(3, String.valueOf(pista.getDificultad()));
            ps.setInt(4, pista.getNumMax());

            ps.executeUpdate();
            con.desconectar();;
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }



    public ArrayList<pistaDTO> listarPistasMantenimiento(Properties sql)
    {
        ArrayList<pistaDTO> lista_pistas = new ArrayList<pistaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("listarPistasMantenimiento");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                //lista_pistas.add(new pistaDTO(nombre, estado, Dificultad, num_max));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }



    public ArrayList<pistaDTO> listarPistasDisponibles(Properties sql)
    {
        ArrayList<pistaDTO> lista_pistas = new ArrayList<pistaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("listarPistasDisponibles");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                //lista_pistas.add(new pistaDTO(nombre, estado, Dificultad, num_max));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }



    public boolean existePista(String nombre,Properties sql)
    {
        boolean pistaExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("existePista");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                pistaExist = true;
            }
            else
            {
                pistaExist = false;
            }
            con.desconectar();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return pistaExist;
    }
    
	
	public int updateDatos(pistaDTO pista,int id_pista, Properties sql) throws IOException {
		int status = 0;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("modificarPista");
			
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, pista.getNomPista());
			ps.setString(2, pista.getEstPista());
			ps.setString(3, String.valueOf(pista.getDificultad()));
			ps.setInt(4, pista.getNumMax());
			
			//ps.setDate(3, fecha_aux);
			ps.setInt(5, id_pista);


			status = ps.executeUpdate();
			
			//System.out.println("DATOS ACTUALIZADOS");
			con.desconectar();
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return status;
	}
	
	public ArrayList<pistaDTO> listarPistas(Properties sql)
	{
		ArrayList<pistaDTO> lista_karts = new ArrayList<pistaDTO>();

		try
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("listarPistas");


			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			int id_pista;
			String nom_pista;
		    String est_pista;
		    dificultad Dificultad;
		    int num_max;
		    
			while(rs.next())
			{
				id_pista = rs.getInt("id_pista");
				nom_pista = rs.getString("nombre");
                est_pista = rs.getString("estado");
                Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                num_max = rs.getInt("num_max");
				lista_karts.add(new pistaDTO(id_pista,nom_pista, est_pista, Dificultad, num_max));

			}

			con.desconectar();
			st.close();
		}
		catch (Exception e)
		{
			System.err.println(e);

		}

		return lista_karts;
	}
	
	public ArrayList<pistaDTO> listarPistasDificultad(String dificultad_,Properties sql)
	{
		ArrayList<pistaDTO> lista_karts = new ArrayList<pistaDTO>();

		try
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("listarPistasDificultad");

			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, dificultad_);
			ResultSet rs = ps.executeQuery();
			
			int id_pista;
			String nom_pista;
		    String est_pista;
		    dificultad Dificultad;
		    int num_max;
		    
			while(rs.next())
			{
				id_pista = rs.getInt("id_pista");
				nom_pista = rs.getString("nombre");
                est_pista = rs.getString("estado");
                Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                num_max = rs.getInt("num_max");
				lista_karts.add(new pistaDTO(id_pista,nom_pista, est_pista, Dificultad, num_max));

			}

			con.desconectar();
			ps.close();
		}
		catch (Exception e)
		{
			System.err.println(e);

		}

		return lista_karts;
	}
}
