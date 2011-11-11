package mx.itesm.personajes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Esta clase define todas las características de cualquier personaje del juego
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 02/11/2011
 * 
 */
public class Personaje extends View {

	private Sprite sprite;
	private float x;
	private float y;

	/**
	 * Contructor de la clase Personaje
	 * 
	 * @param contexto
	 *            Indica en que contexto será usado por Personaje
	 * @param x
	 *            Indica la x que tendrá Personaje
	 * @param y
	 *            Indica la y que tendrá Personaje
	 */
	public Personaje(Context contexto, float x, float y) {
		super(contexto);
		this.x = x;
		this.y = y;
	}

	/**
	 * Método accesor de la posición en "x"personaje
	 * 
	 * @return Se regresa la posicion del personaje en el eje "x"
	 */
	public float getX() {
		return x;
	}

	/**
	 * Método accesor de la posición en "y"personaje
	 * 
	 * @return Se regresa la posicion del personaje en el eje "y"
	 */
	public float getY() {
		return y;
	}

	/**
	 * Método que permite asignar una posición en el eje "x" al personaje
	 * 
	 * @param x
	 *            Representa la nueva posición en "x" del personaje
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Método que permite asignar una posición en el eje "y" al personaje
	 * 
	 * @param y
	 *            Representa la nueva posición en "y" del personaje
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Método accesor del sprite del personaje
	 * 
	 * @return Se regresa un objeto de tipo Sprite que pertenece al personaje
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Método que permite asignar un sprite al personaje
	 * 
	 * @param sprite
	 *            Representa al nuevo objeto tipo Sprite que se le asignará al
	 *            personaje
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * Método que sirve para dibujar al personaje en un canvas
	 * 
	 * @param canvas
	 *            Representa al canvas en el cual se dibujará el personaje
	 * @param paint
	 *            Representa el paint que usará el personaje
	 */
	public void dibujar(Canvas canvas, Paint paint) {
		sprite.draw(canvas, x, y, paint);
	}

	/**
	 * Método que se encarga del movimiento del personaje
	 */
	public void moverse() {
	}

	/**
	 * Método que se encarga de realizar una acción cuando el personaje muere
	 */
	public void morir() {
	}

	/**
	 * Método que se encarga de realizar una acción al indicarle al personaje
	 * que ataque
	 */
	public void atacar() {
	}

	/**
	 * Método accesor de la altura de los sprites del personaje
	 * 
	 * @return La altura del personaje
	 */
	public float getAlto() {
		return sprite.getAlto();
	}

	/**
	 * Método accesor de el ancho de los sprites del personaje
	 * 
	 * @return El ancho del personaje
	 */
	public float getAncho() {
		return sprite.getAncho();
	}

}
