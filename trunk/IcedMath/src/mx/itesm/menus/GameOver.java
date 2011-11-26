package mx.itesm.menus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class GameOver extends View {

	private Paint paint;

	public GameOver(Context context) {
		super(context);
		paint= new Paint();
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(0x44000000);
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
		paint.setColor(Color.BLUE);
		paint.setTextSize(30);
		canvas.drawText("GAME OVER", canvas.getWidth() / 3,
				canvas.getHeight() / 2, paint);
	}
	
}
	
	