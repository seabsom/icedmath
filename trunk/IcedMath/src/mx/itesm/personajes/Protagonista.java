package mx.itesm.personajes;

import android.content.Context;

import mx.itesm.menus.R;

/**
 * Esta clase representa a un protagonista con todos sus atributos. Hereda de la clase "Personaje"
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 06/11/2011
 *
 */
public class Protagonista extends Personaje {

	/**
	 * Contructor de la clase Protagonista
	 * @param contexto Indica en que contexto ser� usado Protagonista
	 * @param x Indica la x que tendr� Protagonista
	 * @param y Indica la y que tendr� Protagonista
	 */
	public Protagonista(Context contexto, float x, float y) {
		super(contexto, x, y);
		voltearDer();
	}

	/**
	 * M�todo que indica a Protagonista que debe voltear a la derecha
	 */
	public void voltearDer() {
		int[] ids = { R.drawable.normalder, R.drawable.caminaunoder,
				R.drawable.caminadosder, R.drawable.caminatresder,
				R.drawable.caminacuatroder, R.drawable.caminacincoder };
		super.setSprite(new Sprite(getResources(), ids));
	}

	/**
	 *M�todo que le dice a Protagonista que debe voltear a la izquierda 
	 */
	public void voltearIzq() {
		int[] ids = { R.drawable.normalizq, R.drawable.caminauno,
				R.drawable.caminados, R.drawable.caminatres,
				R.drawable.caminacuatro, R.drawable.caminacinco };
		super.setSprite(new Sprite(getResources(), ids));
		setX(getX() + 4);

	}

	/**
	 * M�todo que le dice a Protagonista que hacer al indicarle que debe saltar
	 */
	public void saltar() {

		try {
			Thread.currentThread();
			Thread.sleep(1);
			setY(getY() - 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * M�todo que describe la caida de Protagonista despu�s de un salto
	 */
	public void caer() {

		try {
			Thread.currentThread();
			Thread.sleep(3);
			setY(getY() + 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que dice a Protagonista que hacer al tomar un item
	 */
	public void tomarItem() {

	}

	@Override
	public void moverse() {
		getSprite().nextFrame();

	}

	/**
	 * M�todo que le indica a protagonista que debe quedarse quieto
	 */
	public void pararse() {
		getSprite().firstFrame();
	}

	/**
	 * M�todo que le dice al personaje que debe quedarse quieto mirando a la izquierda
	 */
	public void pararseIzq() {
		int[] ids = { R.drawable.normalizq };
		super.setSprite(new Sprite(getResources(), ids));
	}

	/**
	 * M�todo que le dice al personaje que debe quedarse quieto mirando a la derecha
	 */
	public void pararseDer() {
		int[] ids = { R.drawable.normalder };
		super.setSprite(new Sprite(getResources(), ids));
	}

}
