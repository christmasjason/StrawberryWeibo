package com.christmas.strawberryweibo.adapter.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridDivider extends RecyclerView.ItemDecoration {
  private static final int STROKE_WIDTH = 10;

  private Paint paint;
  private int strokeWidth = STROKE_WIDTH;

  public GridDivider() {
    this(-1);
  }

  public GridDivider(@ColorInt int dividerColor) {
    this(dividerColor, STROKE_WIDTH);
  }

  public GridDivider(@ColorInt int dividerColor, int strokeWidth) {
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(dividerColor);
    paint.setStrokeWidth(strokeWidth);
    this.strokeWidth = strokeWidth;
  }

  // RecyclerView vertical, horizontal divider.
  private void drawVertical(Canvas c, RecyclerView parent) {
    int childCount = parent.getChildCount();
    for (int index = 0; index < childCount; index++) {
      View child = parent.getChildAt(index);
      RecyclerView.LayoutParams layoutParams =
          (RecyclerView.LayoutParams) child.getLayoutParams();
      int left = child.getLeft() - layoutParams.leftMargin;
      int top = child.getBottom() + layoutParams.bottomMargin;
      int right = child.getRight() + layoutParams.rightMargin + strokeWidth;
      c.drawLine(left, top, right, top, paint);
    }
  }

  // RecyclerView horizontal, vertical divider.
  private void drawHorizontal(Canvas c, RecyclerView parent) {
    int childCount = parent.getChildCount();
    for (int index = 0; index < childCount; index++) {
      View child = parent.getChildAt(index);
      RecyclerView.LayoutParams layoutParams =
          (RecyclerView.LayoutParams) child.getLayoutParams();
      int left = child.getRight() + layoutParams.rightMargin;
      int top = child.getTop() - layoutParams.topMargin;
      int bottom = child.getBottom() + layoutParams.bottomMargin;
      c.drawLine(left, top, left, bottom, paint);
    }
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);

//    drawVertical(c, parent);
//    drawHorizontal(c, parent);
  }

  private int getSpanCount(RecyclerView parent) {
    int spanCount = -1;
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
    }

    return spanCount;
  }

  private boolean isLastColumn(RecyclerView parent, int position, int spanCount, int childCount) {
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      if (((GridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
        return ((position + 1) % spanCount) == 0;
      } else {
        int lastColumnItemCount = childCount % spanCount;
        lastColumnItemCount = lastColumnItemCount == 0 ? spanCount : lastColumnItemCount;
        return position >= (childCount - lastColumnItemCount);
      }
    }
    return false;
  }

  private boolean isLastRow(RecyclerView parent, int position, int spanCount, int childCount) {
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof GridLayoutManager) {
      if (((GridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
        int lastRowItemCount = childCount % spanCount;
        lastRowItemCount = lastRowItemCount == 0 ? spanCount : lastRowItemCount;
        return position >= (childCount - lastRowItemCount);
      } else {
        return ((position + 1) % spanCount) == 0;
      }
    }
    return false;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);

    int spanCount = getSpanCount(parent);
    int childCount = parent.getAdapter().getItemCount();
    int itemPosition = parent.getChildAdapterPosition(view);

    outRect.set(0, 0, strokeWidth, strokeWidth);

    if (isLastRow(parent, itemPosition, spanCount, childCount)) {
      outRect.bottom = 0;
    }
    if (isLastColumn(parent, itemPosition, spanCount, childCount)) {
      outRect.right = 0;
    }
  }
}
