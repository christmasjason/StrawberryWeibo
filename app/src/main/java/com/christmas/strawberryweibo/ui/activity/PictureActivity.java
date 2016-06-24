package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.adapter.FragmentAdapter;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.presenter.PictureActivityPresenter;
import com.christmas.strawberryweibo.presenter.imp.PictureActivityPresenterImp;
import com.christmas.strawberryweibo.ui.fragment.PictureFragment;
import com.christmas.strawberryweibo.view.PictureActivityView;

import java.util.ArrayList;

import butterknife.Bind;

public class PictureActivity extends BaseActivity implements
    PictureActivityView,
    PictureFragment.OnPicClickListener {
  @Bind(R.id.vp_image) ViewPager vpImage;

  public static final String IMAGE_URLS = "imageUrls";

  private ArrayList<String> imageUrls;
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

    initFragments();
  }

  private void getIntents() {
    imageUrls = getIntent().getStringArrayListExtra(IMAGE_URLS);
  }

  private void initFragments() {
    FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

    int size = imageUrls.size();
    for (int index = 0; index < size; index++) {
      PictureFragment pictureFragment = PictureFragment.newInstance(imageUrls.get(index));
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
    vpImage.setCurrentItem(0);
  }

  public static Intent newIntent(@NonNull Context context, @NonNull ArrayList<String> imageUrls) {
    Intent intent = new Intent(context, PictureActivity.class);
    intent.putStringArrayListExtra(IMAGE_URLS, imageUrls);
    return intent;
  }

  public static Intent newIntent(@NonNull Context context, @NonNull String imageUrl) {
    ArrayList<String> list = new ArrayList<>();
    list.add(imageUrl);
    return newIntent(context, list);
  }

  @Override
  public void saveImageLocalSuccess(String savePath) {

  }

  @Override
  public void onPicClick() {
    finish();
  }
}
