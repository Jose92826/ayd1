package com.example.gestoreconocimo;

public class Categorias_ts {
	
	    String BD[];
	    int cant;
	   public  Categorias_ts(){
	    	 BD = new String[50];
	    	 cant=0;
	     }
	     
public boolean funcion_agregar_nombre(String nuevo_nombre){
		boolean rtn = true;
			
			/*if(!nuevo_nombre.equals("")){
				//si es un nuevo nombre distinto de vacio, se agrega a la base de datos	
				boolean b=nuevo_nombre.matches("[a-zA-Z]+[a-zA-Z\\s0-9]*");
				if(b){*/
				    rtn= BD_insert(nuevo_nombre,"fijo");
				    //acutalizando lista de nombrescione
				      
				/*}else{
					
					rtn = false;
				}
		}else{
			
			rtn = false;
		}*/
		return rtn;
	}
	



  private boolean BD_insert(String nombre,String padre){
	    boolean resul = false;
	      for(int r=0;r==cant;r++){
	    	  if(nombre.equals(BD[r])){
	    		  resul=true;
	    		  
	    	  }
	    	 if(resul){
	    		 resul = !resul; // ya existe la categoria, error en el ingreso
	    		 
	    	 }else{
	    	   resul= !resul; // no existe la categoria , ingreso exitoso
	    	  BD[cant]= nombre;
	    	  cant++;
	    	 }
	    	 
	    	  
	      }           
	    
	  return resul;
  }
}

