package com.christmas.strawberryweibo.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtil {

  public static int dp2px(@NonNull Context context, int dp) {
    return dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }

  public static int getScreenWidth(@NonNull Activity context) {
    DisplayMetrics displayMetrics = getDisplayMetrics(context);
    return displayMetrics.widthPixels;
  }

  public static int getScreenHeight(@NonNull Activity context) {
    DisplayMetrics displayMetrics = getDisplayMetrics(context);
    return displayMetrics.heightPixels;
  }

  @NonNull
  private static DisplayMetrics getDisplayMetrics(@NonNull Activity context) {
    Display display = context.getWindowManager().getDefaultDisplay();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
    return displayMetrics;
  }
}
