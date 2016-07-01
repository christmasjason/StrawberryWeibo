package com.christmas.strawberryweibo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class Picture implements Parcelable {
  @SerializedName("thumbnail_pic") public String thumbnailPic;
  public String bMiddlePic;
  public String largePic;

  public final String THUMBNAIL = "thumbnail";
  public final String BMIDDLE = "bmiddle";
  public final String LARGE = "large";

  protected Picture(Parcel in) {
    thumbnailPic = in.readString();
  }

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(thumbnailPic);
  }

  public static final Creator<Picture> CREATOR = new Creator<Picture>() {
    @Override
    public Picture createFromParcel(Parcel in) {
      return new Picture(in);
    }

    @Override
    public Picture[] newArray(int size) {
      return new Picture[size];
    }
  };
}
