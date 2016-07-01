package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.adapter.FragmentAdapter;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.model.entity.Picture;
import com.christmas.strawberryweibo.presenter.PictureActivityPresenter;
import com.christmas.strawberryweibo.presenter.imp.PictureActivityPresenterImp;
import com.christmas.strawberryweibo.ui.fragment.PictureFragment;
import com.christmas.strawberryweibo.view.PictureActivityView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

public class PictureActivity extends BaseActivity implements
    PictureActivityView,
    PictureFragment.OnPicClickListener {

  @Bind(R.id.vp_image) ViewPager vpImage;
  @Bind(R.id.tb_toolbar) Toolbar tbToolbar;

  public static final String IMAGE_URLS = "imageUrls";
  public static final String CURRENT_INDEX = "currentIndex";

  private List<Picture> imageUrls = new ArrayList<>();
  private int currentIndex;
  private PictureActivityPresenter showImageActivityPresenter;

  @Override
  public int getLayoutRes() {
    return R.layout.layout_picture;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getIntents();

    showImageActivityPresenter = new PictureActivityPresenterImp(this);

    translucentToolbar();

    initFragments();
  }

  private void getIntents() {
    imageUrls = getIntent().getParcelableArrayListExtra(IMAGE_URLS);
    currentIndex = getIntent().getIntExtra(CURRENT_INDEX, 0);
  }

  private void translucentToolbar() {
    tbToolbar.setBackgroundColor(Color.TRANSPARENT);
  }

  private void initFragments() {
    FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

    int size = imageUrls.size();
    for (int index = 0; index < size; index++) {
      PictureFragment pictureFragment = PictureFragment.newInstance(imageUrls.get(index).getLargePicUrl());
      fragmentAdapter.addFragment(pictureFragment, (index + 1) + "/" + size);
      pictureFragment.setPicClickListener(this);
    }

    vpImage.setAdapter(fragmentAdapter);
    vpImage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        setTitle(vpImage.getAdapter().getPageTitle(position));
      }
    });
    vpImage.setCurrentItem(currentIndex);
    setTitle(vpImage.getAdapter().getPageTitle(currentIndex));
  }

  public static Intent newIntent(
      @NonNull Context context, @NonNull List<Picture> imageUrls, @IntRange(from = 0) int currentIndex) {
    Intent intent = new Intent(context, PictureActivity.class);
    intent.putParcelableArrayListExtra(IMAGE_URLS, (ArrayList<Picture>) imageUrls);
    intent.putExtra(CURRENT_INDEX, currentIndex);
    return intent;
  }

  public static Intent newIntent(@NonNull Context context, @NonNull Picture imageUrl) {
    return newIntent(context, Collections.singletonList(imageUrl), 0);
  }

  @Override
  public void saveImageLocalSuccess(String savePath) {

  }

  @Override
  public void onPicClick() {
    finish();
  }
}
