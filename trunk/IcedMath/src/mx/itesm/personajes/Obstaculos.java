package mx.itesm.personajes;

import mx.itesm.menus.R;
import android.content.Context;
/**
 * Esta clase define todas las caracter�sticas de cualquier obstaculo del juego
 * 
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 02/11/2011
 * 
 */
public class Obstaculos extends Personaje {

	public Obstaculos(Context contexto, float x, float y) {
		super(contexto, x, y);
		int[] ids = {R.drawable.picoscortos				
		};
		super.setSprite(new Sprite(getResources(), ids));
		
	}
	
	@Override
	public void moverse() {
		setX(getX()-4);		
	}

}