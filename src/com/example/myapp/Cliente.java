package com.example.myapp;

import Controlador.ClienteSetGet;
import Controlador.Controlador;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by willy on 27/11/2014.
 */
public class Cliente extends Activity{
    private ClasesAdaptador cAdapter;
    private ListView lisviewListadosClientes;
    private Controlador controlador;
    private ClienteSetGet sgCliente;

    private ArrayList<ClienteSetGet> listadoClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisview);
        iniciar();
    }
    private void iniciar(){
        controlador = new Controlador(getApplicationContext(), sgCliente);

        sgCliente = new ClienteSetGet();
        listadoClientes = new ArrayList<ClienteSetGet>();
        listadoClientes = controlador.todosClientes();
        lisviewListadosClientes = (ListView) findViewById(R.id.lvClientes);
        cAdapter = new ClasesAdaptador(Cliente.this,R.layout.clienteslv, listadoClientes);
        lisviewListadosClientes.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();
    }

    private class ClasesAdaptador extends ArrayAdapter<ClienteSetGet> {
        Activity activity;
        int layoutResourceId;
        ClienteSetGet user;
        ArrayList<ClienteSetGet> data = new ArrayList<ClienteSetGet>();

        public ClasesAdaptador(Activity act, int layoutResourceId,ArrayList<ClienteSetGet> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            UserHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new UserHolder();
                holder.nombre = (TextView) row.findViewById(R.id.txtlvClientesNombre);
                holder.apellido = (TextView) row.findViewById(R.id.txtlvClientesApellidos);
                holder.direccion = (TextView) row.findViewById(R.id.txtClientesDireccion);
                row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            user = data.get(position);

            holder.nombre.setTag(user.getId());
            holder.nombre.setText(user.getNombre());
            holder.apellido.setText(user.getApellido());
            holder.direccion.setText(user.getDireccion());

            return row;
        }
        class UserHolder {
            TextView nombre,  apellido, direccion;
        }
    }
}
