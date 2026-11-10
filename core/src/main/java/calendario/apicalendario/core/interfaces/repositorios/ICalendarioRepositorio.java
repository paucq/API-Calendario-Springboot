package calendario.apicalendario.core.interfaces.repositorios;

import calendario.apicalendario.dominio.entidades.Calendario;

import java.util.List;

public interface ICalendarioRepositorio {

    List<Calendario> listar(int anio);

    Calendario obtener(Long id);

    Calendario guardar(Calendario calendario);
}
