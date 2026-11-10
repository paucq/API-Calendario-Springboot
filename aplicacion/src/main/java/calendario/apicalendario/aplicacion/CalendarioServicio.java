package calendario.apicalendario.aplicacion;

import calendario.apicalendario.core.interfaces.integracion.IFestivoIntegracion;
import calendario.apicalendario.core.interfaces.repositorios.ICalendarioRepositorio;
import calendario.apicalendario.core.interfaces.repositorios.ITipoRepositorio;
import calendario.apicalendario.core.interfaces.servicios.ICalendarioServicio;
import calendario.apicalendario.dominio.entidades.Calendario;
import calendario.apicalendario.dominio.entidades.Tipo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    private static final long TIPO_DIA_LABORAL = 1L;
    private static final long TIPO_FIN_DE_SEMANA = 2L;
    private static final long TIPO_DIA_FESTIVO = 3L;

    private static final String[] DIAS = {
            "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };

    private final ICalendarioRepositorio calendarioRepositorio;
    private final ITipoRepositorio tipoRepositorio;
    private final IFestivoIntegracion festivoIntegracion;

    public CalendarioServicio(ICalendarioRepositorio calendarioRepositorio,
                              ITipoRepositorio tipoRepositorio,
                              IFestivoIntegracion festivoIntegracion) {
        this.calendarioRepositorio = calendarioRepositorio;
        this.tipoRepositorio = tipoRepositorio;
        this.festivoIntegracion = festivoIntegracion;
    }

    @Override
    @Transactional
    public boolean generar(int anio) {
        Tipo laboral = tipoRepositorio.obtener(TIPO_DIA_LABORAL);
        Tipo finDeSemana = tipoRepositorio.obtener(TIPO_FIN_DE_SEMANA);
        Tipo festivoTipo = tipoRepositorio.obtener(TIPO_DIA_FESTIVO);
        if (laboral == null || finDeSemana == null || festivoTipo == null) {
            throw new IllegalStateException("Faltan los tipos 1, 2 y 3 en la tabla tipo");
        }

        // Una sola consulta a la API Festivos; la lista queda en memoria
        Set<LocalDate> festivos = festivoIntegracion.obtenerFestivos(anio);

        // Si el año ya fue generado, se reutilizan los ids para actualizar y no duplicar
        Map<LocalDate, Long> existentes = new HashMap<>();
        for (Calendario c : calendarioRepositorio.listar(anio)) {
            existentes.put(c.getFecha(), c.getId());
        }

        LocalDate fecha = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);
        while (!fecha.isAfter(fin)) {
            Tipo tipo;
            if (festivos.contains(fecha)) {
                tipo = festivoTipo;
            } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY
                    || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                tipo = finDeSemana;
            } else {
                tipo = laboral;
            }
            String descripcion = DIAS[fecha.getDayOfWeek().getValue() - 1];
            calendarioRepositorio.guardar(
                    new Calendario(existentes.get(fecha), fecha, tipo, descripcion));
            fecha = fecha.plusDays(1);
        }
        return true;
    }

    @Override
    public List<Calendario> listar(int anio) {
        return calendarioRepositorio.listar(anio);
    }
}
