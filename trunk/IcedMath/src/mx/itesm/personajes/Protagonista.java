package mx.itesm.personajes;

import android.content.Context;

import mx.itesm.menus.R;

/**
 * Esta clase representa a un protagonista con todos sus atributos. Hereda de la clase "Personaje"
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 06/11/2011
 *
 */
public class Protagonista extends Personaje {

	/**
	 * Contructor de la clase Protagonista
	 * @param contexto Indica en que contexto será usado Protagonista
	 * @param x Indica la x que tendrá Protagonista
	 * @param y Indica la y que tendrá Protagonista
	 */
	public Protagonista(Context contexto, float x, float y) {
		super(contexto, x, y);
		voltearDer();
	}

	/**
	 * Método que indica a Protagonista que debe voltear a la derecha
	 */
	public void voltearDer() {
		int[] ids = { R.drawable.normalder, R.drawable.caminaunoder,
				R.drawable.caminadosder, R.drawable.caminatresder,
				R.drawable.caminacuatroder, R.drawable.caminacincoder };
		super.setSprite(new Sprite(getResources(), ids));
	}

	/**
	 *Método que le dice a Protagonista que debe voltear a la izquierda 
	 */
	public void voltearIzq() {
		int[] ids = { R.drawable.normalizq, R.drawable.caminauno,
				R.drawable.caminados, R.drawable.caminatres,
				R.drawable.caminacuatro, R.drawable.caminacinco };
		super.setSprite(new Sprite(getResources(), ids));
		setX(getX() + 4);

	}

	/**
	 * Método que le dice a Protagonista que hacer al indicarle que debe saltar
	 */
	public void saltar() {
		int[] ids = { R.drawable.saltarunoder};
		super.setSprite(new Sprite(getResources(),
				ids));
	}

	/**
	 * Método que describe la caida de Protagonista después de un salto
	 */
	public void caer() {

		int[] ids = { R.drawable.saltardosder};
		super.setSprite(new Sprite(getResources(),
				ids));
	}

	/**
	 * Método que dice a Protagonista que hacer al tomar un item
	 */
	public void tomarItem() {

	}

	@Override
	public void moverse() {
		super.getSprite().nextFrame();
	}

	/**
	 * Método que le indica a protagonista que debe quedarse quieto
	 */
	public void pararse() {
		super.getSprite().firstFrame();
	}

	/**
	 * Método que le dice al personaje que debe quedarse quieto mirando a la izquierda
	 */
	public void pararseIzq() {
		int[] ids = { R.drawable.normalizq };
		super.setSprite(new Sprite(getResources(), ids));
	}

	/**
	 * Método que le dice al personaje que debe quedarse quieto mirando a la derecha
	 */
	public void pararseDer() {
		int[] ids = { R.drawable.normalder };
		super.setSprite(new Sprite(getResources(), ids));
	}

}
