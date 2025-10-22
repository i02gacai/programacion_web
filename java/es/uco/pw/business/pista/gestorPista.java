package es.uco.pw.business.pista;

import es.uco.pw.dao.pistaDAO;
import es.uco.pw.dao.usuarioDAO;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.sql.SQLException;

public class gestorPista
{
    Properties sql;

    pistaDAO pista = new pistaDAO(sql);

    public gestorPista(Properties sql)
    {
        this.sql = sql;
        usuarioDAO pista = new usuarioDAO(this.sql);
    }

    // FUNCIONES

    public void crearPistaBBDD(Properties sql) throws SQLException
    {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        String estado;
        dificultad Dificultad;
        int num_max;




        System.out.print("Introduce el nombre de la pista: ");
        nombre = entrada.nextLine();

        System.out.print("Introduce el estado de la pista (Disponible / Ocupada / En Mantenimiento): ");
        estado = entrada.nextLine();

        System.out.print("Introduce la dificultad de la pista (INFANTIL / FAMILIAR / ADULTOS): ");
        Dificultad = dificultad.valueOf(entrada.nextLine());

        System.out.print("Introduce el numero maximo de karts para la pista: ");
        num_max = entrada.nextInt();



        pista.crearPista(nombre, estado, Dificultad, num_max, sql);
    }


    public String listarPistasMantenimientoBBDD(Properties sql)
    {
        String pista_list = "";

        ArrayList<pistaDTO> pistas = pista.listarPistasMantenimiento(sql);

        for(pistaDTO u: pistas)
        {
            pista_list = pista_list + u.toStringPista() + "\n";
        }

        return pista_list;
    }


    public String listarPistasDisponiblesBBDD(Properties sql)
    {
        String pista_list = "";

        ArrayList<pistaDTO> pistas = pista.listarPistasDisponibles(sql);

        for(pistaDTO u: pistas)
        {
            pista_list = pista_list + u.toStringPista() + "\n";
        }

        return pista_list;
    }
}
