package Sprint2;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;


import com.example.gestoreconocimo.Categoria;


@RunWith(ConcordionRunner.class)
/**
 * Clase encargada de realizar las pruebas de aceptacion 
 * del Sprint No. 2
 * @author CINOTER
 *
 */
public class SpecTest {

//comentario
		
	public boolean getValidacionCategoriaNueva(String categoria)
	{
	
		Categoria cat = new Categoria();
		
		return	cat.funcion_agregar_nombre(categoria);
		
	}
	
	public boolean getPrueba()
	{
		return true;
	}
}
