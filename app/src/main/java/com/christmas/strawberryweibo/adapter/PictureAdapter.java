package com.christmas.strawberryweibo.adapter;

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

  public PictureAdapter(Context context, List<Picture> pictureList) {
    this.context = context;
    this.pictureList = pictureList;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == SINGLE_PIC_VIEW_TYPE) {
      return new SinglePicViewHolder(new ImageView(context));
    } else if (viewType == TWO_FOUR_PIC_VIEW_TYPE) {
      return new TwoFourPicViewHolder(new ImageView(context));
    } else {
      return new OtherCountPicViewHolder(new ImageView(context));
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    if (viewHolder instanceof SinglePicViewHolder) {
      SinglePicViewHolder singlePicViewHolder = (SinglePicViewHolder) viewHolder;
      ImageLoadUtil.loadImageFromString(
          context, pictureList.get(position).getBMiddlePicUrl(), singlePicViewHolder.imageViewSingle);
      singlePicViewHolder.imageViewSingle.setOnClickListener(
          view -> PictureActivity.newIntent(context, pictureList.get(position).getLargePicUrl()));

    } else if (viewHolder instanceof TwoFourPicViewHolder) {
      TwoFourPicViewHolder twoFourPicViewHolder = (TwoFourPicViewHolder) viewHolder;
      ImageLoadUtil.loadImageFromString(
          context, pictureList.get(position).getBMiddlePicUrl(), twoFourPicViewHolder.imageViewTwoFour);
      twoFourPicViewHolder.imageViewTwoFour.setOnClickListener(
          view -> PictureActivity.newIntent(context, pictureList.get(position).getLargePicUrl()));

    } else if (viewHolder instanceof OtherCountPicViewHolder) {
      OtherCountPicViewHolder otherCountPicViewHolder = (OtherCountPicViewHolder) viewHolder;
      ImageLoadUtil.loadImageFromString(
          context, pictureList.get(position).getBMiddlePicUrl(), otherCountPicViewHolder.imageViewOther);
      otherCountPicViewHolder.imageViewOther.setOnClickListener(
          view -> PictureActivity.newIntent(context, pictureList.get(position).getLargePicUrl()));

    }
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

  public class SinglePicViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewSingle;

    public SinglePicViewHolder(View itemView) {
      super(itemView);

      imageViewSingle = (ImageView) itemView;
      imageViewSingle.setScaleType(ImageView.ScaleType.CENTER_CROP);
      RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
          ScreenUtil.dp2px(context, 180), ScreenUtil.dp2px(context, 220));
      imageViewSingle.setLayoutParams(layoutParams);
    }
  }

  public class TwoFourPicViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewTwoFour;

    public TwoFourPicViewHolder(View itemView) {
      super(itemView);

      imageViewTwoFour = (ImageView) itemView;
      imageViewTwoFour.setScaleType(ImageView.ScaleType.CENTER_CROP);
      RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
          ScreenUtil.dp2px(context, 180), ScreenUtil.dp2px(context, 180));
      imageViewTwoFour.setLayoutParams(layoutParams);
    }
  }

  public class OtherCountPicViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewOther;

    public OtherCountPicViewHolder(View itemView) {
      super(itemView);

      imageViewOther = (ImageView) itemView;
      imageViewOther.setScaleType(ImageView.ScaleType.CENTER_CROP);
      RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
          ScreenUtil.dp2px(context, 120), ScreenUtil.dp2px(context, 120));
      imageViewOther.setLayoutParams(layoutParams);
    }
  }
}
