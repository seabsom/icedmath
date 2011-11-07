package mx.itesm.personajes;

import java.util.LinkedList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta clase se encarga de realizar acciones con todas las imagenes que
 * representan un sprite de un personaje
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 03/10/2011
 * 
 */
public class Sprite {
	private LinkedList<Bitmap> listaImgs;
	private int indice;

	/**
	 * Constructor de la clase Sprite
	 * 
	 * @param admRecursos
	 *            Representa los recursos que pueden ser usados para crear el
	 *            objeto
	 * @param ids
	 *            Representa el arreglo de imagenes a usar
	 */
	public Sprite(Resources admRecursos, int[] ids) {
		listaImgs = new LinkedList<Bitmap>();

		for (int id : ids) {
			Bitmap bm = BitmapFactory.decodeResource(admRecursos, id);
			listaImgs.add(bm);
		}

		indice = 0;
	}

	/**
	 * Método que se encarga de dibujar una imagen del sprite, dependiendo del
	 * índice actual
	 * 
	 * @param canvas Respresenta el canvas en el que será dibujada la imagen
	 * @param x Representa la "x" en la que se dibujará la imagen
	 * @param y Representa la "y" en la que se dibujará la imagen
	 * @param p Representa al Paint qus será utilizado 
	 */
	public void draw(Canvas canvas, float x, float y, Paint p) {
		canvas.drawBitmap(listaImgs.get(indice), x, y, p);
	}

	/**
	 * Método que se encarga de avanzar en el arreglo de imágenes
	 */
	public void nextFrame() {
		indice = (indice + 1) % listaImgs.size();
	}

	/**
	 * Método que se de retroceder en el arreglo de imágenes
	 */
	public void prevFrame() {
		indice = (indice - 1) % listaImgs.size();
	}

	/**
	 * Método que regresa a la primera imagen del arreglo
	 */
	public void firstFrame() {
		indice = 0;
	}

	/**
	 * Método accesor de la altura de las imagenes del arreglo
	 * @return La altura de las imágenes en ele arreglo
	 */
	public float getAlto() {
		return listaImgs.getFirst().getHeight();
	}

	public float getAncho() {
		return listaImgs.getFirst().getWidth();
	}

}
