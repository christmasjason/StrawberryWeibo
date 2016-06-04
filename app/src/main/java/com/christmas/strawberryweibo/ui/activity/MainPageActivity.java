package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.ui.fragment.HomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainPageActivity extends BaseActivity {

  @Bind(R.id.tv_title) TextView tvTitle;
  @Bind(R.id.tb_toolbar) Toolbar tbToolbar;
  @Bind(R.id.fl_container) FrameLayout flContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ButterKnife.bind(this);

    initHomeFragment();
  }

  @Override
  public int getLayoutRes() {
    return R.layout.layout_main_page;
  }

  private void initHomeFragment() {
    getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.fl_container, HomeFragment.newInstance())
        .commit();

    setTitle("微博首页");
  }

  @NonNull
  public static Intent newIntent(@NonNull Context context) {
    return new Intent(context, MainPageActivity.class);
  }
}
