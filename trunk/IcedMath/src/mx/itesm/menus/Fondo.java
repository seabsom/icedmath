package mx.itesm.menus;

import mx.itesm.personajes.Posicion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Fondo extends View {

	private Bitmap grafico;
	private Posicion posicion;

	public Fondo(Context contexto) {
		super(contexto);
		grafico = BitmapFactory.decodeResource(getResources(),
				R.drawable.montanas); // Se deja el fondo fijo
		posicion = new Posicion();
	}

	public void dibujar(Canvas canvas, Paint paint) {
		canvas.drawBitmap(grafico, posicion.x, posicion.y, paint);
	}

}
