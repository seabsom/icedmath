package mx.itesm.personajes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Esta clase define todas las caracter�sticas de cualquier personaje del juego
 * 
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
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
	 *            Indica en que contexto ser� usado por Personaje
	 * @param x
	 *            Indica la x que tendr� Personaje
	 * @param y
	 *            Indica la y que tendr� Personaje
	 */
	public Personaje(Context contexto, float x, float y) {
		super(contexto);
		this.x = x;
		this.y = y;
	}

	/**
	 * M�todo accesor de la posici�n en "x"personaje
	 * 
	 * @return Se regresa la posicion del personaje en el eje "x"
	 */
	public float getX() {
		return x;
	}

	/**
	 * M�todo accesor de la posici�n en "y"personaje
	 * 
	 * @return Se regresa la posicion del personaje en el eje "y"
	 */
	public float getY() {
		return y;
	}

	/**
	 * M�todo que permite asignar una posici�n en el eje "x" al personaje
	 * 
	 * @param x
	 *            Representa la nueva posici�n en "x" del personaje
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * M�todo que permite asignar una posici�n en el eje "y" al personaje
	 * 
	 * @param y
	 *            Representa la nueva posici�n en "y" del personaje
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * M�todo accesor del sprite del personaje
	 * 
	 * @return Se regresa un objeto de tipo Sprite que pertenece al personaje
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * M�todo que permite asignar un sprite al personaje
	 * 
	 * @param sprite
	 *            Representa al nuevo objeto tipo Sprite que se le asignar� al
	 *            personaje
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * M�todo que sirve para dibujar al personaje en un canvas
	 * 
	 * @param canvas
	 *            Representa al canvas en el cual se dibujar� el personaje
	 * @param paint
	 *            Representa el paint que usar� el personaje
	 */
	public void dibujar(Canvas canvas, Paint paint) {
		sprite.draw(canvas, x, y, paint);
	}

	/**
	 * M�todo que se encarga del movimiento del personaje
	 */
	public void moverse() {
	}

	/**
	 * M�todo que se encarga de realizar una acci�n cuando el personaje muere
	 */
	public void morir() {
	}

	/**
	 * M�todo que se encarga de realizar una acci�n al indicarle al personaje
	 * que ataque
	 */
	public void atacar() {
	}

	/**
	 * M�todo accesor de la altura de los sprites del personaje
	 * 
	 * @return La altura del personaje
	 */
	public float getAlto() {
		return sprite.getAlto();
	}

	/**
	 * M�todo accesor de el ancho de los sprites del personaje
	 * 
	 * @return El ancho del personaje
	 */
	public float getAncho() {
		return sprite.getAncho();
	}

}
