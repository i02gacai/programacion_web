package es.uco.pw.business.bono;

import java.time.LocalDate;

public class bonoDTO
{
     /*
        ATRIBUTOS DE LA CLASE BONO
     */

    protected int id_bono;
    protected String correo_usuario;
    protected int num_sesiones;
    protected String tipo_bono;
    protected LocalDate caducidad;

    //CONSTRUCTOR PARAMETRIZADO

    public bonoDTO(int id_bono, String correo_usuario, int num_sesiones, String tipo_bono, LocalDate caducidad)
    {
        this.id_bono = id_bono;
        this.correo_usuario = correo_usuario;
        this.num_sesiones = num_sesiones;
        this.tipo_bono = tipo_bono;
        this.caducidad = caducidad;
    }

    // CONSTRUCTOR VACIO

    public bonoDTO()
    {

    }

    // GETS & SETS

    public int getIdBono()
    {
        return this.id_bono;
    }

    public void setIdBono(int id_bono)
    {
        this.id_bono = id_bono;
    }

    public String getUsuario()
    {
        return this.correo_usuario;
    }

    public void setUsuario(String usuario)
    {
        this.correo_usuario = correo_usuario;
    }

    public int getNumSesiones()
    {
        return this.num_sesiones;
    }

    public void setNumSesiones(int num_sesiones)
    {
        this.num_sesiones = num_sesiones;
    }

    public String getTipoBono()
    {
        return this.tipo_bono;
    }

    public void setTipoBono(String tipo_bono)
    {
        this.tipo_bono = tipo_bono;
    }

    public LocalDate getCaducidad()
    {
        return this.caducidad;
    }

    public void setCaducidad(LocalDate caducidad)
    {
        this.caducidad = caducidad;
    }

    // TO STRING

    public String toStringBono()
    {
        String info = "\nID: " + this.id_bono + ". Usuario: " + this.correo_usuario + ". Numero de Sesiones: " + this.num_sesiones + ". Tipo de bono: " + this.tipo_bono + ". Caducidad: " + this.caducidad;
        return info;
    }
}
