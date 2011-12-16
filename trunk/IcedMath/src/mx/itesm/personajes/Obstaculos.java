package mx.itesm.personajes;

import mx.itesm.menus.R;
import android.content.Context;
/**
 * Esta clase define todas las características de cualquier obstaculo del juego
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
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