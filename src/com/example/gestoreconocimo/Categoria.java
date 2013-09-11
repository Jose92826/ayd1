package com.example.gestoreconocimo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Categoria extends Activity {
	//lista de nombres para gasto
	public static Spinner spr_nombre;
	public static Spinner spr_subnombre;
	//declaracion publica de botones
	public static Button btn_agregar_nombre;
	public static Button btn_agregar_subnombre;
	public static Button btn_aceptar;
	//declaracion publica de txt
	public static EditText txt_agregar_nombre;
	public static EditText txt_agregar_subnombre;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria);
		//asignacion de botones de activity a variables boton
		btn_agregar_nombre = (Button) findViewById(R.id.btn_agregar_nombre_cat);
		btn_agregar_subnombre = (Button) findViewById(R.id.btn_agregar_subnombre_cat);
		btn_aceptar = (Button) findViewById(R.id.btn_aceptar_cat);
		//asignacion de txt de activity a variables txt
		txt_agregar_nombre = (EditText) findViewById(R.id.txt_agregar_nombre_cat);
		txt_agregar_subnombre = (EditText) findViewById(R.id.txt_agregar_subnombre_cat);
		//cargando ID lista nombres a spinner de lista de nombres
		spr_nombre = (Spinner) findViewById(R.id.spr_monedas_cat);
		spr_subnombre = (Spinner) findViewById(R.id.spr_submonedas_cat);
		//llamada a asignacion de eventos
		eventos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria, menu);
		return true;
	}
	
	public void eventos(){
		//para enviar el activity a travez del evento de Onclick
    	final Activity atv=this;
    	btn_agregar_nombre.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//agregando nombre
				agregar_nombre();
			}
        });
    	spr_nombre.setOnItemSelectedListener(new OnItemSelectedListener() {
    	    @Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
    	    	set_adapter_subnombre(Categoria.this, "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

    	});
    	btn_agregar_subnombre.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//agregando nombre
				agregar_subnombre();
			}
        });
    	//llenando lista de nombres de costos
    	set_adapter_nombre(this,"");
    	//boton aceptar
    	btn_aceptar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				seleccionar();
			}
        });
	}
	
	public void seleccionar(){
		String nombre = spr_nombre.getSelectedItem().toString();
		String subnombre ="";
		try{
			subnombre=spr_subnombre.getSelectedItem().toString();
		}catch(Exception e){}
		if(subnombre.equals("")){
			data.categoria=nombre;
			data.categoria_padre="fijo";
			//Opciones.mensaje(Categoria.this, null, "", "Categoria: "+nombre, "");
		}else{
			data.categoria=subnombre;
			data.categoria_padre=nombre;
			//Opciones.mensaje(Categoria.this, null, "", "Categoria="+subnombre+" ;padre="+nombre+";", "");
		}
		Intent i = new Intent( Categoria.this, Opciones.class );
        setResult( Activity.RESULT_OK, i );
        Categoria.this.finish();
	}
	
	
	
	
	
	
	
	
	public void agregar_nombre(){
    	String nuevo_nombre=txt_agregar_nombre.getText().toString();
    	funcion_agregar_nombre(nuevo_nombre);
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
				    Opciones.mensaje(Categoria.this, null, "", "Categoria agregada", "");
				    if(resultado)
				    	set_adapter_nombre(atv,nuevo_nombre);
				}else{
					Opciones.mensaje(Categoria.this, null, "", "Se necesita una categoria valida", "");
					rtn = false;
				}
		}else{
			Opciones.mensaje(Categoria.this, null, "", "Ingrese una categoria", "");
			rtn = false;
		}
		return rtn;
	}
	
	
	
	
	
	
	
	
	
	
	public void agregar_subnombre(){
    	String nuevo_nombre=txt_agregar_subnombre.getText().toString();
    	String padre = spr_nombre.getSelectedItem().toString();
    	funcion_agregar_subnombre(nuevo_nombre, padre);
    }
	
	public boolean funcion_agregar_subnombre(String nuevo_nombre, String padre){
		
		boolean rtn = true;
	    	final Activity atv=this;
	    	if(!nuevo_nombre.equals("") && !padre.equals("")){
				//si es un nuevo nombre distinto de vacio, se agrega a la base de datos
	    		boolean b=nuevo_nombre.matches("[a-zA-Z]+[a-zA-Z\\s0-9]*");
	    		if(b){
		    		
					ContentValues parametros = new ContentValues();   
				    parametros.put("nombre",nuevo_nombre);
				    parametros.put("padre", padre);
				    boolean resultado=Opciones.insert("nombre",parametros);
				    //acutalizando lista de nombres
				    Opciones.mensaje(Categoria.this, null, "", "Subategoria agregada", "");
				    if(resultado)
				    	set_adapter_subnombre(atv,nuevo_nombre);
	    		}else{
	    			Opciones.mensaje(Categoria.this, null, "", "Se necesita una subcategoria valida", "");
	    			rtn = false;
	    		}
			}else if(padre.equals("")){
				Opciones.mensaje(Categoria.this, null, "", "Seleccione categoria padre", "");
			}else{
				Opciones.mensaje(Categoria.this, null, "", "Ingrese una subcategoria", "");
			}
		return rtn;
    }
	
	//etiquetas creadas por el usuario
    public void set_adapter_subnombre(Activity atv, String seleccion){
    	boolean vacio=false;
    	String padre = spr_nombre.getSelectedItem().toString();
    	//lista de nombres de gastos
    	String[] nombres=Opciones.getNombreCostos("SELECT nombre FROM nombre WHERE padre = '"+padre+"'");
    	//item seleccionado
    	int select=0;
    	try{
	        if(nombres==null)
	        	vacio=true;
	        else{
	        	for(int j=0; j<nombres.length; j++){
	        		if(nombres[j].equals(seleccion))
	        			select=j+1;
	        	}
	        }
	        String[] sub=new String[nombres.length+1];
	        sub[0]="";
	        for(int j=1;j<nombres.length+1;j++){
	        	sub[j]=nombres[j-1];
	        }
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(atv, android.R.layout.simple_list_item_1, sub);
	        spr_subnombre.setAdapter(adapter);
	        if(vacio){
	        	//Lista de nombres de gastos vacia
	        	spr_subnombre.setVisibility(View.GONE);
	        	Opciones.mensaje(Categoria.this, null, "", "Sin sub categorias", "");
	        	//mensaje
	        }else{
	        	//Lista de nombres con parametros
	        	spr_subnombre.setVisibility(View.VISIBLE);
	        }
	        spr_subnombre.setSelection(select, false);
    	}catch(Exception e){
    		spr_subnombre.setVisibility(View.GONE);
        	//Opciones.mensaje(Categoria.this, null, "", "Sin sub categorias", "");
    	}
    }
	
	//etiquetas creadas por el usuario
    public  void set_adapter_nombre(Activity atv, String seleccion){
    	boolean vacio=false;
    	//lista de nombres de gastos
    	String[] nombres=Opciones.getNombreCostos("SELECT nombre FROM nombre WHERE padre = 'fijo'");
    	//item seleccionado
    	int select=0;
    	
        if(nombres==null)
        	vacio=true;
        else{
        	for(int j=0; j<nombres.length; j++){
        		if(nombres[j].equals(seleccion))
        			select=j;
        	}
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(atv, android.R.layout.simple_list_item_1, nombres);
        spr_nombre.setAdapter(adapter);
        if(vacio){
        	//Lista de nombres de gastos vacia
        	spr_nombre.setVisibility(View.GONE);
        	Opciones.mensaje(Categoria.this, null, "", "Base de datos vacia", "");
        	//mensaje
        }else{
        	//Lista de nombres con parametros
        	spr_nombre.setVisibility(View.VISIBLE);
        }
        spr_nombre.setSelection(select, false);
    }

}
