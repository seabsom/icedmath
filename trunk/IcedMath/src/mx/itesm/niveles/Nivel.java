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

public class Nivel extends View {

	private Paint paint;
	private Bitmap fondo;
	private Enemigo enemigo;
	private Protagonista oleg;
	private Bitmap derecha;
	private Bitmap izquierda;
	private boolean derIsPressed;
	private boolean izqIsPressed;
	private int xd, xp, offset;
	private Bitmap btnsaltar;
	private Bitmap btnatacar;

	public Nivel(Context contexto) {
		super(contexto);
		paint = new Paint();
		fondo = BitmapFactory.decodeResource(getResources(),
				R.drawable.montanas);
		xp = 0;
		oleg = new Protagonista(contexto, xd, 215);
		enemigo = new Enemigo(contexto, 500, 200);
		derecha = BitmapFactory.decodeResource(getResources(),
				R.drawable.derecha);
		izquierda = BitmapFactory.decodeResource(getResources(),
				R.drawable.izquierda);
		btnsaltar= BitmapFactory.decodeResource(getResources(),
				R.drawable.saltar);
		btnatacar= BitmapFactory.decodeResource(getResources(),
				R.drawable.atacar);


	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		calcularXd();
		canvas.drawBitmap(fondo, -offset, 0, paint);
		oleg.dibujar(canvas, paint);
		enemigo.dibujar(canvas, paint);
		canvas.drawBitmap(izquierda, 0,
				(canvas.getHeight() - izquierda.getHeight()), paint);
		canvas.drawBitmap(derecha, izquierda.getWidth(),
				(canvas.getHeight() - derecha.getHeight()), paint);
		canvas.drawBitmap(btnsaltar, canvas.getWidth() - btnsaltar.getWidth(),
				(canvas.getHeight() - btnsaltar.getHeight()), paint);
		canvas.drawBitmap(btnatacar, canvas.getWidth() - 2*(btnatacar.getWidth()),
				(canvas.getHeight() - derecha.getHeight()), paint);


	}

	//MODIFICALA!!!
	public void calcularXd() {
		int ancho = getWidth();
		if (xp < ancho / 2) {
			xd = xp;
			offset = 0;
		} else if (xp > fondo.getWidth() - ancho / 2) {
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
			xp += 4;
		} else if (izqIsPressed) {
			oleg.moverseAtras();
			xp -= 4;
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
			
			if ((event.getX() > izquierda.getWidth() && event.getX() < 2*izquierda.getWidth())
					&& event.getY() > (getHeight() - derecha.getHeight())) {
				oleg.spriteNormal();
				derIsPressed = true;
				
			}else if (event.getX() < izquierda.getWidth()
					&& event.getY() > (getHeight() - izquierda.getHeight())) {
				oleg.voltear();
				izqIsPressed = true;

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
