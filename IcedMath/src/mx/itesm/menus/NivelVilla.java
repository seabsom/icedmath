package mx.itesm.menus;

import mx.itesm.personajes.Enemigo;
import mx.itesm.personajes.Posicion;
import mx.itesm.personajes.Protagonista;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class NivelVilla extends View {

	private Paint paint;
	private Fondo fondo;
	private Enemigo enemigo;
	private Protagonista oleg;
	private Bitmap derecha;
	private Bitmap izquierda;

	public NivelVilla(Context contexto) {
		super(contexto);
		paint = new Paint();

		fondo = new Fondo(contexto);
		oleg = new Protagonista(contexto, new Posicion(50, 215));
		enemigo = new Enemigo(contexto, new Posicion(300, 200));
		derecha = BitmapFactory.decodeResource(getResources(),
				R.drawable.derecha);
		izquierda = BitmapFactory.decodeResource(getResources(),
				R.drawable.izquierda);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		fondo.dibujar(canvas, paint);
		oleg.dibujar(canvas, paint);
		enemigo.dibujar(canvas, paint);
		canvas.drawBitmap(derecha, canvas.getWidth() - derecha.getWidth(),
				(canvas.getHeight() - derecha.getHeight()), paint);
		canvas.drawBitmap(izquierda, 0,
				(canvas.getHeight() - izquierda.getHeight()), paint);
	}

	public void actualizar() {
		enemigo.moverse();
	}

	// Este método se hizo para hacer pruebas con los sprites y para asegurar el
	// funcionamiento de las clases Enemigo y Protagonista

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			oleg.moverse();
			break;
		default:
			return false;
		}
		return true;
	}

}
