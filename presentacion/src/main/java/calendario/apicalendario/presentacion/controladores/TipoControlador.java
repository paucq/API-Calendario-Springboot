package calendario.apicalendario.presentacion.controladores;

import calendario.apicalendario.core.interfaces.servicios.ITipoServicio;
import calendario.apicalendario.dominio.entidades.Tipo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoControlador {

    private final ITipoServicio servicio;

    public TipoControlador(ITipoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listar")
    public List<Tipo> listar() {
        return servicio.listar();
    }

    @GetMapping("/obtener/{id}")
    public Tipo obtener(@PathVariable Long id) {
        return servicio.obtener(id);
    }

    @GetMapping("/buscar/{tipo}")
    public List<Tipo> buscar(@PathVariable String tipo) {
        return servicio.buscar(tipo);
    }

    @PostMapping("/agregar")
    public Tipo agregar(@RequestBody Tipo tipo) {
        return servicio.agregar(tipo);
    }

    @PutMapping("/modificar")
    public Tipo modificar(@RequestBody Tipo tipo) {
        return servicio.modificar(tipo);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminar(@PathVariable Long id) {
        return servicio.eliminar(id);
    }
}
