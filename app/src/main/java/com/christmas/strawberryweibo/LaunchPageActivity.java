package com.christmas.strawberryweibo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.christmas.strawberryweibo.apis.RetrofitUtility;
import com.christmas.strawberryweibo.infrastructure.Constants;
import com.christmas.strawberryweibo.uis.activities.WebViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LaunchPageActivity extends AppCompatActivity {
  @Bind(R.id.btn_authorize) Button btnAuthorize;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.layout_launch_page);

    ButterKnife.bind(this);

    btnAuthorize.setOnClickListener(view -> {
      String actionUrl = RetrofitUtility.BASE_URL
          + "oauth2/authorize"
          + "?client_id="
          + Constants.APP_KEY
          + "&redirect_uri="
          + Constants.REDIRECT_URI;

      startActivity(WebViewActivity.newIntent(this, actionUrl));
    });
  }
}
