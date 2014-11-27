package Modelo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by willy on 26/11/2014.
 */
public class Core extends SQLiteOpenHelper{
    protected static final String DDnombre = "nombre";
    protected static final int BDversion = 1;

    /* TABLA CLIENTES */
    protected static final String tbClie = "CLIENTE";
    protected static final String tbClieID = "id";
    protected static final String tbClieNom = "nombre";
    protected static final String tbClieApe = "apellido";
    protected static final String tbClieDir = "direccion";


    public Core(Context context) {
        super(context, DDnombre, null, BDversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String tbClientes = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",tbClie,tbClieID,tbClieNom,tbClieApe,tbClieDir);
            db.execSQL(tbClientes);
        }catch(Exception ex){
            Log.e("Problemas al iniciar sqlite: ",ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
