package com.christmas.strawberryweibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.christmas.strawberryweibo.model.entity.Picture;
import com.christmas.strawberryweibo.util.ImageLoadUtil;
import com.christmas.strawberryweibo.util.ScreenUtil;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  public static final int SINGLE_PICTURE = 100;
  public static final int TWO_FOUR_PICTURE = 101;
  public static final int OTHER_COUNT_PICTURE = 102;

  private Context context;
  private List<Picture> imageUrls;

  public PictureAdapter(Context context, List<Picture> imageUrls) {
    this.context = context;
    this.imageUrls = imageUrls;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == SINGLE_PICTURE) {
      return new SinglePictureViewHolder(new ImageView(parent.getContext()));
    } else if (viewType == TWO_FOUR_PICTURE) {
      return new TwoFourPictureViewHolder(new ImageView(parent.getContext()));
    } else {
      return new OtherCountPictureViewHolder(new ImageView(parent.getContext()));
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    if (viewHolder instanceof SinglePictureViewHolder) {
      SinglePictureViewHolder singlePictureViewHolder = (SinglePictureViewHolder) viewHolder;
      ImageLoadUtil.loadImageFromString(context,
          imageUrls.get(position).thumbnailPic, singlePictureViewHolder.imageView);
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (imageUrls != null) {
      if (imageUrls.size() == 1) {
        return SINGLE_PICTURE;
      } else if (imageUrls.size() == 2 || imageUrls.size() == 4) {
        return TWO_FOUR_PICTURE;
      } else {
        return OTHER_COUNT_PICTURE;
      }
    }

    return super.getItemViewType(position);
  }

  @Override
  public int getItemCount() {
    return imageUrls == null ? 0 : imageUrls.size();
  }

  public class SinglePictureViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public SinglePictureViewHolder(View itemView) {
      super(itemView);

      imageView = (ImageView) itemView;
      RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(context, 220));
      imageView.setLayoutParams(layoutParams);
    }
  }

  public class TwoFourPictureViewHolder extends RecyclerView.ViewHolder {

    public TwoFourPictureViewHolder(View itemView) {
      super(itemView);
    }
  }

  public class OtherCountPictureViewHolder extends RecyclerView.ViewHolder {

    public OtherCountPictureViewHolder(View itemView) {
      super(itemView);
    }
  }
}
