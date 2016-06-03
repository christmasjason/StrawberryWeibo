package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.utility.SharedPreferencesUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainPageActivity extends AppCompatActivity {

  @Bind(R.id.tv_result) TextView tvResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.layout_main_page);

    ButterKnife.bind(this);

    tvResult.setText(String.valueOf(SharedPreferencesUtil.get(this, Oauth2Token.KEY_ACCESS_TOKEN, "")));
  }

  public static Intent newIntent(Context context) {
    return new Intent(context, MainPageActivity.class);
  }
}
