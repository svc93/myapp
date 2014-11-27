    package com.example.myapp;

    import Controlador.Controlador;
    import Controlador.ClienteSetGet;
    import android.app.Activity;
    import android.content.Intent;
    import android.database.Cursor;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import java.util.ArrayList;

    public class Inicio extends Activity {
        /**
         * Called when the activity is first created.
         */

        private Controlador controlador;
        private ClienteSetGet sgCliente;

        private Button btnClienteNuevo,btnClienteActualizar,btnClienteElimianr,btnClienteAnterior,btnClienteSiguiente, btnListview;
        private EditText txtClienteNombre, txtClienteApellido, txtClienteDireccion;

        private ArrayList<ClienteSetGet> listadoClientes;
        private int posision;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            sgCliente = new ClienteSetGet();
            controlador = new Controlador(getApplicationContext(), sgCliente);
            listadoClientes = new ArrayList<ClienteSetGet>();
        }

        @Override
        protected void onResume() {
            super.onResume();
            iniciar();
            eventos();
            inicioRegistros();
            posision=0;
        }

        private void iniciar(){
            btnClienteNuevo = (Button) findViewById(R.id.btnClienteNuevo);
            btnClienteActualizar = (Button) findViewById(R.id.btnClienteActualizar);
            btnClienteElimianr = (Button) findViewById(R.id.btnClienteEliminar);
            btnClienteAnterior = (Button) findViewById(R.id.btnClienteAnterior);
            btnClienteSiguiente = (Button) findViewById(R.id.btnClienteSiguiente);
            btnListview = (Button) findViewById(R.id.btnListview);

            txtClienteNombre = (EditText) findViewById(R.id.txtClienteNombre);
            txtClienteApellido = (EditText) findViewById(R.id.txtClienteApellido);
            txtClienteDireccion = (EditText) findViewById(R.id.txtClienteDireccion);

        }

        private void eventos() {
            btnClienteNuevo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comprobarVacios()==false){
                        sgCliente.setNombre(txtClienteNombre.getText().toString());
                        sgCliente.setApellido(txtClienteApellido.getText().toString());
                        sgCliente.setDireccion(txtClienteDireccion.getText().toString());
                        int respuesta = controlador.agregarNuevoCliente();
                        switch (respuesta){
                            case 1:
                                Toast.makeText(getApplicationContext(),"Agregado",Toast.LENGTH_LONG).show();
                                inicioRegistros();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(),"Problemas",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(),"mmmm",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                }
            });
            btnClienteActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comprobarVacios()==false){
                        sgCliente.setId(Integer.parseInt(txtClienteNombre.getTag().toString()));
                        sgCliente.setNombre(txtClienteNombre.getText().toString());
                        sgCliente.setApellido(txtClienteApellido.getText().toString());
                        sgCliente.setDireccion(txtClienteDireccion.getText().toString());
                        int respuesta = controlador.actualizarCliente();
                        switch (respuesta){
                            case 1:
                                Toast.makeText(getApplicationContext(),"Actualizado",Toast.LENGTH_LONG).show();
                                inicioRegistros();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(),"Problemas",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(),"mmmm",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                }
            });
            btnClienteElimianr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sgCliente.setId(Integer.parseInt(txtClienteNombre.getTag().toString()));
                    int respuesta = controlador.eliminarCliente();
                    switch (respuesta){
                        case 1:
                            Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_LONG).show();
                            inicioRegistros();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(),"Problemas",Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(),"mmmm",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });

            btnClienteAnterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posision = posision - 1;
                    posision = (posision < 1)? 0 : posision;
                        txtClienteNombre.setTag(listadoClientes.get(posision).getId());
                        txtClienteNombre.setText(listadoClientes.get(posision).getNombre());
                        txtClienteApellido.setText(listadoClientes.get(posision).getApellido());
                        txtClienteDireccion.setText(listadoClientes.get(posision).getDireccion());
                }
            });
            btnClienteSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posision = posision + 1;
                    posision = (posision >= listadoClientes.size())? listadoClientes.size()-1 : posision;
                        txtClienteNombre.setTag(listadoClientes.get(posision).getId());
                        txtClienteNombre.setText(listadoClientes.get(posision).getNombre());
                        txtClienteApellido.setText(listadoClientes.get(posision).getApellido());
                        txtClienteDireccion.setText(listadoClientes.get(posision).getDireccion());
                }
            });
            btnListview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent todos = new Intent(Inicio.this, Cliente.class);
                    startActivity(todos);
                }
            });
        }

        private void inicioRegistros() {
            listadoClientes = controlador.todosClientes();
            if (listadoClientes.size()!=0){
            posision = 0;
            txtClienteNombre.setTag(listadoClientes.get(posision).getId());
            txtClienteNombre.setText(listadoClientes.get(posision).getNombre());
            txtClienteApellido.setText(listadoClientes.get(posision).getApellido());
            txtClienteDireccion.setText(listadoClientes.get(posision).getDireccion());
            }
        }

        private boolean comprobarVacios(){
            boolean respuesta = true;
            if ("".equals(txtClienteNombre.getText().toString())) {
                Toast.makeText(getApplicationContext(),"Verficar Nombre",Toast.LENGTH_LONG).show();
            }else if ("".equals(txtClienteApellido.getText().toString())) {
                Toast.makeText(getApplicationContext(),"Verificar Apellido",Toast.LENGTH_LONG).show();
            }else if ("".equals(txtClienteDireccion.getText().toString())) {
                Toast.makeText(getApplicationContext(),"Verificar Direccion",Toast.LENGTH_LONG).show();
            }else{respuesta=false;}
            return respuesta;
        }


    }
