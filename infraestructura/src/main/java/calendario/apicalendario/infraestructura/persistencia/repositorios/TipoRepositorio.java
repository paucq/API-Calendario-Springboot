package calendario.apicalendario.infraestructura.persistencia.repositorios;

import calendario.apicalendario.core.interfaces.repositorios.ITipoRepositorio;
import calendario.apicalendario.dominio.entidades.Tipo;
import calendario.apicalendario.infraestructura.persistencia.mapeadores.TipoMapeador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoRepositorio implements ITipoRepositorio {

    private final ITipoJPA jpa;

    public TipoRepositorio(ITipoJPA jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Tipo> listar() {
        return jpa.findAll().stream().map(TipoMapeador::aDominio).toList();
    }

    @Override
    public Tipo obtener(Long id) {
        return jpa.findById(id).map(TipoMapeador::aDominio).orElse(null);
    }

    @Override
    public List<Tipo> buscar(String tipo) {
        return jpa.findByTipoContainingIgnoreCase(tipo).stream()
                .map(TipoMapeador::aDominio).toList();
    }

    @Override
    public Tipo guardar(Tipo tipo) {
        return TipoMapeador.aDominio(jpa.save(TipoMapeador.aEntidad(tipo)));
    }

    @Override
    public boolean eliminar(Long id) {
        if (!jpa.existsById(id)) {
            return false;
        }
        jpa.deleteById(id);
        return true;
    }
}
