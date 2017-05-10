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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.ReplyGroup;
import gjj.com.myapp.myreply.adapter.ReplyStudentRecyclerViewAdapter;
import gjj.com.myapp.presenter.ReplyPresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.TimeUtils;
import gjj.com.myapp.views.ReplyView;

public class ReplyDetailActivity extends MvpActivity<ReplyPresenter> implements ReplyView {

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
    private ReplyStudentRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_detail);
        ButterKnife.bind(this);
        initTitle();
        initView();
        initData();
    }

    @Override
    protected ReplyPresenter createPresenter() {
        return new ReplyPresenter(this);
    }

    private void initView() {
        mReplyScrollView.smoothScrollTo(0,0);
        mReplyStudentRecyclerView.setLayoutManager(new AutoLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mReplyStudentRecyclerView.setNestedScrollingEnabled(false);
        mReplyStudentRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ReplyStudentRecyclerViewAdapter(this);
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
        long replygroupId = getIntent().getLongExtra(Constants.REPLYGROUPID, 0);
        mvpPresenter.loadReplyGroupFromDb(replygroupId);
    }

    @Override
    public void loadSucceed(List<ReplyGroup> replyGroups) {
        ReplyGroup replyGroup = replyGroups.get(0);
        mReplyTeamNameTv.setText(replyGroup.getDescription());
        mReplyLeaderTv.setText(replyGroup.getLeader_name());
        mReplyPlaceTv.setText(replyGroup.getLocation());
        mReplyMajorTv.setText(replyGroup.getMajor());
        List<GraduateProject> projects = replyGroup.getGraduateProjects();
        if (projects != null&&projects.size()!=0){
            String members = "";
            for (GraduateProject project : projects) {
                if (project.getStudent_name()!=null){
                    members = members+project.getStudent_name().getName()+"、";
                }
            }
            if (members.contains("、")){
                mReplyMemberTv.setText(members.substring(0,members.lastIndexOf("、")));
            }
            mAdapter.addList(projects);
        }
        mStartTimeTv.setText(TimeUtils.formatTimeInMillis(replyGroup.getBeginTime()));
        mEndTimeTv.setText(TimeUtils.formatTimeInMillis(replyGroup.getEndTime()));
    }

    @Override
    public void loadFail(String msg) {

    }
}
