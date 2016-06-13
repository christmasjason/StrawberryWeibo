package com.christmas.strawberryweibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
  private List<Fragment> fragmentList = new ArrayList<Fragment>();
  private List<String> titleList = new ArrayList<String>();

  public FragmentAdapter(FragmentManager fm) {
    super(fm);
  }

  public void addFragment(Fragment fragment, String title) {
    fragmentList.add(fragment);
    titleList.add(title);
  }

  public void addFragment(Fragment fragment) {
    addFragment(fragment, fragment.getClass().getSimpleName());
  }

  @Override
  public Fragment getItem(int position) {
    return fragmentList.get(position);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titleList.get(position);
  }

  @Override
  public int getCount() {
    return fragmentList.size();
  }

  public void clear() {
    fragmentList.clear();
    titleList.clear();
  }
}
