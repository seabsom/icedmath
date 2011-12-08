package mx.itesm.personajes;

import mx.itesm.menus.R;
import android.content.Context;

public class Obstaculos extends Personaje {

	public Obstaculos(Context contexto, float x, float y) {
		super(contexto, x, y);
		int[] ids = {R.drawable.picoscortos				
		};
		super.setSprite(new Sprite(getResources(), ids));
		
	}
	
	@Override
	public void moverse() {
		setX(getX()-4);		
	}

}