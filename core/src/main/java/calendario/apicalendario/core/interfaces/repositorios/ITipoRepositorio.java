package calendario.apicalendario.core.interfaces.repositorios;

import calendario.apicalendario.dominio.entidades.Tipo;

import java.util.List;

public interface ITipoRepositorio {

    List<Tipo> listar();

    Tipo obtener(Long id);

    List<Tipo> buscar(String tipo);

    Tipo guardar(Tipo tipo);

    boolean eliminar(Long id);
}
