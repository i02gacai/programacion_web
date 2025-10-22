package es.uco.pw.business.pista;

public class pistaDTO
{

    /*
        ATRIBUTOS DE LA CLASE PISTA
     */
	protected int id_pista;
	protected String nom_pista;
    protected String est_pista;
    protected dificultad Dificultad;
    protected int num_max;

    // CONSTRUCTOR PARAMETRIZADO

    public pistaDTO(int id_pista,String nom_pista, String est_pista, dificultad Dificultad, int num_max)
    {
    	this.id_pista=id_pista;
        this.nom_pista = nom_pista;
        this.est_pista = est_pista;
        this.Dificultad = Dificultad;
        this.num_max = num_max;
    }

    // CONSTRUCTOR VACIO

    public pistaDTO()
    {

    }

    // GETS & SETS

    public String getNomPista()
    {
        return this.nom_pista;
    }

    public void setNomPista(String nom_pista)
    {
        this.nom_pista = nom_pista;
    }

    public String getEstPista()
    {
        return this.est_pista;
    }

    public void setEstPista(String est_pista)
    {
        this.est_pista = est_pista;
    }

    public dificultad getDificultad()
    {
        return this.Dificultad;
    }

    public void setDificultad(dificultad Dificultad)
    {
        this.Dificultad = Dificultad;
    }

    public int getNumMax()
    {
        return this.num_max;
    }

    public void setNumMax(int num_max)
    {
        this.num_max = num_max;
    }
    
    public int getId_pista() {
		return id_pista;
	}

	public void setId_pista(int id_pista) {
		this.id_pista = id_pista;
	}

	   // TO STRING
	public String toString() {
		return "pistaDTO [id_pista=" + id_pista + ", nom_pista=" + nom_pista + ", est_pista=" + est_pista
				+ ", Dificultad=" + Dificultad + ", num_max=" + num_max + "]";
	}


	
}
