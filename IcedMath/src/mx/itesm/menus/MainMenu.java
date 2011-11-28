package mx.itesm.menus;

import mx.itesm.audio.Musica;
import mx.itesm.niveles.PantallaNivel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Esta clase se encarga de mostrar el menú principal del juego
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/10/2011
 * 
 */
public class MainMenu extends Activity implements OnClickListener {

	private Musica player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.mainmenu);
		
		player = IcedMath.player;
		
		if (player == null) {
			player = new Musica(R.raw.rageofthechampions, this);
			player.play();
		}

		((Button) findViewById(R.id.Aventura)).setOnClickListener(this);
		// ((Button) findViewById(R.id.ModoLibre)).setOnClickListener(this);
		((Button) findViewById(R.id.PuntuacionesAltas))
				.setOnClickListener(this);
		((Button) findViewById(R.id.Extras)).setOnClickListener(this);
		((Button) findViewById(R.id.Ayuda)).setOnClickListener(this);
		((Button) findViewById(R.id.AcercaDe)).setOnClickListener(this);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			player.stop();
			finish();
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intencion;
		switch (v.getId()) {
		case R.id.Aventura:
			player.stop();
			IcedMath.player = null;
			intencion = new Intent(this, PantallaNivel.class);
			startActivity(intencion);
			finish();
			break;
		/*
		 * case R.id.ModoLibre: intencion= new
		 * Intent(this,PantallaNivelVilla.class); startActivity(intencion);
		 * break;
		 */
		case R.id.PuntuacionesAltas:
			intencion = new Intent(this, PuntuacionesAltas.class);
			startActivity(intencion);
			break;
		case R.id.Extras:
			intencion = new Intent(this, Extras.class);
			startActivity(intencion);
			break;
		case R.id.Ayuda:
			intencion = new Intent(this, Ayuda.class);
			startActivity(intencion);
			break;
		case R.id.AcercaDe:
			intencion = new Intent(this, AcercaDe.class);
			startActivity(intencion);
			break;
		default:
			break;
		}

	}

}