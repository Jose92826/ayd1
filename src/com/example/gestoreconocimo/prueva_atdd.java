package com.example.gestoreconocimo;

import android.app.Activity;
import android.content.ContentValues;

public class prueva_atdd {
	
	
prueva_atdd(){}

public boolean funcion_agregar_nombre(String nombre,String padre){

	boolean rtn = true;

	if(!nombre.equals("")){
		//si es un nuevo nombre distinto de vacio, se agrega a la base de datos
		
		boolean b=nombre.matches("[a-zA-Z]+[a-zA-Z\\s0-9]*");
		if(b){
			ContentValues parametros = new ContentValues();   
		    parametros.put("nombre",nombre);
		    parametros.put("padre","fijo");
		    boolean resultado=Opciones.insert("nombre",parametros);
		    //acutalizando lista de nombres
		
		}else{
		
			rtn = false;
		}
}else{
	
	rtn = false;
}
return rtn;
	
	
}

public boolean funcion_agregar_subnombre(String nuevo_nombre, String padre){
	
	boolean rtn = true;
    	if(!nuevo_nombre.equals("") && !padre.equals("")){
			//si es un nuevo nombre distinto de vacio, se agrega a la base de datos
    		boolean b=nuevo_nombre.matches("[a-zA-Z]+[a-zA-Z\\s0-9]*");
    		if(b){
	    		
				ContentValues parametros = new ContentValues();   
			    parametros.put("nombre",nuevo_nombre);
			    parametros.put("padre", padre);
			    boolean resultado=Opciones.insert("nombre",parametros);
			    //acutalizando lista de nombres
			   
    		}else{
    			
    			rtn = false;
    		}
		}else if(padre.equals("")){
			
			rtn = false;
		}else{
			
			rtn = false;
		}
	return rtn;
}
	
}
