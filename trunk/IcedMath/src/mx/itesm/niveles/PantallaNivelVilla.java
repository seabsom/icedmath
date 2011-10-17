package mx.itesm.niveles;

import mx.itesm.menus.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PantallaNivelVilla extends Activity implements Runnable {

	private NivelVilla nivel;
	private boolean corriendo;
	private MediaPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		nivel = new NivelVilla(this);

		setContentView(nivel);
		reproducirAudio();

	}

	private void reproducirAudio() {
		if (player != null) {
			player.release();
		}
		player = MediaPlayer.create(this, R.raw.darkskies);
		player.start();
		player.setLooping(true);
	}

	@Override
	protected void onStop() {
		if (player != null) {
			if (player.isPlaying()) {
				player.stop();
				player.release();
			}
		}
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
