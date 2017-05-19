package gjj.com.myapp.myreply.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.Student;
import gjj.com.myapp.presenter.ProjectPresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.views.ProjectView;

public class ReplyScoreDetailActivity extends MvpActivity<ProjectPresenter> implements ProjectView {

    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.nameTv)
    TextView mNameTv;

    @BindView(R.id.score01_tv)
    TextView mScore01Tv;
    @BindView(R.id.score02_tv)
    TextView mScore02Tv;
    @BindView(R.id.score03_tv)
    TextView mScore03Tv;
    @BindView(R.id.score04_tv)
    TextView mScore04Tv;
    @BindView(R.id.total_tv)
    TextView mTotalTv;
    @BindView(R.id.studentNameTv)
    TextView mStudentNameTv;
    @BindView(R.id.classNameTv)
    TextView mClassNameTv;
    @BindView(R.id.noTv)
    TextView mNoTv;
    private static final String[] PLANETS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    @BindView(R.id.saveBtn)
    Button mSaveBtn;
    @BindView(R.id.submitBtn)
    Button mSubmitBtn;

    private int scoreState = 0;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_score_detail);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }

    private void initData() {
        long projectId = getIntent().getLongExtra(Constants.PROJECT_ID, -1);
        position = getIntent().getIntExtra(Constants.POSITION, -1);
        if (projectId != -1) {
            //加载数据
            mvpPresenter.loadProjectFromDb(projectId);
        }
    }

    private void initTitle() {
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText("答辩打分");
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }

    @OnClick({R.id.score01_tv, R.id.score02_tv, R.id.score03_tv, R.id.score04_tv,R.id.saveBtn, R.id.submitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.saveBtn:
                //将数据保存到数据库中
                Toast.makeText(mActivity, "保存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submitBtn:
                //提交分数
                Toast.makeText(mActivity, "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.score01_tv:
            case R.id.score02_tv:
            case R.id.score03_tv:
            case R.id.score04_tv:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(2);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(Integer.valueOf(((TextView) view).getText().toString()));
                wv.setView((TextView) view);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item, TextView view) {
                        view.setText(item);
                    }
                });

                new AlertDialog.Builder(this)
                        .setTitle("请选择分数")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intTotalScore();
                            }
                        })
                        .show();
                break;
        }
    }

    /**
     * 赋值总分
     */
    private void intTotalScore() {
        Integer score01 = Integer.valueOf(mScore01Tv.getText().toString());
        Integer score02 = Integer.valueOf(mScore02Tv.getText().toString());
        Integer score03 = Integer.valueOf(mScore03Tv.getText().toString());
        Integer score04 = Integer.valueOf(mScore04Tv.getText().toString());
        mTotalTv.setText(String.valueOf(score01+score02+score03+score04));
    }

    @Override
    public void loadSucceed(List<GraduateProject> projects) {
        if (projects != null && projects.size() != 0) {
            GraduateProject project = projects.get(0);
            mNameTv.setText(project.getTitle());
            Student student = project.getStudent();
            if (student != null) {
                mStudentNameTv.setText(student.getName());
                mClassNameTv.setText(student.getStudentClass());
                mNoTv.setText(student.getNo());
                int completenessScoreByGroup = project.getCompletenessScoreByGroup();
                int qualityScoreBtGroup = project.getQualityScoreBtGroup();
                int replyScoreByGroup = project.getReplyScoreByGroup();
                int correctnessScoreByGroup = project.getCorrectnessScoreByGroup();
                mScore01Tv.setText(String.valueOf(completenessScoreByGroup));
                mScore02Tv.setText(String.valueOf(qualityScoreBtGroup));
                mScore03Tv.setText(String.valueOf(replyScoreByGroup));
                mScore04Tv.setText(String.valueOf(correctnessScoreByGroup));
                mTotalTv.setText(String.valueOf(completenessScoreByGroup + qualityScoreBtGroup + replyScoreByGroup + correctnessScoreByGroup));
            }
        }
    }

    @Override
    public void loadFail(String msg) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constants.SCORESTATE,scoreState);
        intent.putExtra(Constants.POSITION,position);
        this.setResult(RESULT_OK,intent);
        finish();
    }
}
