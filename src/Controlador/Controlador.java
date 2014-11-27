package Controlador;

import Modelo.Cliente;
import android.content.Context;

import java.util.ArrayList;

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
        return modeloCliente.insertar();
    }
    public int actualizarCliente(){
        return modeloCliente.actualizar();
    }
    public int eliminarCliente(){
        return modeloCliente.eliminar();
    }
    public ClienteSetGet buscarCliente(){
        return modeloCliente.buscarCliente();
    }
    public ArrayList<ClienteSetGet> todosClientes(){
        return modeloCliente.leerClientes();
    }
}
