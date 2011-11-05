package mx.itesm.niveles;

import mx.itesm.audio.Musica;
import mx.itesm.menus.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PantallaNivelVilla extends Activity implements Runnable {

	private Nivel nivel;
	private boolean corriendo;
	private Musica musica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		nivel = new Nivel(this);
		setContentView(nivel);
		musica = new Musica(R.raw.darkskies, this);
		musica.play();
	}

	@Override
	protected void onStop() {
		musica.stop();
		corriendo = false;
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		corriendo = true;
		while (corriendo) {
			nivel.actualizar();
			nivel.postInvalidate();
			try {
				Thread.sleep(34);
			} catch (InterruptedException e) {
			}
		}

	}

}
