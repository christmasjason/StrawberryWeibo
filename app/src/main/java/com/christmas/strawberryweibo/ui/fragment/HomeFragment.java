package com.christmas.strawberryweibo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class HomeFragment extends Fragment implements
    HomeFragmentView,
    SwipeRefreshLayout.OnRefreshListener {
  @Bind(R.id.rv_public_statuses) RecyclerView rvPublicStatuses;
  @Bind(R.id.srl_statuses_wrapper) SwipeRefreshLayout srlStatusesWrapper;

  private StatusesAdapter publicStatusesAdapter;
  private List<Status> statusList = new ArrayList<>();
  private StatusListWrapper statusListWrapper;
  private HomeFragmentPresenter homeFragmentPresenter;
  private boolean loading = false;
  private int currentPage = 1;

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.layout_home_fragment, container, false);

    ButterKnife.bind(this, rootView);

    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    homeFragmentPresenter = new HomeFragmentPresenterImp(this);
    initPublicStatusAdapter();
    srlStatusesWrapper.setOnRefreshListener(this);
    onRefresh();
  }

  private void initPublicStatusAdapter() {
    publicStatusesAdapter = new StatusesAdapter(getContext(), statusList);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    rvPublicStatuses.setLayoutManager(linearLayoutManager);
    rvPublicStatuses.setAdapter(publicStatusesAdapter);
    rvPublicStatuses.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // Scroll down.
        if (dy > 0) {
          int totalItemCount = linearLayoutManager.getItemCount();
          int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
          int visibleItemCount = linearLayoutManager.getChildCount();
          if (!loading && ((visibleItemCount + firstVisiblePosition) >= totalItemCount)) {
            loading = true;
            homeFragmentPresenter.loadFriendsStatuses(
                String.valueOf(SharedPreferencesUtil.get(getActivity(), Oauth2Token.KEY_ACCESS_TOKEN, "")), currentPage);
          }
        }
      }
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void updateStatuses(@NonNull StatusListWrapper statusListWrapper) {
    loading = false;
    if (srlStatusesWrapper != null) {
      srlStatusesWrapper.setRefreshing(false);
    }
    currentPage++;
    this.statusListWrapper = statusListWrapper;
    this.statusList.addAll(statusListWrapper.statuses);
    publicStatusesAdapter.notifyDataSetChanged();
  }

  @Override
  public void emptyStatuses() {
    loading = false;
  }

  @Override
  public void onRefresh() {
    loading = true;
    currentPage = 1;
    this.statusList.clear();
    homeFragmentPresenter.loadFriendsStatuses(
        String.valueOf(SharedPreferencesUtil.get(getActivity(), Oauth2Token.KEY_ACCESS_TOKEN, "")), currentPage);
  }
}
