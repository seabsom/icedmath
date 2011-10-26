package mx.itesm.personajes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Personaje extends View{

	private Posicion posicion;
	private Sprite sprite;
	

	public Personaje(Context contexto, 	Posicion nuevaPosicion) {
		super(contexto);
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
		sprite.draw(canvas, posicion, paint);
	}

	public void moverseAdelante() {		
	}

	public void morir() {
	}

	public void atacar() {
	}
}
