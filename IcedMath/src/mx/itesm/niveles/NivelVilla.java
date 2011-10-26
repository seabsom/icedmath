package mx.itesm.niveles;

import mx.itesm.menus.R;
import mx.itesm.personajes.Enemigo;
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
	private boolean derIsPressed;
	private boolean izqIsPressed;
	private int xd, xp, offset;

	public NivelVilla(Context contexto) {
		super(contexto);
		paint = new Paint();
		fondo = new Fondo(contexto);
		xp = 0;
		oleg = new Protagonista(contexto, xd, 215);
		enemigo = new Enemigo(contexto, 500, 200);
		derecha = BitmapFactory.decodeResource(getResources(),
				R.drawable.derecha);
		izquierda = BitmapFactory.decodeResource(getResources(),
				R.drawable.izquierda);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		calcularXdOffset();
		canvas.drawBitmap(fondo.getGrafico(), -offset, 0, paint);
		fondo.dibujar(canvas, paint);
		oleg.dibujar(canvas, paint);
		enemigo.dibujar(canvas, paint);
		canvas.drawBitmap(izquierda, 0,
				(canvas.getHeight() - izquierda.getHeight()), paint);
		canvas.drawBitmap(derecha, canvas.getWidth() - derecha.getWidth(),
				(canvas.getHeight() - derecha.getHeight()), paint);

	}

	public void calcularXdOffset() {
		int ancho = getWidth();
		if (xp < ancho / 2) {
			xd = xp;
			offset = 0;
		} else if (xd > fondo.getWidth() - ancho / 2) {
			xd = xp - (fondo.getWidth() - ancho);
			offset = fondo.getWidth() - ancho;
		} else {
			xd = ancho / 2;
			offset = xp - ancho / 2;
		}

	}

	public void actualizar() {
		enemigo.moverseAdelante();
		if (derIsPressed) {
			oleg.moverseAdelante();
			xp += 20;
		} else if (izqIsPressed) {
		//	oleg.setX(new Posicion(oleg.getPosicion().x, oleg.getPosicion().y)));
			oleg.moverseAtras();
			xp -= 20;
		} else {
			oleg.pararse();
		}

		// Verificar choques PENDIENTE; NO FUNCIONA
//
//		if (enemigo.getPosicion().x >= xd - oleg.getWidth()
//				&& enemigo.getPosicion().x <= xd + oleg.getWidth()) {
//			if (enemigo.getPosicion().y >= oleg.getHeight()
//					&& enemigo.getPosicion().y <= oleg.getHeight()
//							- enemigo.getHeight()) {
//				oleg.setPosicion(new Posicion(xd, 215));
//			}
//		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			if (event.getX() > getWidth() - derecha.getWidth()
					&& event.getY() > getHeight() - derecha.getHeight()) {
				oleg.spriteNormal();
				derIsPressed = true;

			} else if (event.getX() < izquierda.getWidth()
					&& event.getY() > getHeight() - izquierda.getHeight()) {
				izqIsPressed = true;
				oleg.voltear();

			}
			break;
		default:
			derIsPressed = false;
			izqIsPressed = false;
			return false;
		}
		return true;
	}

}
