package es.uco.pw.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;




public class DBConnection
{
	
	private Connection ConnectionBD;
	private String BDdriver;
    private String BDurl;
    private String BDuser;
    private String BDpass;
	
	public DBConnection(String BDdriver, String BDurl, String BDuser, String BDpass) 
	{
		this.BDdriver = BDdriver;
		this.BDurl = BDurl;
		this.BDuser = BDuser;
		this.BDpass = BDpass;
	}

   public void DBConnection() throws SQLException
    {
       if (ConnectionBD == null || ConnectionBD.isClosed()) {
           try 
           {
               Class.forName(BDdriver);
           } 
           catch (ClassNotFoundException e) 
           {
               throw new SQLException(e);
           }
           ConnectionBD = DriverManager.getConnection(BDurl, BDuser, BDpass);
       }
    }

   public void desconectar() throws SQLException 
   {
       if (ConnectionBD != null && !ConnectionBD.isClosed()) 
       {
       	ConnectionBD.close();
       }
   }
   
   public Connection getJdbcConnection() 
   {
		return ConnectionBD;
   } 
}


