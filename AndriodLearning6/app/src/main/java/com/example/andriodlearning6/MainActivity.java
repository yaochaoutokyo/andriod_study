package com.example.andriodlearning6;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

	// 控制缩放
	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();

	// 控制模式
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;
	private int mode = NONE;

	// 抓取触控点
	private PointF startPointF = new PointF();
	private PointF endPointF = new PointF();
	private PointF middlePointF = new PointF();
	private float distance = 1f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative_layout);

		ImageView imageView = findViewById(R.id.imageView1);
		imageView.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;
		switch (event.getAction() & MotionEvent.ACTION_MASK ) {

			case MotionEvent.ACTION_DOWN:
				Log.i("test", "MotionEvent.ACTION_DOWN");
				matrix .set(view.getImageMatrix());
				savedMatrix.set(matrix);
				startPointF.set(event.getX(), event.getY());
				mode = DRAG;
				break;

			case MotionEvent.ACTION_POINTER_DOWN:
				Log.i("test", "MotionEvent.ACTION_POINTER_DOWN");
				distance = getDistance(event);
				if (distance > 10f) {
					savedMatrix.set(matrix);
					middlePointF = getMiddlePoint(event);
					mode = ZOOM;
				}
				endPointF.set(event.getX(), event.getY());
				mode = ZOOM;
				break;

			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_UP:
				Log.i("test", "MotionEvent.ACTION_POINTER_UP & ACTION_UP");
				mode = NONE;
				break;

			case MotionEvent.ACTION_MOVE:
				Log.i("test", "MotionEvent.ACTION_MOVE");
				switch (mode) {
					case DRAG:
						matrix.set(savedMatrix);
						matrix.postTranslate(event.getX() - startPointF.x, event.getY() - startPointF.y);
						break;

					case ZOOM:
						float newDistance = getDistance(event);
						float scale = newDistance / distance;
						matrix.set(savedMatrix);
						matrix.postScale(scale, scale, middlePointF.x, middlePointF.y);
						break;

					case NONE:
					default:
						break;
				}
				break;

			default:
				break;
		}

		// 设置新的图像矩阵
		view.setImageMatrix(matrix);
		return true;
	}

	public float getDistance(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return (float) Math.sqrt(x*x + y*y);
	}

	public PointF getMiddlePoint(MotionEvent event) {
		float x = event.getX(0) + event.getY(1);
		float y = event.getY(0) - event.getY(1);
		return new PointF(x/2, y/2);
	}
}
