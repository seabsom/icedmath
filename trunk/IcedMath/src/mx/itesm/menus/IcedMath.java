package mx.itesm.menus;

import mx.itesm.audio.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Esta clase se encarga de mostrar la pantalla que indica al usuario que se
 * debe presionar para ir al men�
 * 
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 05/10/2011
 * 
 */
public class IcedMath extends Activity {
	
	static Musica player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicaci�n
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.icedmath);
		player= new Musica(R.raw.rageofthechampions, this);
		player.play();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			startActivity(new Intent(IcedMath.this, MainMenu.class));
			finish();
			return true;
		}
		return false;

	}

}