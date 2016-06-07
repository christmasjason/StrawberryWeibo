package com.christmas.strawberryweibo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.adapter.StatusesAdapter;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.model.entity.Status;
import com.christmas.strawberryweibo.model.entity.StatusListWrapper;
import com.christmas.strawberryweibo.presenter.HomeFragmentPresenter;
import com.christmas.strawberryweibo.presenter.imp.HomeFragmentPresenterImp;
import com.christmas.strawberryweibo.util.SharedPreferencesUtil;
import com.christmas.strawberryweibo.view.HomeFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeFragmentView {
  @Bind(R.id.rv_public_statuses) RecyclerView rvPublicStatuses;

  private StatusesAdapter publicStatusesAdapter;
  private List<Status> statusList = new ArrayList<>();
  private StatusListWrapper statusListWrapper;

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.layout_home_fragment, container, false);

    ButterKnife.bind(this, rootView);

    HomeFragmentPresenter homeFragmentPresenter = new HomeFragmentPresenterImp(this);
    homeFragmentPresenter.startLoadFriendsStatuses(
        String.valueOf(SharedPreferencesUtil.get(getActivity(), Oauth2Token.KEY_ACCESS_TOKEN, "")));

    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initPublicStatusAdapter();
  }

  private void initPublicStatusAdapter() {
    publicStatusesAdapter = new StatusesAdapter(getContext(), statusList);
    rvPublicStatuses.setLayoutManager(new LinearLayoutManager(getContext()));
    rvPublicStatuses.setAdapter(publicStatusesAdapter);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void refreshPublicStatuses(@NonNull StatusListWrapper statusListWrapper) {
    this.statusListWrapper = statusListWrapper;
    this.statusList.addAll(statusListWrapper.statuses);
    publicStatusesAdapter.notifyDataSetChanged();
  }

  @Override
  public void emptyPublicStatuses() {

  }
}
