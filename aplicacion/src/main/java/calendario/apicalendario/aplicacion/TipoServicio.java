package calendario.apicalendario.aplicacion;

import calendario.apicalendario.core.interfaces.repositorios.ITipoRepositorio;
import calendario.apicalendario.core.interfaces.servicios.ITipoServicio;
import calendario.apicalendario.dominio.entidades.Tipo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServicio implements ITipoServicio {

    private final ITipoRepositorio repositorio;

    public TipoServicio(ITipoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Tipo> listar() {
        return repositorio.listar();
    }

    @Override
    public Tipo obtener(Long id) {
        return repositorio.obtener(id);
    }

    @Override
    public List<Tipo> buscar(String tipo) {
        return repositorio.buscar(tipo);
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        tipo.setId(null);
        return repositorio.guardar(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        if (tipo.getId() == null || repositorio.obtener(tipo.getId()) == null) {
            return null;
        }
        return repositorio.guardar(tipo);
    }

    @Override
    public boolean eliminar(Long id) {
        return repositorio.eliminar(id);
    }
}
