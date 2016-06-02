package com.christmas.strawberryweibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainPageActivity extends AppCompatActivity {

  @Bind(R.id.tv) TextView tv;
  @Bind(R.id.btn) Button btn;
  @Bind(R.id.tv_result) TextView tvResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_main_page);
    ButterKnife.bind(this);
  }

  @SuppressWarnings("unused")
  @OnClick(R.id.btn)
  protected void start() {
  }
}
