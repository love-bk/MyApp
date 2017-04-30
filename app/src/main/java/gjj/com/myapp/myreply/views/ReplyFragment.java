package gjj.com.myapp.myreply.views;


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
import gjj.com.myapp.R;
import gjj.com.myapp.myreply.adapter.ReplyRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.replyRecyclerView)
    RecyclerView mReplyRecyclerView;
    @BindView(R.id.reply_swipe_refresh)
    SwipeRefreshLayout mReplySwipeRefresh;
    private List<String> mData = new ArrayList<>();

    public ReplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_defense_rating, container, false);
        ButterKnife.bind(this, view);
        initData();
        mReplyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mReplyRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ReplyRecyclerViewAdapter mAdapter = new ReplyRecyclerViewAdapter(getActivity(), mData);
        mAdapter.setOnItemClickListener(new ReplyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), ReplyDetailActivity.class));
            }
        });
        mReplyRecyclerView.setAdapter(mAdapter);
        mReplySwipeRefresh.setOnRefreshListener(this);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            mData.add("pager" + " 第" + i + "个item");
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //结束后停止刷新
                mReplySwipeRefresh.setRefreshing(false);
            }
        }, 3000);
    }
}
