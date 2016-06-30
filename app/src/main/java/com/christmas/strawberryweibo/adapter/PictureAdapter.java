package com.christmas.strawberryweibo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.christmas.strawberryweibo.model.entity.Picture;
import com.christmas.strawberryweibo.ui.activity.PictureActivity;
import com.christmas.strawberryweibo.util.ImageLoadUtil;
import com.christmas.strawberryweibo.util.ScreenUtil;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  public static final int SINGLE_PIC_VIEW_TYPE = 100;
  public static final int TWO_FOUR_PIC_VIEW_TYPE = 101;
  public static final int OTHER_COUNT_PIC_VIEW_TYPE = 102;

  private Context context;
  private List<Picture> pictureList;
  private int screenWidth;

  public PictureAdapter(Context context, List<Picture> pictureList) {
    this.context = context;
    this.pictureList = pictureList;
    this.screenWidth = ScreenUtil.getScreenWidth((Activity) context);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == SINGLE_PIC_VIEW_TYPE) {
      return new PictureViewHolder(new ImageView(context), ((screenWidth - 50) * 2) / 3, ((screenWidth - 50) * 2) / 3);
    } else if (viewType == TWO_FOUR_PIC_VIEW_TYPE) {
      return new PictureViewHolder(new ImageView(context), (screenWidth - 50) / 2, (screenWidth - 50) / 2);
    } else {
      return new PictureViewHolder(new ImageView(context), (screenWidth - 50) / 3, (screenWidth - 50) / 3);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    PictureViewHolder pictureViewHolder = (PictureViewHolder) viewHolder;
    ImageLoadUtil.loadImageFromString(
        context, pictureList.get(position).getBMiddlePicUrl(), pictureViewHolder.imageView);
    pictureViewHolder.imageView.setOnClickListener(view ->
        context.startActivity(PictureActivity.newIntent(context, pictureList.get(position).getLargePicUrl())));
  }

  @Override
  public int getItemViewType(int position) {
    if (pictureList.size() == 1) {
      return SINGLE_PIC_VIEW_TYPE;
    } else if (pictureList.size() == 2 || pictureList.size() == 4) {
      return TWO_FOUR_PIC_VIEW_TYPE;
    } else {
      return OTHER_COUNT_PIC_VIEW_TYPE;
    }
  }

  @Override
  public int getItemCount() {
    return pictureList == null ? 0 : pictureList.size();
  }

  public class PictureViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public PictureViewHolder(View itemView, int width, int height) {
      super(itemView);

      imageView = (ImageView) itemView;
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(width, height);
      imageView.setLayoutParams(layoutParams);
    }
  }
}
