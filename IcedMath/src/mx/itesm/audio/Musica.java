package mx.itesm.audio;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Esta clase se encarga de reproducir música en la actividad en la cual es
 * llamada
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 29/10/2011
 * 
 */
public class Musica {

	private MediaPlayer player;
	private Context contexto;
	private int cancion;

	/**
	 * Método constructor de la clase Música
	 * 
	 * @param cancion
	 *            Parámetro que indica con que canción va a ser creado el objeto
	 * @param contexto
	 *            Parámetro que indica el contexto en el que será usado el
	 *            objeto
	 */
	public Musica(int cancion, Context contexto) {
		this.cancion = cancion;
		this.contexto = contexto;
	}

	/**
	 * Método que sirve para comenzar a reproducir la música
	 */
	public void play() {
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
	public void stop() {
		if (player != null) {
			if (player.isPlaying()) {
				player.stop();
				player.release();
			}
		}
	}

}
