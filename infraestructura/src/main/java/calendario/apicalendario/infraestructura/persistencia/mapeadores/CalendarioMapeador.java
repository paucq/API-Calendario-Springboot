package calendario.apicalendario.infraestructura.persistencia.mapeadores;

import calendario.apicalendario.dominio.entidades.Calendario;
import calendario.apicalendario.infraestructura.persistencia.entidades.CalendarioEntidad;

public final class CalendarioMapeador {

    private CalendarioMapeador() {
    }

    public static Calendario aDominio(CalendarioEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Calendario(entidad.getId(), entidad.getFecha(),
                TipoMapeador.aDominio(entidad.getTipo()), entidad.getDescripcion());
    }

    public static CalendarioEntidad aEntidad(Calendario calendario) {
        if (calendario == null) {
            return null;
        }
        CalendarioEntidad entidad = new CalendarioEntidad();
        entidad.setId(calendario.getId());
        entidad.setFecha(calendario.getFecha());
        entidad.setTipo(TipoMapeador.aEntidad(calendario.getTipo()));
        entidad.setDescripcion(calendario.getDescripcion());
        return entidad;
    }
}
