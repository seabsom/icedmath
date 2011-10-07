package mx.itesm.personajes;

import mx.itesm.menus.Sprite;
import android.content.Context;

public class Enemigo extends Personaje {

	public Enemigo(Context contexto, Sprite nuevoSprite, Posicion nuevaPosicion) {
		super(contexto, nuevoSprite, nuevaPosicion);
	}

	@Override
	public void moverse() {
		getSprite().nextFrame();
	}
}
