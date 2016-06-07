package com.christmas.strawberryweibo.util;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoadUtil {
  public static void loadImageFromString(
      @NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
    Picasso
        .with(context)
        .load(url)
        .into(imageView);
  }

  public static void loadImageFromResId(
      @NonNull Context context, @IdRes int resId, @NonNull ImageView imageView) {
    Picasso
        .with(context)
        .load(resId)
        .into(imageView);
  }
}
