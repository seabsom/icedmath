package mx.itesm.menus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Esta clase se encarga de mostrar la pantalla "Puntuaciones Altas" (opción del menú principal)
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/10/2011
 *
 */
public class PuntuacionesAltas extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.puntuacionesaltas);
	}

}
