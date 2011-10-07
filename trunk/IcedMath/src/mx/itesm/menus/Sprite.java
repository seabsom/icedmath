package mx.itesm.menus;

import java.util.LinkedList;

import mx.itesm.personajes.Posicion;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Sprite {
	private LinkedList<Bitmap> listaImgs;
	private int indice;
	
	public Sprite(Resources admRecursos, int[] ids){
		listaImgs= new LinkedList<Bitmap>();
		for(int id:ids){
			Bitmap bm= BitmapFactory.decodeResource(admRecursos, id);
			listaImgs.add(bm);
		}
		indice=0;
	}
	
	public void draw(Canvas canvas, Paint p, Posicion posicion){
		canvas.drawBitmap(listaImgs.get(indice), posicion.x, posicion.y, p);
	}
	
	public void nextFrame(){
		indice=(indice+1)%listaImgs.size();
	}
	
	public void prevFrame(){
		indice=(indice-1)%listaImgs.size();
	}

}
