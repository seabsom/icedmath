package mx.itesm.menus;

import mx.itesm.niveles.PantallaNivelVilla;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.mainmenu);

		((Button) findViewById(R.id.Aventura)).setOnClickListener(this);
		// ((Button) findViewById(R.id.ModoLibre)).setOnClickListener(this);
		((Button) findViewById(R.id.PuntuacionesAltas))
				.setOnClickListener(this);
		((Button) findViewById(R.id.Extras)).setOnClickListener(this);
		((Button) findViewById(R.id.Ayuda)).setOnClickListener(this);
		((Button) findViewById(R.id.AcercaDe)).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intencion;
		switch (v.getId()) {
		case R.id.Aventura:
			intencion = new Intent(this, PantallaNivelVilla.class);
			startActivity(intencion);
			break;
	/*
	 * 	case R.id.ModoLibre: 
	 * 		intencion= new Intent(this,PantallaNivelVilla.class); 
	 * 		startActivity(intencion);
	 * 		break;
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