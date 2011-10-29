package mx.itesm.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class Itesm extends Activity {

	private static int SPLASH_TIME = 100; // tiempo a mostrar Activity en ms

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.itesm);

		// thread para SplashScreen
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < SPLASH_TIME) {
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e) {
					// no hacer nada
				} finally {
					startActivity(new Intent(Itesm.this, MadIce.class));
					finish();
					stop();
				}
			}
		};
		splashTread.start();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			super.onDestroy();
		}
		return true;
	}

}