package com.christmas.strawberryweibo.infrastructure;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.christmas.strawberryweibo.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

  private TextView tvTitle;

  @CallSuper
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutRes());

    initToolbar();
  }

  private void initToolbar() {
    Toolbar toolbar = ButterKnife.findById(this, R.id.tb_toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);

      tvTitle = ButterKnife.findById(toolbar, R.id.tv_title);

      if (getSupportActionBar() != null) {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      }

      toolbar.setNavigationOnClickListener(view -> onNavigationBarClick());
    }
  }

  @Override
  protected void onTitleChanged(CharSequence title, int color) {
    super.onTitleChanged(title, color);
    if (tvTitle != null) {
      tvTitle.setText(title);
    }
  }

  public void onNavigationBarClick() {
    onBackPressed();
  }

  public abstract int getLayoutRes();
}
