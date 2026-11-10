package calendario.apicalendario.infraestructura.integracion;

import calendario.apicalendario.core.interfaces.integracion.IFestivoIntegracion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente de la API Festivos: GET {url}/{anio} -> [{"festivo": "...", "fecha": "2023-01-01"}, ...]
 */
@Component
public class FestivoIntegracion implements IFestivoIntegracion {

    private final RestTemplate restTemplate;
    private final String url;

    public FestivoIntegracion(RestTemplateBuilder builder,
                              @Value("${servicio.festivos.url}") String url) {
        this.restTemplate = builder.build();
        this.url = url;
    }

    @Override
    public Set<LocalDate> obtenerFestivos(int anio) {
        FestivoRespuesta[] respuesta =
                restTemplate.getForObject(url + "/" + anio, FestivoRespuesta[].class);
        Set<LocalDate> fechas = new HashSet<>();
        if (respuesta != null) {
            for (FestivoRespuesta f : respuesta) {
                if (f.fecha != null && f.fecha.length() >= 10) {
                    fechas.add(LocalDate.parse(f.fecha.substring(0, 10)));
                }
            }
        }
        return fechas;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class FestivoRespuesta {
        public String festivo;
        public String fecha;
    }
}
