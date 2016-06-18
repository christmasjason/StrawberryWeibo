package com.christmas.strawberryweibo.adapter.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalDivider extends RecyclerView.ItemDecoration {
  private static final int STROKE_WIDTH = 2;

  private Paint paint;

  public VerticalDivider(@ColorInt int dividerColor) {
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(dividerColor);
    paint.setStrokeWidth(STROKE_WIDTH);
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);

    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();

    for (int index = 0; index < parent.getChildCount(); index++) {
      View child = parent.getChildAt(index);
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
      int top = child.getBottom() + layoutParams.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
      c.drawLine(left, top, right, top + STROKE_WIDTH, paint);
    }
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);

    outRect.set(0, 0, 0, STROKE_WIDTH);
  }
}
