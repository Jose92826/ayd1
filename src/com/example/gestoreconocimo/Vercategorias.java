package com.example.gestoreconocimo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.YuvImage;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Vercategorias extends Activity {

	public String selec_cat=""; // categoria selecionada en el listview
	private ListView categorias;
    private String [] cats; // lista para la consulta de categorias 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vercategorias);
        
		cats = Opciones.getNombreCostos("SELECT nombre FROM nombre WHERE padre ='fijo'");
		 ArrayAdapter<String> lista = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cats);
		categorias =(ListView) findViewById(R.id.lv_categorias);
		categorias.setAdapter(lista);
	
		//metodo para relacionar la listview con la context
		registerForContextMenu(categorias);
		eventos(); 
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vercategorias, menu);
		return true;
	}

	
	//metodo para crear el contextmenu.
   @Override
public void onCreateContextMenu(ContextMenu menu, View v,
		ContextMenuInfo menuInfo) {
	// TODO Auto-generated method stub
	   
	   MenuInflater menus = getMenuInflater();
	   menus.inflate(R.menu.vercategorias, menu); //relaciona el xml menu vercategorias que cree en la carpeta res>menu
	   
	super.onCreateContextMenu(menu, v, menuInfo);
}
   
   //metodo para sabar que item del menucontext fue selecionado.
   @Override
public boolean onContextItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	   switch(item.getItemId()){
	   case R.id.G_agregar:
	   break;
	   case R.id.G_nueva:
		   Intent i = new Intent( this,Nueva_categoria.class );
           i.putExtra("selecion",selec_cat);
           this.startActivityForResult(i,0);
           
		   break;
	   case R.id.G_ver:
		   break;
	   
	   }
	return super.onContextItemSelected(item);
}
   
 public void eventos(){
	
		categorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				selec_cat= cats[arg2];
				
			}
			
		});
 
 }



  
}
