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


public class GameOver extends Activity implements OnClickListener {
	
	private Musica player;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.gameover);
		
		player= new Musica(R.raw.fourbravechampions, this);
		player.reproducir();
		

		Button btnRetry = (Button) findViewById(R.id.btnRetry);
		Button btnMenu = (Button) findViewById(R.id.btnMenu);

		btnRetry.setOnClickListener(this);
		btnMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intencion;
		switch (v.getId()) {
		case R.id.btnRetry:
			player.detener();		
			intencion= new Intent(this, PantallaNivel.class);
			startActivity(intencion);
			finish();
			break;
		case R.id.btnMenu:
			player.detener();
			intencion= new Intent(this, MainMenu.class);
			startActivity(intencion);
			finish();
			break;
		default:
			break;
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){			
		}
		return true;
		
	}

}
