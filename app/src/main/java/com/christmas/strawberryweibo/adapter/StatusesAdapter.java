package com.christmas.strawberryweibo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.model.entity.Status;
import com.christmas.strawberryweibo.util.ImageLoadUtil;
import com.christmas.strawberryweibo.util.TimeUtil;
import com.christmas.strawberryweibo.widget.CircleImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main page home fragment public statuses adapter.
 */
public class StatusesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private List<Status> statusList;
  private LayoutInflater layoutInflater;

  public StatusesAdapter(@NonNull Context context, @NonNull List<Status> statusList) {
    this.context = context;
    this.statusList = statusList;
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.layout_status_item, parent, false);
    return new StatusViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    final Status status = statusList.get(position);
    StatusViewHolder statusViewHolder = (StatusViewHolder) viewHolder;
    statusViewHolder.tvStatusPublishTime.setText(TimeUtil.convertDateToTimeFlies(status.createdAt));
    statusViewHolder.tvStatusSource.setText(
        context.getString(R.string.weiboFrom, Html.fromHtml(status.source)));
    statusViewHolder.tvStatusContent.setText(status.text);
    if (status.picUrls != null && status.picUrls.size() > 0) {
      statusViewHolder.rvMiddlePic.setVisibility(View.VISIBLE);
//      ImageLoadUtil.loadImageFromString(
//          context, status.picUrls.get(0).getBMiddlePicUrl(), statusViewHolder.ivMiddlePic);
//      statusViewHolder.ivMiddlePic.setOnClickListener(view ->
//          context.startActivity(
//              PictureActivity.newIntent(context, status.picUrls.get(0).getLargePicUrl())));

      if (status.picUrls.size() == 1) {
        statusViewHolder.rvMiddlePic.setLayoutManager(new GridLayoutManager(context, 1));
      } else if (status.picUrls.size() == 2 || status.picUrls.size() == 4) {
        statusViewHolder.rvMiddlePic.setLayoutManager(new GridLayoutManager(context, 2));
      } else {
        statusViewHolder.rvMiddlePic.setLayoutManager(new GridLayoutManager(context, 3));
      }

      statusViewHolder.rvMiddlePic.setAdapter(new PictureAdapter(context, status.picUrls));

    } else {
      statusViewHolder.rvMiddlePic.setVisibility(View.GONE);
//      statusViewHolder.ivMiddlePic.setOnClickListener(null);
    }

    if (status.user != null) {
      statusViewHolder.tvStatusOwnerName.setText(status.user.name);
      ImageLoadUtil.loadImageFromString(
          context, status.user.avatarLarge, statusViewHolder.ivStatusOwnerAvatar);
    } else {
      // TODO: 6/6/16 add default image.
    }
  }

  @Override
  public int getItemCount() {
    return statusList == null ? 0 : statusList.size();
  }

  public class StatusViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv_status_owner_name) TextView tvStatusOwnerName;
    @Bind(R.id.tv_status_publish_time) TextView tvStatusPublishTime;
    @Bind(R.id.tv_status_source) TextView tvStatusSource;
    @Bind(R.id.tv_status_content) TextView tvStatusContent;
    @Bind(R.id.iv_status_owner_avatar) CircleImageView ivStatusOwnerAvatar;
    @Bind(R.id.iv_show_option) ImageView ivShowOption;
    @Bind(R.id.rv_middle_pic) RecyclerView rvMiddlePic;

    public StatusViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
