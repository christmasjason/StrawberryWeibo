package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.model.entity.User;
import com.christmas.strawberryweibo.presenter.MainPageActivityPresenter;
import com.christmas.strawberryweibo.presenter.imp.MainPageActivityPresenterImp;
import com.christmas.strawberryweibo.ui.fragment.HomeFragment;
import com.christmas.strawberryweibo.util.ImageLoadUtil;
import com.christmas.strawberryweibo.util.SharedPreferencesUtil;
import com.christmas.strawberryweibo.view.MainPageActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainPageActivity extends BaseActivity implements
    NavigationView.OnNavigationItemSelectedListener,
    MainPageActivityView {

  @Bind(R.id.tv_title) TextView tvTitle;
  @Bind(R.id.tb_toolbar) Toolbar tbToolbar;
  @Bind(R.id.fl_container) FrameLayout flContainer;
  @Bind(R.id.nv_menu) NavigationView nvMenu;
  @Bind(R.id.dl_main_page) DrawerLayout dlMainPage;

  private ImageView ivAvatar;
  private TextView tvUserName;
  private TextView tvMotto;

  private MainPageActivityPresenter mainPageActivityPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initViews();

    initHomeFragment();

    mainPageActivityPresenter = new MainPageActivityPresenterImp(this);

    if (TextUtils.isEmpty(String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_UID, "")))) {
      mainPageActivityPresenter.getTokenInfo(
          String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_ACCESS_TOKEN, "")));
    } else {
      mainPageActivityPresenter.getUserInfo(
          String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_ACCESS_TOKEN, "")),
          String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_UID, "")));
    }
  }

  @Override
  public int getLayoutRes() {
    return R.layout.layout_main_page;
  }

  private void initViews() {
    ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, dlMainPage, tbToolbar, R.string.openDrawerDesc, R.string.closeDrawerDesc);
    actionBarDrawerToggle.syncState();
    dlMainPage.addDrawerListener(actionBarDrawerToggle);

    ivAvatar = ButterKnife.findById(nvMenu.getHeaderView(0), R.id.iv_avatar);
    tvUserName = ButterKnife.findById(nvMenu.getHeaderView(0), R.id.tv_user_name);
    tvMotto = ButterKnife.findById(nvMenu.getHeaderView(0), R.id.tv_motto);
  }

  private void initHomeFragment() {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fl_container, HomeFragment.newInstance())
        .commit();

    setTitle(R.string.mainPageName);
  }

  @NonNull
  public static Intent newIntent(@NonNull Context context) {
    return new Intent(context, MainPageActivity.class);
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    return false;
  }

  @Override
  public void updateUserTokenInfo(Oauth2Token oauth2Token) {
    mainPageActivityPresenter.getUserInfo(
        String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_ACCESS_TOKEN, "")), oauth2Token.uid);
  }

  @Override
  public void updateUserInfo(User user) {
    ImageLoadUtil.loadImageFromString(this, user.avatarLarge, ivAvatar);
    tvUserName.setText(user.screenName);
    if (TextUtils.isEmpty(user.description)) {
      tvMotto.setText(R.string.mottoDefault);
    } else {
      tvMotto.setText(user.description);
    }
  }
}
