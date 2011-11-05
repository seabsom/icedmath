package mx.itesm.niveles;


import mx.itesm.menus.R;
import mx.itesm.personajes.Enemigo;
import mx.itesm.personajes.Protagonista;
import mx.itesm.personajes.Sprite;
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
	private float posOle;
	private boolean viendoDerecha;
	private boolean isJump = false;


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
			viendoDerecha=true;
			oleg.moverseAdelante();
			xp += 10;
			if (oleg.getX() >= 790 || oleg.getX() <= 0) {
				derIsPressed = false;
			}
		} else if (izqIsPressed) {
			viendoDerecha=false;
			oleg.moverseAtras();
			xp -= 10;
			if (oleg.getX() <= -790 || oleg.getX() <= 0) {
				izqIsPressed = false;					
			}
		} else {
			oleg.pararse();
		}

		if (enemigo.getX() >= xd - oleg.getX()
				&& enemigo.getX() <= xd + oleg.getWidth()) {
			oleg.setX(-400);
			if (enemigo.getY() >= oleg.getY()
					&& enemigo.getY() <= oleg.getY() - enemigo.getHeight()) {
				oleg.setY(-400);
			}
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			
			if ((event.getX() > izquierda.getWidth() && event.getX() < 2*izquierda.getWidth())
					&& event.getY() > (getHeight() - derecha.getHeight())) {
				oleg.voltearDer();
				derIsPressed = true;
				
			}else if (event.getX() < izquierda.getWidth()
					&& event.getY() > (getHeight() - izquierda.getHeight())) {
				oleg.voltearIzq();
				izqIsPressed = true;

			}
			
			if(event.getX()>getWidth()-btnsaltar.getWidth()
					&&event.getY()>getHeight()-btnsaltar.getHeight()){
				if(isJump==false){
					isJump=true;
					posOle=oleg.getY();
					Thread thr = new Thread(new Runnable() {

						public void run() {
			            	if(viendoDerecha==true){
			            		int[] ids ={R.drawable.saltarunoder,R.drawable.saltardosder};
			            		oleg.setSprite(new Sprite(getResources(), ids));
			            		while (oleg.getY()>posOle-200) {
			            			oleg.saltar();
			            		}
			            		oleg.getSprite().nextFrame();
			            		while (oleg.getY()<posOle) {
			        				oleg.caer();
			        			}
			            		oleg.pararseDer();
			            		isJump=false;

			               	}
			            	else if(viendoDerecha==false){
			            		int[] ids ={R.drawable.saltaruno, R.drawable.saltardos};
			            		oleg.setSprite(new Sprite(getResources(), ids));
			            		while (oleg.getY()>posOle-200) {
			            				oleg.saltar();
			            		}
			            		oleg.getSprite().nextFrame();
			            		while (oleg.getY()<posOle) {
		        					oleg.caer();
		        				}
			            		oleg.pararseIzq();
			            		isJump=false;
			            		        				
			            	}
			                
			            }
			        });
					thr.start();
			      }
		}
			break;
			
		default:
			derIsPressed = false;
			izqIsPressed = false;
			oleg.pararse();
			return false;
			
		}
		return true;
	
		
	}
}
	