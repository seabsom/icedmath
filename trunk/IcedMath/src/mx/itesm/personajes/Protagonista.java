package mx.itesm.personajes;

import android.content.Context;
import mx.itesm.menus.R;

public class Protagonista extends Personaje {

	public Protagonista(Context contexto, Posicion nuevaPosicion) {
		super(contexto, nuevaPosicion);
		int[] ids= { R.drawable.normalder, R.drawable.caminaunoder,
				R.drawable.caminadosder, R.drawable.caminatresder,
				R.drawable.caminacuatroder, R.drawable.caminacincoder };
		super.setSprite(new Sprite(getResources(), ids));
	}

	public void saltar() {
		// TODO Auto-generated method stub

	}

	public void tomarItem() {

	}

	@Override
	public void moverse() {
		getSprite().nextFrame();
		getPosicion().x = getPosicion().x+5; //Revisar esta l’nea: se deber’a usar setPosicion... 
	}

}
