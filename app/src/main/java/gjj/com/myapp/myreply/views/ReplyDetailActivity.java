package gjj.com.myapp.myreply.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
import gjj.com.myapp.model.Student;
import gjj.com.myapp.model.Tutor;
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
//    @BindView(R.id.replyMajorTv)
//    TextView mReplyMajorTv;
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
    private int requestCode = 0;

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
            public void onItemClick(View view, int position,Long projecId) {
                Intent intent = new Intent(ReplyDetailActivity.this, ReplyScoreDetailActivity.class);
                intent.putExtra(Constants.PROJECT_ID,projecId);
                intent.putExtra(Constants.POSITION,position);
                startActivityForResult(intent,requestCode);
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
        if (TextUtils.isEmpty(replyGroup.getLocation())){
            mReplyPlaceTv.setText("未设置");
            mReplyPlaceTv.setTextColor(Color.RED);
        }else {
            mReplyPlaceTv.setText(replyGroup.getLocation());
        }
//        mReplyMajorTv.setText(replyGroup.getMajor());
        List<GraduateProject> projects = replyGroup.getGraduateProjects();
        List<Tutor> tutors = replyGroup.getTutorId();
        if (tutors != null&&tutors.size()!=0){
            String members = "";
            for (Tutor tutor : tutors) {
                if (tutor.getName()!=null){
                    members = members+tutor.getName()+"、";
                }
            }
            if (members.contains("、")){
                mReplyMemberTv.setText(members.substring(0,members.lastIndexOf("、")));
            }
        }
        if (projects!=null&&projects.size()!=0){
            mAdapter.addList(projects);
        }
        if (replyGroup.getBeginTime() == -1){
            mStartTimeTv.setText("未设置");
            mStartTimeTv.setTextColor(Color.RED);
        }else{
            mStartTimeTv.setText(TimeUtils.formatTimeInMillis(replyGroup.getBeginTime()));
        }
        if (replyGroup.getEndTime()==-1){
            mEndTimeTv.setText("未设置");
            mEndTimeTv.setTextColor(Color.RED);
        }else{
            mEndTimeTv.setText(TimeUtils.formatTimeInMillis(replyGroup.getEndTime()));
        }
    }

    @Override
    public void loadFail(String msg) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (0 == requestCode){
            int scoreState = data.getIntExtra(Constants.SCORESTATE,-1);
            int position = data.getIntExtra(Constants.POSITION,-1);
            mAdapter.getData().get(position);
            Toast.makeText(mActivity, "返回了", Toast.LENGTH_SHORT).show();
        }
    }
}
