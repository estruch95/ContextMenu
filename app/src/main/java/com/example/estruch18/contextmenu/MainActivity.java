package com.example.estruch18.contextmenu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.estruch18.contextmenu.R.menu.context_menu;


public class MainActivity extends Activity {
    //Atributos de la clase
    private ListView listaContactos;
    private ArrayList<String> contenidoLista;
    private ArrayAdapter<String> adaptador;
    private TextView textoInfo;
    private String contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaración de los atributos de la clase
        listaContactos = (ListView)findViewById(R.id.lv_Contactos);
        textoInfo = (TextView)findViewById(R.id.t_Info);
        textoInfo.setTextColor(Color.WHITE);
        //Relleno de ArrayList<String>
        contenidoLista = new ArrayList<String>();
        contenidoLista.add("Ivan Estruch");
        contenidoLista.add("Joaquin Bahamonde");
        contenidoLista.add("Adrian Rodriguez");
        contenidoLista.add("Jose Igualada");
        contenidoLista.add("Silvia Reolid");
        contenidoLista.add("David Alós");

        //Declaración del adaptador
        adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, contenidoLista);

        //Añadimos el adaptador a la lista
        listaContactos.setAdapter(adaptador);

        //Asociamos el View
        registerForContextMenu(listaContactos);

        //Ejecución de métodos
        //getContacto();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    //Inflado del xml del context menú
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        contacto = ((TextView)info.targetView).getText().toString();
        menu.setHeaderTitle(contacto);
    }

    //Gestión del item seleccionado en el context menú
    public boolean onContextItemSelected(MenuItem item){
        String accion = item.getTitle().toString();
        //Condicionamos la selección del item
        switch (accion){
            case "Mostrar":
                //Información por pantalla
                Toast.makeText(getApplicationContext(), contacto+": Opción "+accion, Toast.LENGTH_SHORT).show();
                break;
            case "Eliminar":
                //""
                Toast.makeText(getApplicationContext(), contacto+": Opción "+accion, Toast.LENGTH_SHORT).show();
                break;
        }
        textoInfo.setTextColor(Color.WHITE);
        textoInfo.setText("Has seleccionado: " + accion);
        return true;
    }
}
