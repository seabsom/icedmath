package mx.itesm.menus;

import mx.itesm.audio.Musica;
import mx.itesm.niveles.PantallaNivel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameOver extends Activity implements OnClickListener {
	
	private Musica musica;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		
		musica= new Musica(R.raw.fourbravechampions, this);
		musica.play();
		
		Button btnRetry = (Button) findViewById(R.id.btnRetry);
		Button btnVolver = (Button) findViewById(R.id.btnVolver);
		btnRetry.setOnClickListener(this);
		btnVolver.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intencion;
		switch (v.getId()) {
		case R.id.btnRetry:
			intencion = new Intent(this, PantallaNivel.class);
			startActivity(intencion);
			finish();
			break;
		case R.id.btnVolver:
			intencion = new Intent(this, MainMenu.class);
			startActivity(intencion);
			finish();
			break;
		default:
			break;
		}

	}

}
