package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.ui.fragment.HomeFragment;

import butterknife.Bind;

public class MainPageActivity extends BaseActivity implements
    NavigationView.OnNavigationItemSelectedListener {

  @Bind(R.id.tv_title) TextView tvTitle;
  @Bind(R.id.tb_toolbar) Toolbar tbToolbar;
  @Bind(R.id.fl_container) FrameLayout flContainer;
  @Bind(R.id.nv_menu) NavigationView nvMenu;
  @Bind(R.id.dl_main_page) DrawerLayout dlMainPage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initDrawerLayoutAndNavigationView();

    initHomeFragment();
  }

  @Override
  public int getLayoutRes() {
    return R.layout.layout_main_page;
  }

  private void initDrawerLayoutAndNavigationView() {
    ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, dlMainPage, tbToolbar, R.string.openDrawerDesc, R.string.closeDrawerDesc);
    actionBarDrawerToggle.syncState();
    dlMainPage.addDrawerListener(actionBarDrawerToggle);
  }

  private void initHomeFragment() {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fl_container, HomeFragment.newInstance())
        .commit();

    setTitle("微博首页");
  }

  @NonNull
  public static Intent newIntent(@NonNull Context context) {
    return new Intent(context, MainPageActivity.class);
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    return false;
  }
}
