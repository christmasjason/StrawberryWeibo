package com.christmas.strawberryweibo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.api.RetrofitClient;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.util.SharedPreferencesUtil;

public class LaunchPageActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (TextUtils.isEmpty(String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_ACCESS_TOKEN, "")))) {
      startActivity(WebViewActivity.newIntent(this, RetrofitClient.OAUTHORIZE_URL));
    } else {
      startActivity(MainPageActivity.newIntent(this));
      finish();
    }
  }

  @Override
  public int getLayoutRes() {
    return R.layout.layout_launch_page;
  }
}
