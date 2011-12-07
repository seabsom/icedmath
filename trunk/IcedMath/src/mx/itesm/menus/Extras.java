package mx.itesm.menus;

import mx.itesm.audio.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Esta clase se encarga de mostrar la pantalla "Extras" (opción del menú
 * principal)
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/10/2011
 * 
 */
public class Extras extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.extras);

		if (IcedMath.player == null) {
			IcedMath.player = new Musica(R.raw.rageofthechampions, this);
			IcedMath.player.reproducir();			
		}

		Button btnAudio = (Button) findViewById(R.id.btnAudioExtra);
		Button btnGaleria = (Button) findViewById(R.id.btnGaleria);

		btnAudio.setOnClickListener(this);
		btnGaleria.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intencion;
		switch (v.getId()) {
		case R.id.btnGaleria:
			intencion = new Intent(this, Galeria.class);
			startActivity(intencion);
			finish();
			break;
		case R.id.btnAudioExtra:
			IcedMath.player.detener();
			IcedMath.player = null;
			intencion = new Intent(this, AudioExtra.class);
			startActivity(intencion);
			finish();
			break;
		}
	}

}
