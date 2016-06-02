package com.christmas.strawberryweibo.Utilities;

import android.content.Context;
import android.widget.Toast;

public class ToastUtility {
  public static void showLongToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }
}
