package com.christmas.strawberryweibo.model.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class Picture {
  @SerializedName("thumbnail_pic") public String thumbnailPic;
  public String bMiddlePic;
  public String largePic;

  public final String THUMBNAIL = "thumbnail";
  public final String BMIDDLE = "bmiddle";
  public final String LARGE = "large";

  public String getThumbnailPicUrl() {
    return thumbnailPic;
  }

  public String getBMiddlePicUrl() {
    if (!TextUtils.isEmpty(bMiddlePic)) {
      return bMiddlePic;
    } else {
      bMiddlePic = thumbnailPic.replace(THUMBNAIL, BMIDDLE);
      return bMiddlePic;
    }
  }

  public String getLargePicUrl() {
    if (!TextUtils.isEmpty(largePic)) {
      return largePic;
    } else {
      largePic = thumbnailPic.replace(THUMBNAIL, LARGE);
      return largePic;
    }
  }
}
