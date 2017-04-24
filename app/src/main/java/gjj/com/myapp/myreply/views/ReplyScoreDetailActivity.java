package gjj.com.myapp.myreply.views;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;

public class ReplyScoreDetailActivity extends BaseActivity {

    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.nameTv)
    TextView mNameTv;
    @BindView(R.id.sexTv)
    TextView mSexTv;
    @BindView(R.id.worker_numTv)
    TextView mWorkerNumTv;
    @BindView(R.id.departmentTv)
    TextView mDepartmentTv;
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

    private static final String[] PLANETS = new String[]{"0","1","2","3","4","5","6","7","8","9","10"};
    private String selectScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_score_detail);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText("答辩打分");
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }

    @OnClick({R.id.score01_tv, R.id.score02_tv, R.id.score03_tv, R.id.score04_tv})
    public void onViewClicked(View view) {
        View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(2);
        wv.setItems(Arrays.asList(PLANETS));
        wv.setSeletion(Integer.valueOf(((TextView) view).getText().toString()));
        wv.setView((TextView) view);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item,TextView view) {
                view.setText(item);
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("请选择分数")
                .setView(outerView)
                .setPositiveButton("OK", null)
                .show();
//        switch (view.getId()) {
//            case R.id.score01_tv:
//                mScore01Tv.setText(selectScore);
//                break;
//            case R.id.score02_tv:
//                mScore02Tv.setText(selectScore);
//                break;
//            case R.id.score03_tv:
//                mScore03Tv.setText(selectScore);
//                break;
//            case R.id.score04_tv:
//                mScore04Tv.setText(selectScore);
//                break;
//            default:
//                break;
//        }
    }
}
