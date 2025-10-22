package es.uco.pw.display;

import es.uco.pw.business.bono.gestorBono;
import es.uco.pw.business.kart.gestorKart;
import es.uco.pw.business.pista.gestorPista;
import es.uco.pw.business.reserva.gestorReserva;
import es.uco.pw.business.usuario.gestorUsuarios;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {

        InputStream entradaSql = null;

        Properties sql;

            try {
                sql = new Properties();
                entradaSql = new FileInputStream(getServletContext().getResourceAsStream("/WEB-INF/config/sql.properties"));
                sql.load(entradaSql);

                gestorBono GestorBono = new gestorBono(sql);
                gestorKart GestorKart = new gestorKart(sql);
                gestorPista GestorPista = new gestorPista(sql);
                gestorUsuarios GestorUsuario = new gestorUsuarios(sql);
                gestorReserva GestorReserva = new gestorReserva(sql);

                Scanner entrada = new Scanner(System.in);
                int opcion;

                boolean controlador = true;

                while (controlador == true) {
                    System.out.println("\n0. Salir\n");
                    System.out.println("SECCION USUARIO");
                    System.out.println("-----------------------");
                    System.out.println("1. Dar de alta a un usuario");
                    System.out.println("2. Dar de baja a un usuario");
                    System.out.println("3. Listar usuarios actualmente registrados");
                    System.out.println("4. Actualizar datos de un usuario");
                    System.out.println("\nSECCION PISTA");
                    System.out.println("------------------------");
                    System.out.println("5. Crear pista");
                    System.out.println("6. Listar pistas en mantenimiento");
                    System.out.println("7. Listar pistas libres");
                    System.out.println("\nSECCION KART");
                    System.out.println("----------------------");
                    System.out.println("8. Crear karts");
                    System.out.println("9. Asociar karts");
                    System.out.println("10. Listar karts disponibles");
                    System.out.println("\nSECCION RESERVAS");
                    System.out.println("----------------------");
                    System.out.println("11. Realizar Reserva Individual");
                    System.out.println("12. Realizar Reserva con bono");
                    System.out.println("13. Sacar Bono de reservas");
                    System.out.println("14. Cancelar Reserva.");
                    System.out.println("15. Modificar Reserva.");
                    System.out.println("16. Consultar reservas a partir de hoy.");
                    System.out.println("17. Consultar reservas de una pista y día concreto.");
                    System.out.println("\n----------------------");
                    System.out.print("\nIntroduzca la opcion deseada: ");
                    opcion = entrada.nextInt();
                    entrada.nextLine();


                    switch (opcion) {
                        case 0:
                            System.out.println("Saliendo del gestor...");
                            controlador = false;
                            break;

                        case 1:
                            GestorUsuario.crearUsuarioBBDD(sql);
                            break;

                        case 2:
                            GestorUsuario.borrarUsuarioBBDD(sql);
                            break;

                        case 3:
                            String lista_usuarios = GestorUsuario.listarUsuariosBBDD(sql);
                            System.out.println("\n--- USUARIOS REGISTRADOS ---");
                            System.out.println(lista_usuarios);
                            break;

                        case 4:
                            GestorUsuario.actualizarUsuarioBBDD(sql);
                            break;

                        case 5:
                            GestorPista.crearPistaBBDD(sql);
                            break;

                        case 6:
                            String lista_pistas_mant = GestorPista.listarPistasMantenimientoBBDD(sql);
                            System.out.println("\n --- PISTAS EN MANTENIMIENTO ---");
                            System.out.println(lista_pistas_mant);
                            break;

                        case 7:
                            String lista_pistas_disp = GestorPista.listarPistasDisponiblesBBDD(sql);
                            System.out.println("\n --- PISTAS DISPONIBLES ---");
                            System.out.println(lista_pistas_disp);
                            break;

                        case 8:
                            GestorKart.crearKartBBDD(sql);
                            break;

                        case 9:
                            GestorKart.asociarKartPistaBBDD(sql);
                            break;

                        case 10:
                            String lista_karts_disp = GestorKart.listarKartsDisponiblesBBDD(sql);
                            System.out.println("\n --- KARTS DISPONIBLES ---");
                            System.out.println(lista_karts_disp);
                            break;

                        case 11:
                            GestorReserva.reservaIndividualBBDD(sql);
                            break;

                        case 12:
                            break;

                        case 13:
                            break;

                        case 14:
                            GestorReserva.cancelarReservaBBDD(sql);
                            break;

                        case 15:
                            GestorReserva.actualizarReservaBBDD(sql);
                            break;

                        case 16:
                            String lista_reservas_futu = GestorReserva.listarReservasFuturasBBDD(sql);
                            System.out.println("\n --- RESERVAS REALIZADAS ---");
                            System.out.println(lista_reservas_futu);
                            break;

                        case 17:
                            String lista_reservas_act = GestorReserva.listarReservasBBDD(sql);
                            System.out.println("\n --- RESERVAS REALIZADAS ---");
                            System.out.println(lista_reservas_act);
                            break;

                    }
                }


            } catch(FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }


    }
}