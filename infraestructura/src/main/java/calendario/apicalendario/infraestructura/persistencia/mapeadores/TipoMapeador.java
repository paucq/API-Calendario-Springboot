package calendario.apicalendario.infraestructura.persistencia.mapeadores;

import calendario.apicalendario.dominio.entidades.Tipo;
import calendario.apicalendario.infraestructura.persistencia.entidades.TipoEntidad;

public final class TipoMapeador {

    private TipoMapeador() {
    }

    public static Tipo aDominio(TipoEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Tipo(entidad.getId(), entidad.getTipo());
    }

    public static TipoEntidad aEntidad(Tipo tipo) {
        if (tipo == null) {
            return null;
        }
        TipoEntidad entidad = new TipoEntidad();
        entidad.setId(tipo.getId());
        entidad.setTipo(tipo.getTipo());
        return entidad;
    }
}
