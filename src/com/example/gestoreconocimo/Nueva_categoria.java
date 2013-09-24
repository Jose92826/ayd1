package com.example.gestoreconocimo;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;

public class Nueva_categoria extends Activity {
       private String prueva="";
       
       // creacion de botones para relacionarlos con los botones del activity
       private Button crear;
       private Button cancelar;
       private EditText nuevo_nombre;
	   
       
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_categoria);
		
	   prueva =this.getIntent().getExtras().getString("selecion");
		
		crear = (Button) findViewById(R.id.btn_crear);
		cancelar=(Button)findViewById(R.id.btn_cancelar);
		nuevo_nombre = (EditText)findViewById(R.id.gftxt_costo);
		nuevo_nombre.setText(prueva);
		
		eventos();
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nueva_categoria, menu);
		
	
		return true;
		
	   
	
	}

	
	public void eventos(){
		//para enviar el activity a travez del evento de Onclick
    	final Activity atv=this;
    	crear.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//agregando nombre
			    prueva =  nuevo_nombre.getText().toString(); 
				funcion_agregar_nombre( prueva);
				 Intent i = new Intent(Nueva_categoria.this,Gastofijo.class );
		           Nueva_categoria.this.startActivityForResult(i,0);
		           Nueva_categoria.this.finish();
			}
        });
    	
    	cancelar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//agregando nombre
				 Intent i = new Intent(Nueva_categoria.this,Gastofijo.class );
		           Nueva_categoria.this.startActivityForResult(i,0);
		           Nueva_categoria.this.finish();
			}
        });
    	
	}
public boolean funcion_agregar_nombre(String nuevo_nombre){
		
		boolean rtn = true;
			final Activity atv=this;
			if(!nuevo_nombre.equals("")){
				//si es un nuevo nombre distinto de vacio, se agrega a la base de datos
				
				boolean b=nuevo_nombre.matches("[a-zA-Z]+[a-zA-Z\\s0-9]*");
				if(b){
					ContentValues parametros = new ContentValues();   
				    parametros.put("nombre",nuevo_nombre);
				    parametros.put("padre","fijo");
				    boolean resultado=Opciones.insert("nombre",parametros);
				    //acutalizando lista de nombres
				    Opciones.mensaje(Nueva_categoria.this, null, "", "Categoria agregada", "");
				    
				}else{
					Opciones.mensaje(Nueva_categoria.this, null, "", "Se necesita una categoria valida", "");
					rtn = false;
				}
		}else{
			Opciones.mensaje(Nueva_categoria.this, null, "", "Ingrese una categoria", "");
			rtn = false;
		}
		return rtn;
	}


	
}
