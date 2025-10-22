package es.uco.pw.business.usuario;

import java.time.LocalDate;
import java.time.Period;

public class usuarioDTO
{
    /*
        ATRIBUTOS DE LA CLASE USUARIO
     */

    protected String correo;
    protected String nombre;
    protected LocalDate fecha_nacimiento;
    protected LocalDate fecha_inscripcion;
    protected String password;
    protected rol Rol;

    // CONSTRUCTOR PARAMETRIZADO

    public usuarioDTO(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, String password, rol Rol)
    {
        this.correo = correo;
        this.nombre = nombre;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.password = password;
        this.Rol = Rol;
        
    }

    // CONSTRUCTOR VACIO

    public usuarioDTO()
    {

    }

    // GETS & SETS

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCorreo()
    {
        return this.correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento()
    {
        return this.fecha_nacimiento;
    }

    public void setFechaNacimiento(LocalDate fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public LocalDate getFechaInscripcion()
    {
        return this.fecha_inscripcion;
    }

    public void setFechaInscripcion(LocalDate fecha_inscripcioh)
    {
        this.fecha_inscripcion = fecha_inscripcioh;
    }
    
    public String getPassword()
    {
    	return this.password;
    }
    
    public void setPassowrd(String password)
    {
    	this.password = password;
    }
    
    public rol getRol()
    {
    	return this.Rol;
    }
    
    public void setRol(rol Rol)
    {
    	this.Rol = Rol;
    }
    
    public int getAntiguedad() {
    	LocalDate fechaHoy = LocalDate.now();
    	Period periodo = Period.between(this.fecha_inscripcion, fechaHoy);
        int diferencia = periodo.getYears();
        
        return diferencia;

    }

    // TO STRING

    public String toStringUsuario()
    {
        String info = "\nNombre: " + this.nombre + ". Correo: " + this.correo + ". Fecha Nacimiento: " + this.fecha_nacimiento + ". Fecha Inscripcion: " + this.fecha_inscripcion;
        return info;
    }
}

