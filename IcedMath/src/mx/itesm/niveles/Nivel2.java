package mx.itesm.niveles;

import java.util.LinkedList;

import mx.itesm.menus.R;
import mx.itesm.personajes.Enemigo;
import mx.itesm.personajes.Obstaculos;
import mx.itesm.personajes.Protagonista;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Esta clase se encarga de dibujar todos los elementos que conforman el nivel 2
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
public class Nivel2 extends View {

	private Paint paint;
	private Bitmap fondo;
	private LinkedList<Enemigo> listaEnemigos;
	private LinkedList<Obstaculos> listaObstaculos;
	private int posEnemigosX = 250;
	private int distanciaEnemigos = 200;
	private int posObstaculoX = 350;
	private int distanciaObstaculos = 400;
	private int numeroObstaculos = 6;
	private int numeroEnemigos = 15;
	private Protagonista oleg;
	private int xPersonaje, xRelativaPersonaje, offset;
	private Bitmap btnPausa;
	private Bitmap vida;
	private Bitmap burbuja;
	private int yPersonaje, yOriginal;
	private boolean estaSaltando = false;
	private Bitmap plataforma;
	private int contadorVida = 4;
	private boolean invulnerable = false;
	private int tiempoInvulnerable = 0;
	private boolean juegoTerminado;
	private boolean pausa;
	private int puntaje;
	private float puntajeFloat;
	private final int ALTURA_MAXIMA = 100;
	private int saltoRealizado = 0;
	private boolean estaCayendo;
	private Bitmap btnSaltar;
	private boolean primerToquePlataforma;
	private boolean estaAtacando;
	private int tiempoAtaque = 0;
	private boolean apareceConfirmacionPausa;
	private boolean volverMenu;
	private final int TAMANO_TEXTO = 20;
	private Bitmap puerta;
	private int tiempoNivel;
	private boolean nivelTerminado;
	private final int TIEMPO_NIVEL=	21500;
	
	/**
	 * Constructor de la clase Nivel
	 * 
	 * @param contexto
	 *            Indica en que contexto se usará todo lo que se dibuje
	 */
	public Nivel2(Context contexto/* , Bitmap fondo */) {
		super(contexto);
		paint = new Paint();
		fondo = BitmapFactory.decodeResource(getResources(),
				R.drawable.montanas2);
		xRelativaPersonaje = 0;
		yOriginal = 0;

		oleg = new Protagonista(contexto, xPersonaje, yPersonaje);
		listaEnemigos = new LinkedList<Enemigo>();
		for (int i = 0; i < numeroEnemigos; i++) {
			listaEnemigos.add(new Enemigo(contexto, posEnemigosX, 0));
			posEnemigosX += distanciaEnemigos;
		}
		listaObstaculos = new LinkedList<Obstaculos>();
		for (int i = 0; i < numeroObstaculos; i++) {
			listaObstaculos.add(new Obstaculos(contexto, posObstaculoX, 0));
			posObstaculoX += distanciaObstaculos;
		}

		plataforma = BitmapFactory.decodeResource(getResources(),
				R.drawable.primeraplataforma);
		vida = BitmapFactory.decodeResource(getResources(), R.drawable.manzana);
		burbuja = BitmapFactory.decodeResource(getResources(),
				R.drawable.burbuja);
		btnSaltar = BitmapFactory.decodeResource(getResources(),
				R.drawable.saltar);
		btnPausa = BitmapFactory.decodeResource(getResources(),
				R.drawable.pausar);
		puerta = BitmapFactory
				.decodeResource(getResources(), R.drawable.puerta);
		primerToquePlataforma = false;
		pausa = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Bitmap plataformaEscala = Bitmap.createScaledBitmap(plataforma,
				plataforma.getWidth(), btnSaltar.getHeight(), false);
		canvas.drawBitmap(fondo, -offset, 0, paint);
		canvas.drawBitmap(btnPausa, canvas.getWidth() - btnPausa.getWidth(), 0,
				paint);

		plataforma = plataformaEscala;
		plataformaEscala = null;
		int anchoPlataforma = plataforma.getWidth();
		int anchofondo = fondo.getWidth();

		for (int i = 0; i < anchofondo; i += anchoPlataforma) {
			canvas.drawBitmap(plataforma, i - offset, canvas.getHeight()
					- plataforma.getHeight(), paint);
		}

		canvas.drawBitmap(
				puerta,
				fondo.getWidth() - offset - puerta.getWidth(),
				canvas.getHeight() - plataforma.getHeight()
						- puerta.getHeight(), paint);

		yOriginal = (int) (canvas.getHeight() - plataforma.getHeight() - oleg
				.getAlto());
		calcularXPersonaje();
		calcularYPersonaje();
		for (int i = 0; i < listaEnemigos.size(); i++) {
			listaEnemigos.get(i).dibujar(canvas, paint);
			listaEnemigos.get(i).setY(
					canvas.getHeight() - plataforma.getHeight()
							- listaEnemigos.get(i).getAlto());
		}

		for (int i = 0; i < listaObstaculos.size(); i++) {
			listaObstaculos.get(i).dibujar(canvas, paint);
			listaObstaculos.get(i).setY(
					canvas.getHeight() - plataforma.getHeight()
							- listaObstaculos.get(i).getAlto());
		}
		oleg.dibujar(canvas, paint);

		if (pausa) {

			paint.setColor(0x44000000);
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			paint.setColor(Color.WHITE);
			paint.setTextSize(TAMANO_TEXTO);
			canvas.drawText("Pausa", 0, canvas.getHeight() / 4, paint);
			canvas.drawText("Continuar", canvas.getWidth() / 2,
					canvas.getHeight() / 4, paint);
			canvas.drawText("Volver al menú", canvas.getWidth() / 2,
					canvas.getHeight() / 2, paint);

		}

		if (apareceConfirmacionPausa) {

			paint.setColor(0x44000000);
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			paint.setColor(Color.WHITE);
			paint.setTextSize(TAMANO_TEXTO);
			canvas.drawText("¿Seguro que deseas regresar?", 0,
					2 * TAMANO_TEXTO, paint);
			canvas.drawText("Si", canvas.getWidth() / 2,
					canvas.getHeight() / 3, paint);
			canvas.drawText("No", canvas.getWidth() / 2,
					2 * canvas.getHeight() / 3, paint);

		}

		int coordenadaXVida = 0;
		for (int i = 1; i <= contadorVida; i++) {
			canvas.drawBitmap(this.vida, coordenadaXVida, 0, paint);
			coordenadaXVida = vida.getWidth() + coordenadaXVida;
		}

		if (invulnerable) {
			canvas.drawBitmap(this.burbuja, oleg.getX(), oleg.getY(), paint);
		}

		puntaje = (int) puntajeFloat;
		paint.setColor(Color.WHITE);
		paint.setTextSize(20);
		canvas.drawText("Puntos: " + puntaje, (canvas.getWidth() / 2) - 50, 20,
				paint);

	}

	private void calcularXPersonaje() {
		int ancho = getWidth();
		if (xRelativaPersonaje < ancho / 2) {
			xPersonaje = xRelativaPersonaje;
			offset = 0;

		} else if (xRelativaPersonaje > fondo.getWidth() - ancho / 2) {
			xPersonaje = xRelativaPersonaje - (fondo.getWidth() - ancho);
			offset = fondo.getWidth() - ancho;

		} else {
			xPersonaje = (ancho / 2);
			offset = (xRelativaPersonaje - ancho / 2);
		}
	}

	/**
	 * Método que se encarga de actualizar todos los elementos que se han
	 * dibujado
	 */
	public void actualizar() {

		if (!juegoTerminado && !apareceConfirmacionPausa && !pausa) {
			puntajeFloat += 0.2;
			oleg.moverse();

			for (int i = 0; i < listaEnemigos.size(); i++) {
				listaEnemigos.get(i).moverse();
			}
			for (int i = 0; i < listaObstaculos.size(); i++) {
				listaObstaculos.get(i).moverse();
			}

			xRelativaPersonaje += 4;
			oleg.setX(xPersonaje - oleg.getAncho());
			oleg.setY(yPersonaje);

			if (invulnerable) {
				if (tiempoInvulnerable < 1000) {
					tiempoInvulnerable += 34;
				} else {
					invulnerable = false;
					tiempoInvulnerable = 0;
				}
			}

			if (estaAtacando) {
				if (tiempoAtaque < 500) {
					tiempoAtaque += 34;
				} else {
					estaAtacando = false;
					tiempoAtaque = 0;
					oleg.voltearDer();
				}
			}

			for (int i = 0; (i < listaEnemigos.size())
					|| (i < listaObstaculos.size()); i++) {
				if (comprobarChoques()) {

					if (!estaAtacando) {
						if (!invulnerable) {
							contadorVida--;
							if (contadorVida == 0) {
								juegoTerminado = true;
							}
							invulnerable = true;

						}
					} else {
						listaEnemigos.get(i).morir();
						puntajeFloat += 50.0;
						;
					}
				}
			}
			if (tiempoNivel < TIEMPO_NIVEL) {
				tiempoNivel += 34;
			}else{
				nivelTerminado=true;
			}

		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (!juegoTerminado && !apareceConfirmacionPausa && !pausa) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				if (event.getX() > getWidth() - btnPausa.getWidth()
						&& event.getY() < btnPausa.getHeight()) {
					pausa = true;
				} else
				// if (!pausa && !juegoTerminado) {
				if (!estaSaltando && !estaCayendo) {
					estaSaltando = true;

					// }
				}
			}
		} else if (pausa && !juegoTerminado && !apareceConfirmacionPausa) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (event.getX() > getWidth() / 2
						&& event.getY() > (getHeight() / 4) - TAMANO_TEXTO
						&& event.getY() < (getHeight() / 2) - TAMANO_TEXTO) {
					pausa = false;
				}
				if (event.getX() > getWidth() / 2
						&& event.getY() > (getHeight() / 2) - TAMANO_TEXTO
						&& event.getY() < 3 * getHeight() / 4) {
					apareceConfirmacionPausa = true;
					pausa = false;

				}
			}

		} else if (!juegoTerminado && !pausa && apareceConfirmacionPausa) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (event.getX() > getWidth() / 2
						&& event.getY() > (getHeight() / 3) - TAMANO_TEXTO
						&& event.getY() < (2 * getHeight() / 3) - TAMANO_TEXTO) {
					volverMenu = true;
					apareceConfirmacionPausa = false;

				}
				if (event.getX() > getWidth() / 2
						&& event.getY() > (2 * getHeight() / 3) - TAMANO_TEXTO
						&& event.getY() < getHeight()) {
					pausa = true;
					apareceConfirmacionPausa = false;
				}
			}

		}
		return true;
	}

	private void calcularYPersonaje() {
		if (!pausa) {
			if (estaSaltando) {
				if (saltoRealizado <= ALTURA_MAXIMA) {
					if (!estaAtacando) {
						oleg.saltar();
					}
					yPersonaje = (int) (oleg.getY() - 15);
					saltoRealizado += 15;
				} else {
					estaCayendo = true;
					estaSaltando = false;
				}
			} else if (estaCayendo) {
				if (saltoRealizado > 0) {
					if (!estaAtacando) {
						oleg.caer();
					}
					yPersonaje = (int) (oleg.getY() + 10);
					saltoRealizado -= 10;
				} else {
					estaCayendo = false;
					estaSaltando = false;
					primerToquePlataforma = true;
				}

			} else {
				yPersonaje = yOriginal;
				if (primerToquePlataforma) {
					oleg.voltearDer();
					primerToquePlataforma = false;
				}
			}
		}
	}

	public boolean estaPausado() {
		return pausa;
	}

	public boolean recienReanudado() {
		return !pausa;
	}

	public boolean comprobarChoques() {
		for (int i = 0; i < listaEnemigos.size(); i++) {
			if ((listaEnemigos.get(i).getX() <= xPersonaje
					&& xPersonaje < listaEnemigos.get(i).getX() || listaEnemigos
					.get(i).getX() < xPersonaje
					&& xPersonaje < listaEnemigos.get(i).getX()
							+ listaEnemigos.get(i).getAncho())) {
				if (listaEnemigos.get(i).getY() <= yPersonaje + oleg.getAlto()
						&& yPersonaje < listaEnemigos.get(i).getY()
						|| listaEnemigos.get(i).getY() < yPersonaje
						&& yPersonaje < listaEnemigos.get(i).getY()
								+ listaEnemigos.get(i).getAlto()) {
					return true;
				}
			}
		}
		for (int i = 0; i < listaObstaculos.size(); i++) {
			if ((listaObstaculos.get(i).getX() <= xPersonaje
					&& xPersonaje < listaObstaculos.get(i).getX() || listaObstaculos
					.get(i).getX() < xPersonaje
					&& xPersonaje < listaObstaculos.get(i).getX()
							+ listaObstaculos.get(i).getAncho())) {
				if (listaObstaculos.get(i).getY() <= yPersonaje
						+ oleg.getAlto()
						&& yPersonaje < listaObstaculos.get(i).getY()
						|| listaObstaculos.get(i).getY() < yPersonaje
						&& yPersonaje < listaObstaculos.get(i).getY()
								+ listaObstaculos.get(i).getAlto()) {
					return true;
				}
			}
		}
		return false;

	}

	public Protagonista getProtagonista() {
		return oleg;
	}

	public void setAtacando(boolean atacando) {
		estaAtacando = atacando;
	}

	public boolean getJuegoTerminado() {

		return juegoTerminado;

	}

	public boolean getConfirmacionPausa() {
		return apareceConfirmacionPausa;
	}

	public boolean getVolverMenu() {
		return volverMenu;
	}

	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}

	public boolean getNivelTerminado() {
		return nivelTerminado;
	}

}