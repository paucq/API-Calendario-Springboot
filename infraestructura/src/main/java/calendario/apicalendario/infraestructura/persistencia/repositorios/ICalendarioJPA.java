package calendario.apicalendario.infraestructura.persistencia.repositorios;

import calendario.apicalendario.infraestructura.persistencia.entidades.CalendarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICalendarioJPA extends JpaRepository<CalendarioEntidad, Long> {

    List<CalendarioEntidad> findByFechaBetweenOrderByFechaAsc(LocalDate desde, LocalDate hasta);
}
