package es.uco.pw.business.reserva;

import es.uco.pw.business.usuario.gestorUsuarios;
import es.uco.pw.dao.pistaDAO;
import es.uco.pw.dao.reservaDAO;
import es.uco.pw.dao.usuarioDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class gestorReserva
{
    Properties sql;

    reservaDAO reserva = new reservaDAO(sql);

    pistaDAO pista = new pistaDAO(sql);

    usuarioDAO usuario = new usuarioDAO(sql);

    gestorUsuarios GestorUsuario = new gestorUsuarios(sql);

    public gestorReserva(Properties sql)
    {
        this.sql = sql;
        reservaDAO reserva = new reservaDAO(this.sql);
    }

    // FUNCIONES

    public float establecerPrecio(int duracion, String email, Properties sql) throws SQLException
    {
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

        if(reserva.comprobarAntiguedad(email, sql) && duracion == 60)
        {
            precio = precio - 2;
        }

        if(reserva.comprobarAntiguedad(email, sql) && duracion == 90)
        {
            precio = precio - 3;
        }

        if(reserva.comprobarAntiguedad(email, sql) && duracion == 120)
        {
            precio = precio - 4;
        }

        return precio;
    }



    public void reservaIndividualBBDD(Properties sql) throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        String correo;
        String nom_pista;
        int idRes, duracion;
        float precio, descuento;
        String hora;
        LocalDate fecha;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");



        System.out.print("Introduce el correo del usuario: ");
        correo = sc.nextLine();

        if(GestorUsuario.verificar_Email(correo) == true) {

            if (usuario.existeUsuario(correo, sql)) {
                System.out.print("Introduce el nombre de la pista: ");
                nom_pista = sc.nextLine();

                if (pista.existePista(nom_pista)) {
                    System.out.print("Introduce el ID de la reserva: ");
                    idRes = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Introduce la fecha de la reserva: ");
                    fecha = LocalDate.parse(sc.nextLine(), fmt);

                    System.out.print("Introduce la duracion de la reserva (60 min / 90 min / 120 min): ");
                    duracion = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Introduce la hora de la reserva: ");
                    hora = sc.nextLine();

                    if (reserva.comprobarAntiguedad(correo, sql) == true) {
                        descuento = 10;
                    } else {
                        descuento = 0;
                    }

                    precio = establecerPrecio(duracion, correo, sql);

                    reserva.reservaIndividual(correo, nom_pista, idRes, fecha, duracion, hora, precio, descuento, sql);
                }
            }
        }
        else
        {
            System.out.print("El correo introducido no tiene el formato correcto, intentelo de nuevo con el formato: \nusuario@dominio.com/es\n");
            reservaIndividualBBDD(sql);
        }
    }


    public String listarReservasBBDD(Properties sql)
    {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        System.out.print("Introduce la fecha de la reserva: ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), fmt);

        System.out.print("Introduce la pista: ");
        String pista = sc.nextLine();

        String reserva_list = "";

        ArrayList<reservaDTO> reservas = reserva.listarReservas(fecha, pista, sql);

        for(reservaDTO u: reservas)
        {
            reserva_list = reserva_list + u.toStringReserva() + "\n";
        }

        return reserva_list;
    }



    public String listarReservasFuturasBBDD(Properties sql)
    {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate fecha = LocalDate.now();

        System.out.print("Introduce la pista: ");
        String pista = sc.nextLine();

        String reserva_list = "";

        ArrayList<reservaDTO> reservas = reserva.listarReservasFuturas(fecha, pista, sql);

        for(reservaDTO u: reservas)
        {
            reserva_list = reserva_list + u.toStringReserva() + "\n";
        }

        return reserva_list;
    }



    public void cancelarReservaBBDD(Properties sql) throws ParseException, SQLException {
        Scanner entrada = new Scanner(System.in);
        int idRes;

        System.out.print("Introduce la ID de la reserva: ");
        idRes = entrada.nextInt();
        entrada.nextLine();

        reserva.cancelarReserva(idRes, sql);
        System.out.println("\n--- RESERVA ELIMINADA CON EXITO ---\n");
    }



    public void actualizarReservaBBDD(Properties sql) throws ParseException, SQLException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        int idRes, duracion;
        String correo, nombre, hora;
        LocalDate fecha;
        float descuento;

        System.out.print("Introduce el ID de la reserva: ");
        idRes = entrada.nextInt();
        entrada.nextLine();

        if(reserva.existeReserva(idRes, sql) == true)
        {

            System.out.print("Introduce el correo del usuario: ");
            correo = entrada.nextLine();

            if(GestorUsuario.verificar_Email(correo) == true) {

                if (usuario.existeUsuario(correo, sql) == true) {
                    System.out.print("Introduce el nombre de la pista: ");
                    nombre = entrada.nextLine();

                    if (pista.existePista(nombre) == true) {
                        System.out.print("Introduce la fecha de reserva (YYYY/MM/DD): ");
                        fecha = LocalDate.parse(entrada.nextLine(), fmt);

                        System.out.print("Introduce la duracion de la reserva: ");
                        duracion = entrada.nextInt();
                        entrada.nextLine();

                        System.out.print("Introduce la hora de la reserva: ");
                        hora = entrada.nextLine();

                        if (reserva.comprobarAntiguedad(correo, sql) == true) {
                            descuento = 10;
                        } else {
                            descuento = 0;
                        }


                        float precio = establecerPrecio(duracion, correo, sql);
                        reserva.actualizarReserva(correo, nombre, idRes, fecha, duracion, hora, precio, descuento, sql);
                    }
                }
            }
            else
            {
                System.out.print("El correo introducido no tiene el formato correcto, intentelo de nuevo con el formato: \nusuario@dominio.com/es\n");
                actualizarReservaBBDD(sql);
            }
        }
        else
        {
            String respuesta = "";
            System.out.print("La reserva introducida no existe, ¿Desea crearla? (S -> Si / N -> No): ");
            respuesta = entrada.nextLine();

            if(respuesta == "S" || respuesta == "s")
            {
                reservaIndividualBBDD(sql);
            }
            else
            {
                System.out.print("Regresando al menu...");
            }
        }
    }
}

