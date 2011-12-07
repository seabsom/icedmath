package mx.itesm.menus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * Esta clase se encarga de mostrar la pantalla "Galeria" (opción del menú
 * "Extras")
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 05/10/2011
 * 
 */
public class Galeria extends Activity {

	private final Integer[] pics = { R.drawable.extra1, R.drawable.extra2, R.drawable.extra3,
			R.drawable.extra4, R.drawable.extra5, R.drawable.extra6,
			R.drawable.extra7, R.drawable.extra8, R.drawable.extra9,
			R.drawable.extra10 };
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Este bloque quita el nombre de la aplicación
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.galeria);

		Gallery ga = (Gallery) findViewById(R.id.Gallery01);
		ga.setAdapter(new ImageAdapter(this));

		imageView = (ImageView) findViewById(R.id.ImageView01);	
		
		
		ga.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int indice,
					long arg3) {
				Toast.makeText(
						getBaseContext(),
						"Seleccionaste la imagen " + (indice + 1)
								+ " de Antartica", Toast.LENGTH_SHORT).show();
				imageView.setImageResource(pics[indice]);

			}

		});

	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intencion = new Intent(this, Extras.class);
			startActivity(intencion);
			finish();
		}
		return true;

	}

	private class ImageAdapter extends BaseAdapter {

		private Context ctx;
		int imageBackground;

		public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
		public int getCount() {
			return pics.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv = new ImageView(ctx);

			iv.setImageResource(pics[position]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(100, 65));
			iv.setBackgroundResource(imageBackground);

			return iv;
		}

	}
	
	

}
