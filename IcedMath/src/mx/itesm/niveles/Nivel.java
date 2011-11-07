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
	private Bitmap derecha;
	private Bitmap izquierda;
	private boolean derIsPressed;
	private boolean izqIsPressed;
	private int xd, xp, offset;
	private Bitmap btnsaltar;
	private Bitmap btnatacar;
	private Bitmap vida;
	private float yOriginal;
	private boolean viendoDerecha;
	private boolean isJump = false;
	private Bitmap plataforma;
	private Bitmap puerta;
	private int cvida = 3;
	private boolean invul = false;
	private int tiempoinv = 0;

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
				R.drawable.montanas); // ASê Se deja el fondo fijo, pero se debe
										// recibir "fondo" y asignarlo a
										// "this.fondo"
		xp = 0;
		oleg = new Protagonista(contexto, xd, 260);
		enemigo = new Enemigo(contexto, 250, 200);
		derecha = BitmapFactory.decodeResource(getResources(),
				R.drawable.derecha);
		izquierda = BitmapFactory.decodeResource(getResources(),
				R.drawable.izquierda);
		btnsaltar = BitmapFactory.decodeResource(getResources(),
				R.drawable.saltar);
		btnatacar = BitmapFactory.decodeResource(getResources(),
				R.drawable.atacar);
		plataforma = BitmapFactory.decodeResource(getResources(),
				R.drawable.primeraplataforma);
		vida = BitmapFactory.decodeResource(getResources(), R.drawable.manzana);
		puerta = BitmapFactory
				.decodeResource(getResources(), R.drawable.puerta);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		calcularXd();

		// Bitmap fondoEscala= Bitmap.createScaledBitmap(fondo,
		// fondo.getWidth()*(pantallaAncho/fondo.getWidth()),
		// fondo.getHeight()*(pantallaAlto/fondo.getHeight()), false);

		canvas.drawBitmap(fondo, -offset, 0, paint);

		// Los botones siempre se escalarán de acuerdo a este criterio
		Bitmap btnAtacarEscala = Bitmap.createScaledBitmap(btnatacar,
				canvas.getWidth() / 6, canvas.getWidth() / 6, false);
		Bitmap btnSaltarEscala = Bitmap.createScaledBitmap(btnsaltar,
				canvas.getWidth() / 6, canvas.getWidth() / 6, false);
		Bitmap btnDerechaEscala = Bitmap.createScaledBitmap(derecha,
				canvas.getWidth() / 6, canvas.getWidth() / 6, false);
		Bitmap btnIzquierdaEscala = Bitmap.createScaledBitmap(izquierda,
				canvas.getWidth() / 6, canvas.getWidth() / 6, false);
		Bitmap plataformaEscala = Bitmap.createScaledBitmap(plataforma,
				plataforma.getWidth(), btnDerechaEscala.getHeight() + 5, false);
		canvas.drawBitmap(plataformaEscala, -offset, canvas.getHeight()
				- plataformaEscala.getHeight(), paint);
		canvas.drawBitmap(btnIzquierdaEscala, 0,
				(canvas.getHeight() - btnIzquierdaEscala.getHeight()), paint);
		canvas.drawBitmap(btnDerechaEscala, btnIzquierdaEscala.getWidth(),
				(canvas.getHeight() - btnDerechaEscala.getHeight()), paint);
		canvas.drawBitmap(btnSaltarEscala,
				canvas.getWidth() - btnSaltarEscala.getWidth(),
				(canvas.getHeight() - btnSaltarEscala.getHeight()), paint);
		canvas.drawBitmap(btnAtacarEscala, canvas.getWidth()
				- (2 * (btnAtacarEscala.getWidth())),
				(canvas.getHeight() - btnDerechaEscala.getHeight()), paint);
		canvas.drawBitmap(
				puerta,
				fondo.getWidth() - puerta.getWidth(),
				canvas.getHeight() - plataformaEscala.getHeight()
						- puerta.getHeight(), paint);

		/*
		 * oleg.setY(canvas.getHeight() - plataformaEscala.getHeight() -
		 * oleg.getAlto());
		 */
		enemigo.setY(canvas.getHeight() - plataformaEscala.getHeight()
				- enemigo.getAlto());

		oleg.dibujar(canvas, paint);
		enemigo.dibujar(canvas, paint);

		int extvida = 0;
		for (int i = 0; i <= cvida; i++) {
			canvas.drawBitmap(this.vida, extvida, 0, paint);
			extvida = vida.getWidth() + extvida;
		}

		izquierda = btnIzquierdaEscala;
		derecha = btnDerechaEscala;
		btnatacar = btnAtacarEscala;
		btnsaltar = btnSaltarEscala;

	}

	/**
	 * Método que calcula las posiciones relativas del fondo y del personaje
	 */
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

	/**
	 * Método que se encarga de actualizar todos los elementos que se han
	 * dibujado
	 */
	public void actualizar() {
		enemigo.moverse();
		if (derIsPressed) {
			viendoDerecha = true;
			oleg.moverse();
			xp += 4;
			oleg.setX(xd);
			if (oleg.getX() >= 790 || oleg.getX() <= 0) { // 790 es #m‡gico; se
															// debe usar
															// panatlla.get
															// width
				derIsPressed = false;
			}
		} else if (izqIsPressed) {
			viendoDerecha = false;
			oleg.moverse();
			xp -= 4;
			oleg.setX(xd);
			if (oleg.getX() <= -790 || oleg.getX() <= 0) { // 790 es #m‡gico; se
															// debe usar
															// panatlla.get
															// width
				izqIsPressed = false;
			}
		} else if (isJump) {

		} else {
			oleg.pararse();
		}

		if (enemigo.getX() >= xd - oleg.getX()
				&& enemigo.getX() <= xd + oleg.getWidth()) {
			enemigo.setX(-400);
			cvida--;
			if (enemigo.getY() >= oleg.getY()
					&& enemigo.getY() <= oleg.getY() - enemigo.getHeight()) {
				enemigo.setY(-400);
				cvida--;
			}
		}

		if (enemigo.getX() >= xd + oleg.getWidth()
				&& enemigo.getWidth() <= xd + oleg.getX()) {
			if (invul) {
				if (tiempoinv < 10000) {
					tiempoinv += 34;
				} else {
					invul = false;
					tiempoinv = 0;
				}
			} else {
				cvida = cvida - 1;
				invul = true;
			}

			if (enemigo.getY() >= oleg.getY()
					&& enemigo.getY() <= oleg.getY() - enemigo.getHeight()) {

				if (invul) {
					if (tiempoinv < 10000) {
						tiempoinv += 34;
					} else {
						invul = false;
						tiempoinv = 0;
					}

				} else {

					cvida = cvida - 1;
					invul = true;

				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:

			if ((event.getX() > izquierda.getWidth() && event.getX() < 2 * izquierda
					.getWidth())
					&& event.getY() > (getHeight() - derecha.getHeight())) {
				oleg.voltearDer();
				derIsPressed = true;

			} else if (event.getX() < izquierda.getWidth()
					&& event.getY() > (getHeight() - izquierda.getHeight())) {
				oleg.voltearIzq();
				izqIsPressed = true;

			}

			if (event.getX() > getWidth() - btnsaltar.getWidth()
					&& event.getY() > getHeight() - btnsaltar.getHeight()) {
				if (isJump == false) {
					isJump = true;
					yOriginal = oleg.getY();
					Thread thr = new Thread(new Runnable() {

						public void run() {
							if (viendoDerecha == true) {
								int[] ids = { R.drawable.saltarunoder,
										R.drawable.saltardosder };
								oleg.setSprite(new Sprite(getResources(), ids));
								while (oleg.getY() > yOriginal - 150) {
									oleg.saltar();
								}
								oleg.getSprite().nextFrame();
								while (oleg.getY() < yOriginal) {
									oleg.caer();
								}
								oleg.pararseDer();
								isJump = false;

							} else if (viendoDerecha == false) {
								int[] ids = { R.drawable.saltaruno,
										R.drawable.saltardos };
								oleg.setSprite(new Sprite(getResources(), ids));
								while (oleg.getY() > yOriginal - 150) {
									oleg.saltar();
								}
								oleg.getSprite().nextFrame();
								while (oleg.getY() < yOriginal) {
									oleg.caer();
								}
								oleg.pararseIzq();
								isJump = false;
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
