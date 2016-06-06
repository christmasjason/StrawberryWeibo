package com.christmas.strawberryweibo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.model.entity.Status;
import com.christmas.strawberryweibo.util.ImageLoadUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main page home fragment public statuses adapter.
 */
public class PublicStatusesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private List<Status> statusList;
  private LayoutInflater layoutInflater;

  public PublicStatusesAdapter(@NonNull Context context, @NonNull List<Status> statusList) {
    this.context = context;
    this.statusList = statusList;
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.layout_public_status_item, parent, false);
    return new PublicStatusViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    Status status = statusList.get(position);
    PublicStatusViewHolder publicStatusViewHolder = (PublicStatusViewHolder) viewHolder;
    publicStatusViewHolder.tvStatusPublishTime.setText(status.createdAt);

    if (status.user != null) {
      publicStatusViewHolder.tvStatusOwnerName.setText(status.user.name);
      ImageLoadUtil.loadImageFromString(
          context, status.user.profileImageUrl, publicStatusViewHolder.ivStatusOwnerAvatar);
    } else {
      // TODO: 6/6/16 add default image.
    }
  }

  @Override
  public int getItemCount() {
    return statusList == null ? 0 : statusList.size();
  }

  public class PublicStatusViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.iv_status_owner_avatar) ImageView ivStatusOwnerAvatar;
    @Bind(R.id.tv_status_owner_name) TextView tvStatusOwnerName;
    @Bind(R.id.tv_status_publish_time) TextView tvStatusPublishTime;
    @Bind(R.id.tv_status_from_device) TextView tvStatusFromDevice;
    @Bind(R.id.iv_show_option) ImageView ivShowOption;

    public PublicStatusViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
