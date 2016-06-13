package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.presenter.PictureActivityPresenter;
import com.christmas.strawberryweibo.presenter.imp.PictureActivityPresenterImp;
import com.christmas.strawberryweibo.view.PictureActivityView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PictureActivity extends BaseActivity implements PictureActivityView {
  @Bind(R.id.vp_image) ViewPager vpImage;

  public static final String IMAGE_URLS = "imageUrls";

  private ArrayList<String> imageUrls;
  private PictureActivityPresenter showImageActivityPresenter;

  @Override
  public int getLayoutRes() {
    return R.layout.layout_show_image;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getIntents();

    initViews();

    showImageActivityPresenter = new PictureActivityPresenterImp(this);
  }

  private void getIntents() {
    imageUrls = getIntent().getStringArrayListExtra(IMAGE_URLS);
  }

  private void initViews() {
    ButterKnife.bind(this);
  }

  public static Intent newIntent(@NonNull Context context, @NonNull ArrayList<String> imageUrls) {
    Intent intent = new Intent(context, PictureActivity.class);
    intent.putStringArrayListExtra(IMAGE_URLS, imageUrls);
    return intent;
  }

  @Override
  public void saveImageLocalSuccess(String savePath) {

  }
}
