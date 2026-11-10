package calendario.apicalendario.infraestructura.persistencia.repositorios;

import calendario.apicalendario.infraestructura.persistencia.entidades.TipoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoJPA extends JpaRepository<TipoEntidad, Long> {

    List<TipoEntidad> findByTipoContainingIgnoreCase(String tipo);
}
