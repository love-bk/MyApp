package gjj.com.myapp.myreply.views;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.HomeActivity;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MvpFragment;
import gjj.com.myapp.model.ReplyGroup;
import gjj.com.myapp.myreply.adapter.ReplyRecyclerViewAdapter;
import gjj.com.myapp.presenter.ReplyPresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.ReplyView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplyFragment extends MvpFragment<ReplyPresenter> implements SwipeRefreshLayout.OnRefreshListener,ReplyView{


    @BindView(R.id.replyRecyclerView)
    RecyclerView mReplyRecyclerView;
    @BindView(R.id.reply_swipe_refresh)
    SwipeRefreshLayout mReplySwipeRefresh;
    private ReplyRecyclerViewAdapter mAdapter;

    public ReplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_defense_rating, container, false);
        ButterKnife.bind(this, view);
        initData();
        initWidget();

        return view;
    }

    private void initWidget() {
        mReplyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mReplyRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ReplyRecyclerViewAdapter(getActivity());
        mAdapter.setOnItemClickListener(new ReplyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position,ReplyGroup replyGroup) {

                Intent intent = new Intent(getActivity(), ReplyDetailActivity.class);
                intent.putExtra(Constants.REPLYGROUPID,replyGroup.getId());
                startActivity(intent);
            }
        });
        mReplyRecyclerView.setAdapter(mAdapter);
        mReplySwipeRefresh.setOnRefreshListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void initData() {

    }


    @Override
    protected ReplyPresenter createPresenter() {
        return new ReplyPresenter(this);
    }

    @Override
    public void hideLoading() {
        mReplySwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onRefresh() {
        mvpPresenter.loadReplyGroupData(String.valueOf(SPUtil.getTutorIdfromSP(mActivity)));
    }

    @Override
    public void loadSucceed(List<ReplyGroup> replyGroups) {
        mAdapter.addList(replyGroups);
    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (mvpPresenter != null){
                mvpPresenter.loadReplyGroupFromDB();
            }
        }
    }
}
