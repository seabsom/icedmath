package mx.itesm.personajes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import mx.itesm.menus.Sprite;

public class Personaje extends View {

	private Posicion posicion;
	private Sprite sprite;
	

	public Personaje(Context contexto, Sprite nuevoSprite,
			Posicion nuevaPosicion) {
		super(contexto);
		sprite = nuevoSprite;
		posicion = nuevaPosicion;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void dibujar(Canvas canvas, Paint paint) {
		sprite.draw(canvas, paint, posicion);
	}

	public void moverse() {		
	}

	public void morir() {
	}

	public void atacar() {
	}
}
