package mx.itesm.audio;

import android.content.Context;
import android.media.MediaPlayer;

public class Musica {

	private MediaPlayer player;
	private Context contexto;
	private int cancion;

	public Musica(int cancion, Context contexto) {
		this.cancion = cancion;
		this.contexto = contexto;
	}

	public void play() {
		if (player != null) {
			player.release();
		}
		player = MediaPlayer.create(contexto, cancion);
		player.start();
		player.setLooping(true);
	}

	public void stop() {
		if (player != null) {
			if (player.isPlaying()) {
				player.stop();
				player.release();
			}
		}
	}

}
