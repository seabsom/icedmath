package mx.itesm.personajes;

import android.content.Context;
import mx.itesm.menus.Sprite;

public class Protagonista extends Personaje {

	public Protagonista(Context contexto, Sprite nuevoSprite,
			Posicion nuevaPosicion) {
		super(contexto, nuevoSprite, nuevaPosicion);
	}

	public void saltar() {
		// TODO Auto-generated method stub

	}

	public void tomarItem() {

	}

	@Override
	public void moverse() {
		getSprite().nextFrame();
		getPosicion().x= getPosicion().x+5;
	}

}
