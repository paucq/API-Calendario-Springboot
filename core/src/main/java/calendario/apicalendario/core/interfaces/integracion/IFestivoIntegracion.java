package calendario.apicalendario.core.interfaces.integracion;

import java.time.LocalDate;
import java.util.Set;

public interface IFestivoIntegracion {

    /**
     * Consulta UNA sola vez la API Festivos y devuelve las fechas festivas del año.
     */
    Set<LocalDate> obtenerFestivos(int anio);
}
