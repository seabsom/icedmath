package mx.itesm.audio;

import android.content.Context;
import android.media.MediaPlayer;



/**
 * Esta clase se encarga de reproducir música en la actividad en la cual es llamada
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
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
	 * Método constructor de la clase Música
	 * @param cancion Parámetro que indica con que canción va a ser creado el objeto 
	 * @param contexto Parámetro que indica el contexto en el que será usado el objeto 
	 */
	public Musica(int cancion, Context contexto) {
		this.cancion = cancion;
		this.contexto = contexto;
	}

	/**
	 * Método que sirve para comenzar a reproducir la música
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
	 * Método que sirve para detener la reproducción de música
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
	 * Método que sirve para pausar la canción
	 */
	public void pausar(){
		if (player != null) {
			if (player.isPlaying()) {
				player.pause();				
			}		
		}
	}	
	
	/**
	 * Método que sirve para reanudar una canción pausada
	 */
	public void reanudar(){
		if (player != null) {
			if (!player.isPlaying()) {
				player.start();				
			}		
		}
	}
	
	
	/**
	 * Método que nos dice si el reproductor esta reproduciendo algo
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
	 * Nos dice cual es la canción actual del reproductor
	 * @return la canción actual
	 */
	public int getCancion() {
		return cancion;
	}

}
