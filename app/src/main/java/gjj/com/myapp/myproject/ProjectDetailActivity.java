package gjj.com.myapp.myproject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.presenter.ProjectPresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.views.HomeView;
import gjj.com.myapp.views.ProjectView;

public class ProjectDetailActivity extends MvpActivity<ProjectPresenter> implements ProjectView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.title_Tv)
    TextView mTitleNameTv;
    @BindView(R.id.subtitleTv)
    TextView mSubtitleTv;
    @BindView(R.id.projectTypeTv)
    TextView mProjectTypeTv;
    @BindView(R.id.projectFidelityTv)
    TextView mProjectFidelityTv;
    @BindView(R.id.projectFromTv)
    TextView mProjectFromTv;
    @BindView(R.id.yearTv)
    TextView mYearTv;
    @BindView(R.id.majorTv)
    TextView mMajorTv;
    @BindView(R.id.approveStateTv)
    TextView mApproveStateTv;
    @BindView(R.id.student_nameTv)
    TextView mStudentNameTv;
    @BindView(R.id.contentTv)
    TextView mContentTv;
    @BindView(R.id.basicRequirementTv)
    TextView mBasicRequirementTv;
    @BindView(R.id.basicSkillTv)
    TextView mBasicSkillTv;
    @BindView(R.id.referenceTv)
    TextView mReferenceTv;
    @BindView(R.id.back_iv)
    ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }

    private void initData() {
        long projectId = getIntent().getLongExtra(Constants.PROJECT_ID, 0);
        mvpPresenter.loadProjectFromDb(projectId);

    }

    private void initTitle() {
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText("我的报题详情");
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    public void loadSucceed(List<GraduateProject> projects) {
        GraduateProject project = projects.get(0);
        mTitleNameTv.setText(project.getTitle());
        mSubtitleTv.setText(project.getSubTitle());
        mProjectTypeTv.setText(project.getProjectType());
        mProjectFidelityTv.setText(project.getProjectFidelity());
        mProjectFromTv.setText(project.getProjectFrom());
        mYearTv.setText(project.getYear());
        mMajorTv.setText(project.getMajor());
        if (project.getAuditByDirector()) {
            mApproveStateTv.setText("已审核");
        } else {
            mApproveStateTv.setText("未审核");
        }
        if (project.getStudent() == null) {
            mStudentNameTv.setText("该课题还未分配学生");
            mStudentNameTv.setTextColor(Color.RED);
        } else {
            mStudentNameTv.setText(project.getStudent().getName());
        }
        mContentTv.setText(project.getContent());
        mBasicRequirementTv.setText(project.getBasicRequirement());
        mBasicSkillTv.setText(project.getBasicSkill());
        mReferenceTv.setText(project.getReference());

    }

    @Override
    public void loadFail(String msg) {

    }
}
