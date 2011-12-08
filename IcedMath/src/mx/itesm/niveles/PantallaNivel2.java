package mx.itesm.niveles;

import mx.itesm.audio.Musica;
import mx.itesm.entradas.Acelerometro;
import mx.itesm.menus.GameOver;
import mx.itesm.menus.MainMenu;
import mx.itesm.menus.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Esta clase se encarga de mostrar todo el contenido del nivel haciendo uso de
 * la clase "Nivel2"
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/11/2011
 * 
 */
public class PantallaNivel2 extends Activity implements Runnable {

	private Nivel2 nivel;
	private boolean corriendo;
	private Musica musica;
	private Acelerometro acelerometro;
	private boolean pausado;
	private final long VELOCIDAD_THREAD = 34;
	private boolean juegoTerminado;
	private boolean regresarMenu;
	private boolean confirmacionRegreso;
	private boolean nivelDosTerminado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		nivel = new Nivel2(this);
		setContentView(nivel);

		acelerometro = new Acelerometro(this, nivel);

		musica = new Musica(R.raw.winterbliss, this);
		musica.reproducir();

	}

	@Override
	protected void onStop() {
		acelerometro.desactivar();
		acelerometro = null;
		corriendo = false;
		musica.detener();
		super.onStop();
	}

	@Override
	protected void onPause() {
		corriendo = false;
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		corriendo = true;
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		corriendo = true;
		while (corriendo) {
			nivel.actualizar();
			nivel.postInvalidate();
			pausado = nivel.estaPausado();
			confirmacionRegreso = nivel.getConfirmacionPausa();
			juegoTerminado = nivel.getJuegoTerminado();
			regresarMenu = nivel.getVolverMenu();			
			nivelDosTerminado = nivel.getNivelTerminado();

			if (pausado || confirmacionRegreso) {
				musica.pausar();

			} else {
				musica.reanudar();
			}
			if (juegoTerminado) {
				finish();
				Intent intencion = new Intent(this, GameOver.class);
				startActivity(intencion);

			}
			
			if(nivelDosTerminado){
				finish();
				Intent intencion = new Intent(this, MainMenu.class);
				startActivity(intencion);
			}

			if (regresarMenu) {
				finish();
				Intent intencion = new Intent(this, MainMenu.class);
				startActivity(intencion);

			}

			try {
				Thread.sleep(VELOCIDAD_THREAD);
			} catch (InterruptedException e) {

			}

		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			nivel.setPausa(true);
		}
		return true;

	}

}
