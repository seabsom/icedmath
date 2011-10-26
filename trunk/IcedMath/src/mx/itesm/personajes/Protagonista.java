package mx.itesm.personajes;

import android.content.Context;
import mx.itesm.menus.R;

public class Protagonista extends Personaje {

	public Protagonista(Context contexto, Posicion nuevaPosicion) {
		super(contexto, nuevaPosicion);
		spriteNormal();
	}
	
	public void spriteNormal(){
		int[] ids ={ R.drawable.normalder, R.drawable.caminaunoder,
				R.drawable.caminadosder, R.drawable.caminatresder,
				R.drawable.caminacuatroder, R.drawable.caminacincoder};
		super.setSprite(new Sprite(getResources(), ids));
	}
	
	public void voltear(){
		int[] ids ={ R.drawable.normalizq, R.drawable.caminauno,
				R.drawable.caminados, R.drawable.caminatres,
				R.drawable.caminacuatro, R.drawable.caminacinco};
		super.setSprite(new Sprite(getResources(), ids));
	}
	
	
	public void saltar() {
		// TODO Auto-generated method stub

	}

	public void tomarItem() {

	}

	@Override
	public void moverseAdelante() {
		getSprite().nextFrame();
		getPosicion().x+= 4; //Revisar esta l�nea: se deber�a usar setPosicion... 
	}
	
	public void moverseAtras() {
		getSprite().nextFrame();
		getPosicion().x-= 4; //Revisar esta l�nea: se deber�a usar setPosicion... 
	}
	
	public void pararse(){
		getSprite().firstFrame();
	}

}
