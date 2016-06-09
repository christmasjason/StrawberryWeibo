package com.christmas.strawberryweibo.infrastructure;

import android.app.Application;

import com.christmas.strawberryweibo.util.Logger;

public class StrawberryApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    Logger.getLogger().i(getClass().getSimpleName() + " started.");
  }
}
