package calendario.apicalendario.core.interfaces.servicios;

import calendario.apicalendario.dominio.entidades.Calendario;

import java.util.List;

public interface ICalendarioServicio {

    boolean generar(int anio);

    List<Calendario> listar(int anio);
}
