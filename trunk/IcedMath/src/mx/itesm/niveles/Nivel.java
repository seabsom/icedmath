package mx.itesm.niveles;

import mx.itesm.menus.R;
import mx.itesm.personajes.Enemigo;
import mx.itesm.personajes.Protagonista;
//import mx.itesm.personajes.Sprite;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Esta clase se encarga de dibujar todos los elementos que conforman un nivel
 * del juego
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/10/2011
 * 
 */
public class Nivel extends View {

	private Paint paint;
	private Bitmap fondo;
	private Enemigo enemigo;
	private Protagonista oleg;
	private int xd, xp, offset;
	private Bitmap btnPausa;
	private Bitmap vida;
	private int yPersonaje, yOriginal;
	private boolean estaSaltando = false;
	private Bitmap plataforma;
	private int contadorVida = 3;
	private boolean invulnerable = false;
	private int tiempoInvulnerable = 0;
	private boolean juegoTerminado;
	private boolean pausa;
	private int puntaje;
	private final int ALTURA_MAXIMA = 100;
	private int saltoRealizado = 0;
	private boolean estaCayendo;
	private Bitmap btnSaltar;

	/**
	 * Constructor de la clase Nivel
	 * 
	 * @param contexto
	 *            Indica en que contexto se usará todo lo que se dibuje
	 */
	public Nivel(Context contexto/* , Bitmap fondo */) {
		super(contexto);
		paint = new Paint();
		fondo = BitmapFactory.decodeResource(getResources(),
				R.drawable.montanas);
		xp = 0;
		yOriginal = 0;

		oleg = new Protagonista(contexto, xd, yPersonaje);
		enemigo = new Enemigo(contexto, 250, 200);
		plataforma = BitmapFactory.decodeResource(getResources(),
				R.drawable.primeraplataforma);
		vida = BitmapFactory.decodeResource(getResources(), R.drawable.manzana);
		btnSaltar = BitmapFactory.decodeResource(getResources(),
				R.drawable.saltar);
		btnPausa = BitmapFactory.decodeResource(getResources(),
				R.drawable.pausar);
		
		pausa= false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Bitmap plataformaEscala = Bitmap.createScaledBitmap(plataforma,
				fondo.getWidth(), btnSaltar.getHeight(), false);
		canvas.drawBitmap(fondo, -offset, 0, paint);
		canvas.drawBitmap(plataformaEscala, -offset, canvas.getHeight()
				- plataformaEscala.getHeight(), paint);
		canvas.drawBitmap(btnPausa, canvas.getWidth() - btnPausa.getWidth(), 0,
				paint);
		canvas.drawBitmap(btnSaltar, canvas.getWidth() - btnSaltar.getWidth(),
				(canvas.getHeight() - btnSaltar.getHeight()), paint);
		
		plataforma = plataformaEscala;
		plataformaEscala = null;

		yOriginal = (int) (canvas.getHeight() - plataforma.getHeight() - oleg
				.getAlto());
		calcularXd();
		calcularYPersonaje();
		
		enemigo.setY(canvas.getHeight() - plataforma.getHeight()
				- enemigo.getAlto());
		oleg.dibujar(canvas, paint);
		enemigo.dibujar(canvas, paint);

		if (pausa) {
			
			

			paint.setColor(0x44000000);
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			paint.setColor(Color.BLACK);
			paint.setTextSize(50);
			canvas.drawText("Pausa", canvas.getWidth() / 3,
					canvas.getHeight() / 2, paint);	

		}

		if (juegoTerminado) {

			paint.setColor(0x44000000);
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			paint.setColor(Color.BLUE);
			paint.setTextSize(30);
			canvas.drawText("GAME OVER", canvas.getWidth() / 3,
					canvas.getHeight() / 2, paint);

		}

		int coordenadaXVida = 0;
		for (int i = 1; i <= contadorVida; i++) {
			canvas.drawBitmap(this.vida, coordenadaXVida, 0, paint);
			coordenadaXVida = vida.getWidth() + coordenadaXVida;

		}

		paint.setColor(Color.WHITE);
		paint.setTextSize(14);
		canvas.drawText("Score: " + puntaje, canvas.getWidth(),
				canvas.getHeight(), paint);

	}

	private void calcularXd() {
		int ancho = getWidth();
		if (xp < ancho / 2) {
			xd = xp;
			offset = 0;

		} else if (xp > fondo.getWidth() - ancho / 2) {
			xd = xp - (fondo.getWidth() - ancho);
			offset = fondo.getWidth() - ancho;

		} else {
			xd = (ancho / 2);
			offset = (xp - ancho / 2);
		}
	}

	/**
	 * Método que se encarga de actualizar todos los elementos que se han
	 * dibujado
	 */
	public void actualizar() {

		if (!pausa && !juegoTerminado) {
			oleg.moverse();
			enemigo.moverse();			
			
			xp += 3;
			oleg.setX(xd-oleg.getAncho());
			oleg.setY(yPersonaje);
									
			

			if (invulnerable) {
				if (tiempoInvulnerable < 5000) {
					tiempoInvulnerable += 34;
				} else {
					invulnerable = false;
					tiempoInvulnerable = 0;
				}
			}

			if ((250 - 50 <= xd + oleg.getWidth() && // condicion de choque izq
					xd + oleg.getWidth() < enemigo.getX() || // reconoce si se
																// encuentra a
																// la
																// izq
			250 - 50 < xd + oleg.getWidth() && // reconoce si se encuentra a la
												// der
					xd < 50 + enemigo.getX() // condicion de choque der
			)// &&(
				// enemigo.getY() >= oleg.getY()
				// )
			) {
				if (!invulnerable) {
					contadorVida--;
					if (contadorVida == 0) {
						juegoTerminado = true;
					}
					invulnerable = true;

				}
			}
		} else {
			
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction()==MotionEvent.ACTION_DOWN){

			if (event.getX() > getWidth() - btnPausa.getWidth()
					&& event.getY() < btnPausa.getHeight()) {
				pausa = !pausa;
				if (juegoTerminado) {
					pausa = false;
				}
			} else {
			//	if (!pausa && !juegoTerminado) {
					if (!estaSaltando && !estaCayendo) {
						estaSaltando = true;
					}
			//	}
			}
		}

		return true;
	}

	private void calcularYPersonaje() {
		if (!pausa) {
			if (estaSaltando) {
				if (saltoRealizado <= ALTURA_MAXIMA) {
					oleg.saltar();
					yPersonaje = (int) (oleg.getY() - 10);
					saltoRealizado += 10;
				} else {
					estaCayendo = true;
					estaSaltando = false;
				}
			} else if (estaCayendo) {
				if (saltoRealizado > 0) {
					oleg.caer();
					yPersonaje = (int) (oleg.getY() + 10);
					saltoRealizado -= 10;
				} else {
					estaCayendo = false;
					estaSaltando = false;
				}

			} else {
				oleg.voltearDer();
				yPersonaje = yOriginal;
			}
		}
	}
	
	

}