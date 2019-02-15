package ru.eskendarov.weatherapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

class CView extends View {

  private final Paint paint;
  private final RectF rectF;

  public CView(final Context context) {
    super(context);
    paint = new Paint();
    rectF = new RectF(0, 0, 0, 0);
    paint.setColor(Color.parseColor("#FF4081"));
  }

  @Override
  protected void onSizeChanged(final int w,
                               final int h,
                               final int oldw,
                               final int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    final int partOfWidth = w / 5;
    final int halfWidth = w / 2;
    final int halfHeight = h / 2;
    rectF.set(halfWidth - partOfWidth,
            halfHeight - partOfWidth,
            halfWidth + partOfWidth,
            halfHeight + partOfWidth);
  }

  @Override
  public void invalidate() {
    super.invalidate();
  }

  @Override
  public void draw(final Canvas canvas) {
    super.draw(canvas);
    canvas.drawRoundRect(rectF, 20, 20, paint);
  }
}