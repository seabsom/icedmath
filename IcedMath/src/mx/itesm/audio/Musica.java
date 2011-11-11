package mx.itesm.audio;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Esta clase se encarga de reproducir m�sica en la actividad en la cual es
 * llamada
 * 
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 29/10/2011
 * 
 */
public class Musica {

	private MediaPlayer player;
	private Context contexto;
	private int cancion;

	/**
	 * M�todo constructor de la clase M�sica
	 * 
	 * @param cancion
	 *            Par�metro que indica con que canci�n va a ser creado el objeto
	 * @param contexto
	 *            Par�metro que indica el contexto en el que ser� usado el
	 *            objeto
	 */
	public Musica(int cancion, Context contexto) {
		this.cancion = cancion;
		this.contexto = contexto;
	}

	/**
	 * M�todo que sirve para comenzar a reproducir la m�sica
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
	 * M�todo que sirve para detener la reproducci�n de m�sica
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
