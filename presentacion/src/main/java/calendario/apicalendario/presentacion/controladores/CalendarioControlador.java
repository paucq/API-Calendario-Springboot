package calendario.apicalendario.presentacion.controladores;

import calendario.apicalendario.core.interfaces.servicios.ICalendarioServicio;
import calendario.apicalendario.dominio.entidades.Calendario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarioControlador {

    private final ICalendarioServicio servicio;

    public CalendarioControlador(ICalendarioServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/calendario/generar/{anio}")
    public ResponseEntity<Boolean> generar(@PathVariable int anio) {
        try {
            return ResponseEntity.ok(servicio.generar(anio));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/calendarios/listar/{anio}")
    public List<Calendario> listar(@PathVariable int anio) {
        return servicio.listar(anio);
    }
}
