package calendario.apicalendario.presentacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "calendario.apicalendario")
@EntityScan(basePackages = "calendario.apicalendario.infraestructura.persistencia.entidades")
@EnableJpaRepositories(basePackages = "calendario.apicalendario.infraestructura.persistencia.repositorios")
public class Aplicacion {

    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }
}
