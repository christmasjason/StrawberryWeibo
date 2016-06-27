package com.christmas.strawberryweibo.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class ScreenUtil {

  public static int dp2px(@NonNull Context context, int dp) {
    return dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }
}
