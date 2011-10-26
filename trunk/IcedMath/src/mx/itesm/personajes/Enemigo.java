package mx.itesm.personajes;

import mx.itesm.menus.R;
import android.content.Context;

public class Enemigo extends Personaje {

	public Enemigo(Context contexto, float x, float y) {
		super(contexto, x, y);
		int []ids = { R.drawable.specteruno, R.drawable.specterdos,
				R.drawable.spectertres, R.drawable.spectercuatro,
				R.drawable.spectercinco, R.drawable.specterseis,
				R.drawable.spectersiete };
		super.setSprite(new Sprite(getResources(), ids));
	}

	@Override
	public void moverseAdelante() {
		getSprite().nextFrame();
	}
}
