package gjj.com.myapp.myreply.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.myreply.adapter.ReplyStudentRecyclerViewAdapter;

public class ReplyDetailActivity extends BaseActivity {

    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.replyTeamName_tv)
    TextView mReplyTeamNameTv;
    @BindView(R.id.replyLeader_tv)
    TextView mReplyLeaderTv;
    @BindView(R.id.replyMember_tv)
    TextView mReplyMemberTv;
    @BindView(R.id.replyPlace_tv)
    TextView mReplyPlaceTv;
    @BindView(R.id.replyMajorTv)
    TextView mReplyMajorTv;
    @BindView(R.id.startTimeTv)
    TextView mStartTimeTv;
    @BindView(R.id.endTimeTv)
    TextView mEndTimeTv;
    @BindView(R.id.replyStudentRecyclerView)
    RecyclerView mReplyStudentRecyclerView;
    @BindView(R.id.replyScrollView)
    ScrollView mReplyScrollView;
    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_detail);
        ButterKnife.bind(this);
        initTitle();
        initData();
        initView();
    }

    private void initView() {
        mReplyScrollView.smoothScrollTo(0,0);
        mReplyStudentRecyclerView.setLayoutManager(new AutoLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mReplyStudentRecyclerView.setNestedScrollingEnabled(false);
        mReplyStudentRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ReplyStudentRecyclerViewAdapter mAdapter = new ReplyStudentRecyclerViewAdapter(this, mData);
        mAdapter.setOnItemClickListener(new ReplyStudentRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(ReplyDetailActivity.this, ReplyScoreDetailActivity.class));
            }
        });
        mReplyStudentRecyclerView.setAdapter(mAdapter);
    }

    private void initTitle() {
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText("答辩小组信息");
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            mData.add(" 第" + i + "个item");
        }
    }
}
