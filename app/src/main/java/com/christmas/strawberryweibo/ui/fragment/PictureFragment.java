package com.christmas.strawberryweibo.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.presenter.PictureFragmentPresenter;
import com.christmas.strawberryweibo.presenter.imp.PictureFragmentPresenterImp;
import com.christmas.strawberryweibo.view.PictureFragmentView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureFragment extends Fragment implements PictureFragmentView {
  @Bind(R.id.iv_picture) ImageView ivPicture;

  private static final String IMAGE_URL = "imageUrl";

  private String imageUrl;
  private PictureFragmentPresenter pictureFragmentPresenter;
  private OnPicClickListener onPicClickListener;

  public static PictureFragment newInstance(@NonNull String imageUrl) {
    PictureFragment pictureFragment = new PictureFragment();
    Bundle bundle = new Bundle();
    bundle.putString(IMAGE_URL, imageUrl);
    pictureFragment.setArguments(bundle);
    return pictureFragment;
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.layout_picture_fragment, container, false);

    ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getArgument();

    pictureFragmentPresenter = new PictureFragmentPresenterImp(this);

    pictureFragmentPresenter.loadImage(getContext(), imageUrl);
  }

  private void getArgument() {
    imageUrl = getArguments().getString(IMAGE_URL);
  }

  @Override
  public void pictureLoadSuccess(@NonNull Bitmap bitmap) {
    ivPicture.setImageBitmap(bitmap);
  }

  @OnClick(R.id.iv_picture)
  protected void clickPicture() {
    if (onPicClickListener != null) {
      onPicClickListener.onPicClick();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void setPicClickListener(@NonNull OnPicClickListener onPicClickListener) {
    this.onPicClickListener = onPicClickListener;
  }

  public interface OnPicClickListener {
    void onPicClick();
  }
}
