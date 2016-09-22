package comun;

import java.io.Serializable;

/**
 * Created by 1144090943 on 19/09/2016.
 */

public class Usuario implements Serializable {

    private String nombre, contraseña;
    private static final long serialVersionUID = 1L;

    public Usuario(String nombre, String contraseña) {

        this.nombre = nombre;
        this.contraseña = contraseña;

    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }


}
