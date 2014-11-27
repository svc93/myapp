package Controlador;

import Modelo.Cliente;
import android.content.Context;

/**
 * Created by willy on 26/11/2014.
 */
public class Controlador {
    Cliente modeloCliente;
    ClienteSetGet setgetCliente;

    public Controlador(Context contexto, ClienteSetGet setget){
        modeloCliente = new Cliente(contexto, setget);
    }

    public int agregarNuevoCliente(){
        int respuesta = modeloCliente.insertar();
        return respuesta;
    }
    public int actualizarCliente(){
        int respuesta = modeloCliente.actualizar();
        return respuesta;
    }
    public int eliminarCliente(){
        int respuesta = modeloCliente.eliminar();
        return respuesta;
    }
}
