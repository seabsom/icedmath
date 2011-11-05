package mx.itesm.personajes;

import android.content.Context;

import mx.itesm.menus.R;

public class Protagonista extends Personaje {

	public Protagonista(Context contexto, float x, float y) {
		super(contexto, x, y);
		voltearDer();
	}
	
	public void voltearDer(){
		int[] ids ={ R.drawable.normalder, R.drawable.caminaunoder,
				R.drawable.caminadosder, R.drawable.caminatresder,
				R.drawable.caminacuatroder, R.drawable.caminacincoder};
		super.setSprite(new Sprite(getResources(), ids));
	}
	
	public void voltearIzq(){
		int[] ids ={ R.drawable.normalizq, R.drawable.caminauno,
				R.drawable.caminados, R.drawable.caminatres,
				R.drawable.caminacuatro, R.drawable.caminacinco};
		super.setSprite(new Sprite(getResources(), ids));
		setX(getX()+4);

	}
	
	
	public void saltar() {
		
			try {
				Thread.currentThread();
				Thread.sleep(10);
				setY(getY()-1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
	}
	public void caer() {

		try {
			Thread.currentThread();
			Thread.sleep(10);
			setY(getY()+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}		
	}

	public void tomarItem() {

	}

	@Override
	public void moverseAdelante() {
		getSprite().nextFrame();
		setX(getX()+4);
	}
	
	public void moverseAtras() {
		getSprite().nextFrame();
		setX(getX()-4);
	}
	
	
	public void pararse(){
		getSprite().firstFrame();	
	}
	
	public void pararseIzq(){
		int[] ids ={ R.drawable.normalizq};
		super.setSprite(new Sprite(getResources(), ids));	
	}
	
	public void pararseDer(){
		int[] ids ={ R.drawable.normalder};
		super.setSprite(new Sprite(getResources(), ids));
	}

}
