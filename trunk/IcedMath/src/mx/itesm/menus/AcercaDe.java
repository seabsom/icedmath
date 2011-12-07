package mx.itesm.menus;

import mx.itesm.audio.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Esta clase se encarga de mostrar la pantalla "Acerca de" (opción del menú principal)
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/10/2011
 *
 */
public class AcercaDe extends Activity {
	
	private Musica musica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.acercade);
		
		musica = new Musica(R.raw.spanishflea, this);
		musica.play();
	}
	
	@Override
	protected void onStop() {
		musica.stop();
		super.onStop();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intencion = new Intent(this, MainMenu.class);
			startActivity(intencion);
			finish();
		}
		return true;

	}

}
