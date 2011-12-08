package mx.itesm.audio;

import android.content.Context;
import android.media.MediaPlayer;



/**
 * Esta clase se encarga de reproducir m�sica en la actividad en la cual es llamada
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 29/10/2011
 *
 */
/**
 * @author Edwin
 *
 */
public class Musica {

	private MediaPlayer player;
	private Context contexto;
	private int cancion;

	/**
	 * M�todo constructor de la clase M�sica
	 * @param cancion Par�metro que indica con que canci�n va a ser creado el objeto 
	 * @param contexto Par�metro que indica el contexto en el que ser� usado el objeto 
	 */
	public Musica(int cancion, Context contexto) {
		this.cancion = cancion;
		this.contexto = contexto;
	}

	/**
	 * M�todo que sirve para comenzar a reproducir la m�sica
	 */
	public void reproducir() {
		if (player != null) {
			player.release();
		}
		player = MediaPlayer.create(contexto, cancion);
		player.start();
		player.setLooping(true);
	}

	/**
	 * M�todo que sirve para detener la reproducci�n de m�sica
	 */
	public void detener() {
		if (player != null) {
			if (player.isPlaying()) {
				player.stop();
				player.release();
			}
		}
	}
	
	/**
	 * M�todo que sirve para pausar la canci�n
	 */
	public void pausar(){
		if (player != null) {
			if (player.isPlaying()) {
				player.pause();				
			}		
		}
	}	
	
	/**
	 * M�todo que sirve para reanudar una canci�n pausada
	 */
	public void reanudar(){
		if (player != null) {
			if (!player.isPlaying()) {
				player.start();				
			}		
		}
	}
	
	
	/**
	 * M�todo que nos dice si el reproductor esta reproduciendo algo
	 * @return si el reproductor reproduce algo o no
	 */
	public boolean estaReproduciendo(){
		if(player!=null){
			if (player.isPlaying()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Nos dice cual es la canci�n actual del reproductor
	 * @return la canci�n actual
	 */
	public int getCancion() {
		return cancion;
	}

}
