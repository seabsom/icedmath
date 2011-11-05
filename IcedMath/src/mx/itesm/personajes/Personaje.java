package mx.itesm.personajes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Personaje extends View {

	private Sprite sprite;
	private float x;
	private float y;

	public Personaje(Context contexto, float x, float y) {
		super(contexto);
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void dibujar(Canvas canvas, Paint paint) {
		sprite.draw(canvas, x, y, paint);
	}

	public void moverseAdelante() {
	}

	public void morir() {
	}

	public void atacar() {
	}
}
