package calendario.apicalendario.infraestructura.persistencia.repositorios;

import calendario.apicalendario.core.interfaces.repositorios.ICalendarioRepositorio;
import calendario.apicalendario.dominio.entidades.Calendario;
import calendario.apicalendario.infraestructura.persistencia.mapeadores.CalendarioMapeador;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CalendarioRepositorio implements ICalendarioRepositorio {

    private final ICalendarioJPA jpa;

    public CalendarioRepositorio(ICalendarioJPA jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Calendario> listar(int anio) {
        return jpa.findByFechaBetweenOrderByFechaAsc(
                        LocalDate.of(anio, 1, 1), LocalDate.of(anio, 12, 31))
                .stream().map(CalendarioMapeador::aDominio).toList();
    }

    @Override
    public Calendario obtener(Long id) {
        return jpa.findById(id).map(CalendarioMapeador::aDominio).orElse(null);
    }

    @Override
    public Calendario guardar(Calendario calendario) {
        return CalendarioMapeador.aDominio(jpa.save(CalendarioMapeador.aEntidad(calendario)));
    }
}
