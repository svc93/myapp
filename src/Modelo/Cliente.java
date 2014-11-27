package Modelo;

import Controlador.ClienteSetGet;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by willy on 27/11/2014.
 */
public class Cliente {

    private Core sqlite;
    private ClienteSetGet setget;

    public Cliente(Context context, ClienteSetGet datos){
        sqlite = new Core(context);
        setget = datos;
    }

    public int insertar(){
        int respuesta = 0;
        try{
            respuesta = 1;
            SQLiteDatabase db = sqlite.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(sqlite.tbClieNom, setget.getNombre());
            valores.put(sqlite.tbClieApe, setget.getApellido());
            valores.put(sqlite.tbClieDir, setget.getDireccion());
            db.insert(sqlite.tbClie, null, valores);
            db.close();
        }catch(Exception ex){
            respuesta = 2;
            Log.e("Problemas insertar",ex.toString());
        }
        return respuesta;
    }
    public int actualizar(){
        int respuesta = 0;
        try{
            respuesta = 1;
            SQLiteDatabase db = sqlite.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(sqlite.tbClieNom, setget.getNombre());
            valores.put(sqlite.tbClieApe, setget.getApellido());
            valores.put(sqlite.tbClieDir, setget.getDireccion());
            db.update( sqlite.tbClie,valores, sqlite.tbClieID + "=?", new String[]{""+setget.getId()});
            db.close();
        }catch(Exception ex){
            respuesta = 2;
            Log.e("Problemas actualizar",ex.toString());
        }
        return respuesta;
    }
    public int eliminar(){
        int respuesta = 0;
        try{
            respuesta = 1;
            SQLiteDatabase db = sqlite.getWritableDatabase();
            db.delete(sqlite.tbClie, sqlite.tbClieID + "=?", new String[]{"" + setget.getId()});
            db.close();
        }catch(Exception ex){
            respuesta = 2;
            Log.e("Problemas eliminar",ex.toString());
        }
        return respuesta;
    }
    public ArrayList<ClienteSetGet> leerClientes(){
        ArrayList<ClienteSetGet> lista = new ArrayList<ClienteSetGet>();
        String consulta = "SELECT * FROM "+ sqlite.tbClie;
        SQLiteDatabase db = sqlite.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);
        if (cursor.moveToFirst()){
            do{
                ClienteSetGet nuevo = new ClienteSetGet();
                nuevo.setId(Integer.parseInt(cursor.getString(0)));
                nuevo.setNombre(cursor.getString(1));
                nuevo.setApellido(cursor.getString(2));
                nuevo.setDireccion(cursor.getString(3));
                lista.add(nuevo);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
    public ClienteSetGet buscarCliente(){
        ClienteSetGet encontrado = new ClienteSetGet();
        String consulta = "SELECT * FROM "+ sqlite.tbClie + "WHERE " + sqlite.tbClieID + " = " + setget.getId();
        SQLiteDatabase db = sqlite.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);
        if (cursor.moveToFirst()){
            do{
                encontrado.setId(Integer.parseInt(cursor.getString(0)));
                encontrado.setNombre(cursor.getString(1));
                encontrado.setApellido(cursor.getString(2));
                encontrado.setDireccion(cursor.getString(3));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return encontrado;
    }

}
