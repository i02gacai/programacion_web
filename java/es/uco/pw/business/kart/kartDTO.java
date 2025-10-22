package es.uco.pw.business.kart;

public class kartDTO
{
     /*
        ATRIBUTOS DE LA CLASE KART
     */

    protected int id_kart;
    protected String tipo;
    protected estado Estado;

    // CONSTRUCTOR PARAMETRIZADO

    public kartDTO(int id_kart, String tipo, estado Estado)
    {
        this.id_kart = id_kart;
        this.tipo = tipo;
        this.Estado = Estado;
    }

    // CONSTRUCTOR VACIO

    public kartDTO()
    {

    }

    // GETS & SETS

    public int getIdKart()
    {
        return this.id_kart;
    }

    public void setIdKart(int id_kart)
    {
        this.id_kart = id_kart;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public estado getEstado()
    {
        return this.Estado;
    }

    public void setEstado(estado Estado)
    {
        this.Estado = Estado;
    }

    // TO STRING

    public String toStringKart()
    {
        String info = "\nID: " + this.id_kart + ". Tipo: " + this.tipo + ". Estado: " + this.Estado;
        return info;
    }
}
