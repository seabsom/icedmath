package mx.itesm.menus;

import mx.itesm.audio.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Esta clase se encarga de mostrar la sección "Audio Extra" (subsección de "Extras")
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/11/2011
 * 
 */
public class AudioExtra extends Activity implements OnClickListener{
	
	private Musica player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.audioextra);

		Button reproducirFactory = (Button) findViewById(R.id.btnReproducirFactory);
		Button reproducirSimple = (Button) findViewById(R.id.btnReproducirSimple);
		Button reproducirSpace = (Button) findViewById(R.id.btnReproducirSpacePirates);
		Button reproducirShow = (Button) findViewById(R.id.btnReproducirShow);

		
		reproducirFactory.setOnClickListener(this);
		reproducirSimple.setOnClickListener(this);
		reproducirSpace.setOnClickListener(this);
		reproducirShow.setOnClickListener(this);

	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnReproducirFactory:
			if (player!=null) {
				if (player.estaReproduciendo()) {
					player.detener();
				}
			}
			player=new Musica(R.raw.factory, this);
			player.reproducir();
			break;
		case R.id.btnReproducirSimple:
			if (player!=null) {
				if (player.estaReproduciendo()) {
					player.detener();
				}
			}			
			player=new Musica(R.raw.simplesight, this);
			player.reproducir();
			break;
		case R.id.btnReproducirSpacePirates:
			if (player!=null) {
				if (player.estaReproduciendo()) {
					player.detener();
				}
			}			
			player=new Musica(R.raw.spacepirates, this);
			player.reproducir();
			break;
		case R.id.btnReproducirShow:
			if (player!=null) {
				if (player.estaReproduciendo()) {
					player.detener();
				}
			}			
			player=new Musica(R.raw.theshow, this);
			player.reproducir();
			break;
			default:
			break;
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (player!=null) {
				player.detener();
				player = null;
			}
			Intent intencion = new Intent(this, Extras.class);
			startActivity(intencion);
			finish();
		}
		return true;

	}
}
