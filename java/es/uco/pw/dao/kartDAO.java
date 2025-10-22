package es.uco.pw.dao;

import es.uco.pw.business.kart.estado;
import es.uco.pw.business.kart.kartDTO;
import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.usuario.rol;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class kartDAO
{
    private static kartDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private kartDTO kart;

    private pistaDTO pista;

    int status = 0;

    protected Properties sql;
    
    public kartDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}

    public kartDAO(Properties sql)
    {
        this.sql = sql;
    }



    // FUNCIONES

    public void crearKart(kartDTO kart, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();
        try
        {
            String query = sql.getProperty("crearKart");
            PreparedStatement ps = connection.prepareStatement(query);

            //ps.setInt(1, id_kart);
            ps.setString(1, kart.getTipo());
            ps.setString(2, String.valueOf(kart.getEstado()));

            ps.executeUpdate();
            con.desconectar();
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }



    public ArrayList<kartDTO> listarKartsDisponibles(Properties sql)
    {
        ArrayList<kartDTO> lista_karts = new ArrayList<kartDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("listarKartsDisponibles");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int id_kart = rs.getInt("id_kart");
                String tipo = rs.getString("tipo");
                estado Estado = estado.valueOf(rs.getString("estado"));
                lista_karts.add(new kartDTO(id_kart, tipo, Estado));
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



    public boolean listaKartPista(int id_kart, int id_pista, Properties sql) throws SQLException
    {
    	con.DBConnection();
    	connection = con.getJdbcConnection();
    	boolean retorno=true;
    	
        try
        {
            String query_pista = sql.getProperty("queryPista"); // Se controla en la sentencia SQL que esten disponibles tambien
            String query_kart = sql.getProperty("query_kart"); // Se controla en la sentencia SQL que esten disponibles tambien
            String query_pista_kart = sql.getProperty("query_pista_kart");
            dificultad Dificultad;
            String tipo_kart;
            String dif;
            String tipo;

            PreparedStatement ps1 = connection.prepareStatement(query_pista);
            ps1.setInt(1, id_pista);
            ResultSet rs_pista = ps1.executeQuery();

            kart = new kartDTO();
            pista = new pistaDTO();

            while(rs_pista.next())
            {
                Dificultad = dificultad.valueOf(rs_pista.getString("dificultad"));
                pista.setDificultad(Dificultad);
            }

            ps1.close();

            PreparedStatement ps2 = connection.prepareStatement(query_kart);
            ps2.setInt(1, id_kart);
            ResultSet rs_kart = ps2.executeQuery();

            while(rs_kart.next())
            {
                tipo_kart = rs_kart.getString("tipo");
                kart.setTipo(tipo_kart);
            }

            ps2.close();

            PreparedStatement ps = connection.prepareStatement(query_pista_kart);
            dif = String.valueOf(pista.getDificultad());
            tipo = kart.getTipo();
            
            if(dif.equals("infantil") && tipo.equals("niño"))
            {
                ps.setInt(1, id_kart);
                ps.setInt(2, id_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            else if(dif.equals("adultos") && tipo.equals("adulto"))
            {
                ps.setInt(1, id_kart);
                ps.setInt(2, id_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            else if(dif.equals("familiar") && tipo.equals("niño"))
            {
                ps.setInt(1, id_kart);
                ps.setInt(2, id_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            else if(dif.equals("familiar") && tipo.equals("adulto"))
            {
                ps.setInt(1, id_kart);
                ps.setInt(2, id_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }
            else {
            	retorno=false;
            }
            con.desconectar();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return retorno;
    }



    public boolean existeKart(int id_kart, Properties sql)
    {
        boolean kartExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("existeKart");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                kartExist = true;
            }
            else
            {
                kartExist = false;
            }
            con.desconectar();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return kartExist;
    }

	public int comprobarRegistro(int id) throws IOException 
	{
		int comprobacion = 5;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			Statement statement2 = connection.createStatement();
			ResultSet resultset2 = statement2.executeQuery("select * from kart where id_kart = " + id + "");

			while (resultset2.next()) 
			{

				comprobacion = 0;

			}
			
			con.desconectar();
		} catch (Exception e) {
			System.out.println(e);
		}

		return comprobacion;
	}
	

	
	public ArrayList<kartDTO> listarKarts()
    {
        ArrayList<kartDTO> lista_karts = new ArrayList<kartDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = "SELECT * FROM kart";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int id_kart = rs.getInt("id_kart");
                String tipo = rs.getString("tipo");
                estado Estado = estado.valueOf(rs.getString("estado"));
                lista_karts.add(new kartDTO(id_kart, tipo, Estado));

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
	
	public int modificarKart(kartDTO kart, Properties sql) throws IOException {
		int status = 0;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("modificarKart");
			
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, kart.getIdKart());
			ps.setString(2, kart.getTipo());
			ps.setString(3, String.valueOf(kart.getEstado()));
			ps.setInt(4, kart.getIdKart());
			


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


	public ArrayList<Integer> listarKartsId(Properties sql)
	{
		ArrayList<Integer> lista_karts = new ArrayList<Integer>();

		try
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("listarKart");


			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next())
			{
				int id_kart = rs.getInt("id_kart");
				lista_karts.add(id_kart);

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
	
	public int contarKartsInfantiles(int id_pista, Properties sql) throws IOException {
		int status = 0;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("contarKartsInfantiles");
			
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, id_pista);
			

			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				status = rs.getInt(1);
			}
			
			ps.close();
			con.desconectar();
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return status;
	}
	
	public int contarKartsAdultos(int id_pista, Properties sql) throws IOException {
		int status = 0;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			String query = sql.getProperty("contarKartsAdultos");
			
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, id_pista);
			

			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				status = rs.getInt(1);
			}
			
			ps.close();
			con.desconectar();
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return status;
	}

}
