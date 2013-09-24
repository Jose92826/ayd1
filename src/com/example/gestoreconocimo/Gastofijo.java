package com.example.gestoreconocimo;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Gastofijo extends Activity {
	//declaracion publica de botones
	public static Button gfbtn_aceptar;
	public static Button gfbtn_categoria;
	//declaracion publica de txt
	public static EditText gftxt_costo;
	public static EditText gftxt_descripcion;
	public static EditText gftxt_vence;
	public static EditText gftxt_periodo;
	public static EditText gftxt_categoria;
	public static EditText gftxt_subcategoria;
	//generales
	public static int INT_GASTOS_FIJO=0;
	public static boolean exito = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gastofijo);
		//asignacion de botones de activity a variables boton
		gfbtn_aceptar = (Button) findViewById(R.id.gfbtn_aceptar);
		gfbtn_categoria = (Button) findViewById(R.id.gfbtn_categoria);
		//asignacion de txt de activity a variables txt
		gftxt_costo = (EditText) findViewById(R.id.gftxt_costo);
		gftxt_descripcion = (EditText) findViewById(R.id.gftxt_descripcion);
		gftxt_vence = (EditText) findViewById(R.id.gftxt_vence);
		gftxt_periodo = (EditText) findViewById(R.id.gftxt_periodo);
		gftxt_categoria = (EditText) findViewById(R.id.gftxt_categoria);
		gftxt_subcategoria = (EditText) findViewById(R.id.gftxt_subcategoria);
		//llamada a asignacion de eventos
		eventos();
		inicializacion();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gastofijo, menu);
		return true;
	}
	
	public void eventos(){
		//para enviar el activity a travez del evento de Onclick
    	final Activity atv=this;
    	//boton aceptar
    	gfbtn_aceptar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//agregando nombre
				aceptar(atv);
				//saliendo de la aplicacion
				if ( exito ){
					//cerrando la ventana
					Intent i = new Intent( Gastofijo.this, Gestor.class );
					atv.setResult( Activity.RESULT_OK, i );
					Gastofijo.this.finish();
				}
			}
        });
    	//boton seleccion de categoria
    	gfbtn_categoria.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//carga activity de gastos diarios
				Intent i = new Intent( Gastofijo.this,Categoria.class );
				//posible opcion de envio de parametros
				//i.putExtra("parametro", "valor");
				Gastofijo.this.startActivityForResult(i, INT_GASTOS_FIJO);
			}
        });
    	//onfocus txt
    	gftxt_vence.setOnFocusChangeListener(new OnFocusChangeListener() {
    		@Override
    		public void onFocusChange(View v, boolean hasFocus) {
    			String vence=gftxt_vence.getText().toString();
    		    if(hasFocus){
    		        //get focus
    		    	if(vence.equals("dd/mm/yyyy")){
    		    		gftxt_vence.setText("");
    		    	}
    		    }else {
    		        //lost the focus
    		    	if(vence.equals("")){
    		    		gftxt_vence.setText("dd/mm/yyyy");
    		    	}
    		    }
    		}
    	});
    	//onfocus txt
    	gftxt_periodo.setOnFocusChangeListener(new OnFocusChangeListener() {
    		@Override
    		public void onFocusChange(View v, boolean hasFocus) {
    			String vence=gftxt_periodo.getText().toString();
    		    if(hasFocus){
    		        //get focus
    		    	if(vence.equals("Dia, Semana, Mes")){
    		    		gftxt_periodo.setText("");
    		    	}
    		    }else {
    		        //lost the focus
    		    	if(vence.equals("")){
    		    		gftxt_periodo.setText("Dia, Semana, Mes");
    		    	}
    		    }
    		}
    	});
	}
	
	public void inicializacion(){
		if(data.categoria_padre.equals("fijo")){
			gftxt_categoria.setText(data.categoria);
			gftxt_subcategoria.setText("");
		}else{
			gftxt_categoria.setText(data.categoria_padre);
			gftxt_subcategoria.setText(data.categoria);
		}
		gftxt_categoria.setEnabled(false);
		gftxt_subcategoria.setEnabled(false);
	}
	
	//al terminar de seleccionar categoria
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Idata) {
         if ( requestCode == INT_GASTOS_FIJO ){
              if ( resultCode == Activity.RESULT_OK ){
            	  //exito
            	  inicializacion();
            	  Toast.makeText(getApplicationContext(), "Seleccion exitosa", Toast.LENGTH_LONG).show();
              }else if(resultCode==Activity.RESULT_CANCELED){
            	  //cancelo
            	  Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
              }else if(resultCode==Activity.RESULT_FIRST_USER){
            	  //error
            	  Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
              }
             //actualiza la informacion de presupuesto
         }
    }
	
	public static void aceptar(final Activity atv){
		//obitene resultados
		String costo=gftxt_costo.getText().toString();
		String descripcion=gftxt_descripcion.getText().toString();
		String vence=gftxt_vence.getText().toString();
		String periodo=gftxt_periodo.getText().toString();
		String categoria=gftxt_categoria.getText().toString();
		String subcategoria=gftxt_subcategoria.getText().toString();
		//expresiones regulares
		String error="";
		boolean a=costo.matches("[0-9]+(.[0-9]+)?");
		if(!a){
			error="Costo incorrecto\n";
		}
		//descripcion
		boolean b=vence.matches("[0-9][0-9]?/[0-9][0-9]?/[0-9][0-9][0-9][0-9]");
		if(!b){
			error+="Fecha incorrecta\n";
		}
		boolean c=periodo.matches("([dD][iI][aA])|([sS][eE][mM][aA][nN][aA])|([mM][eE][sS])");
		if(!c){
			error+="Periodo incorrecto\n";
		}
		boolean d=!data.categoria.equals("") & !data.categoria_padre.equals("");
		if(!d){
			error+="Seleccione categoria\n";
		}
		if(a & b & c & d){
			//insertando en la base de datos
			ContentValues parametros = new ContentValues();
			parametros.put("costo",costo);
			parametros.put("descripcion",descripcion);
			parametros.put("vence",vence);
			parametros.put("periodo",periodo);
			if(data.categoria_padre.equals("fijo")){
				parametros.put("nombre_padre","");
				parametros.put("nombre",categoria);
			}else{
				parametros.put("nombre_padre",categoria);
				parametros.put("nombre",subcategoria);
			}
			boolean resultado=Opciones.insert("gasto",parametros);
			exito = true;
			Toast.makeText(atv.getApplicationContext(), "Ingreso exitoso", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(atv.getApplicationContext(), error, Toast.LENGTH_LONG).show();
			exito = false;
		}
	}
	

}
