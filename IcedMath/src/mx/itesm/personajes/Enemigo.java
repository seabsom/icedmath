package mx.itesm.personajes;

import mx.itesm.menus.R;
import android.content.Context;

/**
 * Esta clase representa a un enemigo con todos sus atributos. Hereda de la
 * clase "Personaje"
 * 
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 05/11/2011
 * 
 */
public class Enemigo extends Personaje {

	/**
	 * Contructor de la clase Enemigo
	 * 
	 * @param contexto
	 *            Indica en que contexto ser� usado Enemigo
	 * @param x
	 *            Indica la x que tendr� Enemigo
	 * @param y
	 *            Indica la y que tendr� Enemigo
	 */
	public Enemigo(Context contexto, float x, float y) {
		super(contexto, x, y);
		int[] ids = { R.drawable.specteruno, R.drawable.specterdos,
				R.drawable.spectertres, R.drawable.spectercuatro,
				R.drawable.spectercinco, R.drawable.specterseis,
				R.drawable.spectersiete };
		super.setSprite(new Sprite(getResources(), ids));
	}

	@Override
	public void moverse() {
		getSprite().nextFrame();
	}
}
