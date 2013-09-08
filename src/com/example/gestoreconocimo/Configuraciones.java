package com.example.gestoreconocimo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class Configuraciones extends Activity{
	//declaracion publica de botones
		
		private Button confi_usua;
	 
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_configuraciones);
	        confi_usua = (Button) findViewById(R.id.btn_confi_usuario);
	        eventos();
	}
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.gestor, menu);
	        return true;
	    }
	 
	    
	    //declaracion de eventos
	   public void eventos(){
		   confi_usua.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					//carga activity de gastos diarios
					Intent i = new Intent( Configuraciones.this, Configura_usuario.class );
					//posible opcion de envio de parametros
					//i.putExtra("parametro", "valor");
					Configuraciones.this.startActivityForResult(i,0);
				}
	        });
	    }
}
