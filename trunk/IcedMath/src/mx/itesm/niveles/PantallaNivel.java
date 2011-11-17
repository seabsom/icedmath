package mx.itesm.niveles;

import mx.itesm.audio.Musica;
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
 * la clase "Nivel"
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/11/2011
 * 
 */
public class PantallaNivel extends Activity implements Runnable {

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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intencion= new Intent(this,MainMenu.class);
			startActivity(intencion);
			finish();
			
		}
		
		return true;
		
	}

}
