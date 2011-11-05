package mx.itesm.niveles;

import mx.itesm.menus.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Fondo extends View {

	private Bitmap grafico;
	private float x;
	private float y;

	public Fondo(Context contexto, Bitmap fondo) {
		super(contexto);
		grafico = BitmapFactory.decodeResource(getResources(),
				R.drawable.montanas); // ASê Se deja el fondo fijo, pero se debe
										// recibir "fondo" y asignarlo a
										// "grafico"
		this.x = 0;
		this.y = 0;
	}

	public Bitmap getGrafico() {
		return grafico;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void dibujar(Canvas canvas, Paint paint) {
		canvas.drawBitmap(grafico, x, y, paint);
	}

}
