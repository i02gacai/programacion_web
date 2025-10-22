package es.uco.pw.business.usuario;

import es.uco.pw.dao.usuarioDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gestorUsuarios
{
    Properties sql;

    usuarioDAO usuario = new usuarioDAO(sql);

    public gestorUsuarios(Properties sql)
    {
        this.sql = sql;
        usuarioDAO usuario = new usuarioDAO(this.sql);
    }

    // FUNCIONES

    public boolean verificar_Email(String correo)
    {

        //Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$");

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mat = pattern.matcher(correo);
        return mat.find();

    }

    public String listarUsuariosBBDD(Properties sql)
    {
        String list_usuario = "";

        ArrayList<usuarioDTO> usuarios = usuario.listarUsuariosRegistrados();

        for(usuarioDTO u: usuarios)
        {
            list_usuario = list_usuario + u.toStringUsuario() + "\n";
        }

        return list_usuario;
    }

    public boolean verificar_fecha(String fecha)
    {
        //Pattern pattern = Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])(\\/|-)\\2(\\d{4})$");

        Pattern pattern = Pattern.compile("^(2[0-9][0-9][0-9]|1[0-9][0-9][0-9])(\\/|-)(0[1-9]|1[0-2])(\\/|-)([0-2][0-9]|3[0-1])$");

        Matcher mat = pattern.matcher(fecha);
        return mat.find();


    }

    public void crearUsuarioBBDD(Properties sql) throws ParseException, SQLException
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo, fecha_inscripcion_aux, fecha_nacimiento_aux;
        LocalDate fecha_inscripcion, fecha_nacimiento;

        fecha_inscripcion = LocalDate.now();

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        if(verificar_Email(correo) == true)
        {
            System.out.print("Introduce el nombre del usuario: ");
            nombre = entrada.nextLine();

            System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
            fecha_nacimiento_aux = entrada.nextLine();

            if(verificar_fecha(fecha_nacimiento_aux))
            {
                fecha_nacimiento = LocalDate.parse(fecha_nacimiento_aux, fmt);
                usuario.registrarUsuario(correo, nombre, fecha_nacimiento, fecha_inscripcion, sql);
            }
            else
            {
                System.out.print("ERROR");
                crearUsuarioBBDD(sql);
            }
        }
        else
        {
            System.out.print("El correo introducido no tiene el formato correcto, intentelo de nuevo con el formato: \nusuario@dominio.com/es\n");
            crearUsuarioBBDD(sql);
        }
    }


    public void borrarUsuarioBBDD(Properties sql) throws ParseException, SQLException {
        Scanner entrada = new Scanner(System.in);
        String correo;

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        if (verificar_Email(correo))
        {
            usuario.eliminarUsuario(correo, sql);
            System.out.println("\n--- USUARIO ELIMINADO CON EXITO ---\n");
        }
        else
        {
            System.out.print("El correo introducido no tiene el formato correcto, intentelo de nuevo con el formato: \nusuario@dominio.com/es\n");
            borrarUsuarioBBDD(sql);
        }
    }

    public void actualizarUsuarioBBDD(Properties sql) throws ParseException, SQLException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo_aux, correo, fecha_inscripcion_aux, fecha_nacimiento_aux, control;
        LocalDate fecha_inscripcion, fecha_nacimiento;


        System.out.print("Introduce el email del usuario que desea actualizar: ");
        correo_aux = entrada.nextLine();

        if(verificar_Email(correo_aux) == true)
        {
            if (usuario.existeUsuario(correo_aux, sql) == true) {

                System.out.print("Introduce el correo del usuario: ");
                correo = entrada.nextLine();

                if (verificar_Email(correo) == true)
                {

                    System.out.print("Introduce el nombre del usuario: ");
                    nombre = entrada.nextLine();

                    System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
                    fecha_nacimiento_aux = entrada.nextLine();
                    fecha_nacimiento = LocalDate.parse(fecha_nacimiento_aux, fmt);

                    System.out.print("¿Desea modificar la fecha de inscripcion? (S -> Si / N -> No:");
                    control = entrada.nextLine();

                    if (control.equals("S")) {
                        System.out.print("¿Desea aplicar la fecha actual (S / N)?: ");
                        control = entrada.nextLine();

                        if (control.equals("S")) {
                            fecha_inscripcion = LocalDate.now();
                        } else {
                            System.out.print("Introduce la fecha de inscripcion (YYYY/MM/DD): ");
                            fecha_inscripcion_aux = entrada.nextLine();
                            fecha_inscripcion = LocalDate.parse(fecha_inscripcion_aux, fmt);
                        }
                    } else {
                        fecha_inscripcion = usuario.obtenerFecha(correo, sql);

                    }

                    usuario.actualizarUsuario(correo, nombre, fecha_nacimiento, fecha_inscripcion, correo_aux, sql);
                }
                else
                {
                    System.out.print("El correo introducido no tiene el formato correcto, intentelo de nuevo con el formato: \nusuario@dominio.com/es\n");
                    actualizarUsuarioBBDD(sql);
                }
            }
            else
            {
                String respuesta = "";
                System.out.print("El usuario introducido no existe en la BD, ¿Desea crearlo? (S -> Si / N -> No): ");
                respuesta = entrada.nextLine();

                if (respuesta == "S" || respuesta == "s") {
                    crearUsuarioBBDD(sql);
                } else {
                    System.out.print("Regresando al menu...");
                }
            }
        }
        else
        {
            System.out.print("El correo introducido no tiene el formato correcto, intentelo de nuevo con el formato: \nusuario@dominio.com/es\n");
            actualizarUsuarioBBDD(sql);
        }
    }



}
