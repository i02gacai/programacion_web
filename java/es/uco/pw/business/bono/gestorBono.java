package es.uco.pw.business.bono;

import es.uco.pw.dao.bonoDAO;
import es.uco.pw.dao.pistaDAO;
import es.uco.pw.dao.usuarioDAO;

import java.util.Properties;

public class gestorBono
{
    Properties sql;

    bonoDAO bono = new bonoDAO(sql);

    pistaDAO pista = new pistaDAO(sql);

    usuarioDAO usuario = new usuarioDAO(sql);

    public gestorBono(Properties sql)
    {
        this.sql = sql;
        bonoDAO bono = new bonoDAO(this.sql);
    }

    // FUNCIONES
}
