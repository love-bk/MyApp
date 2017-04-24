package gjj.com.myapp.mynotice.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.utils.Constants;

public class MyNoticeDetailActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.noticeTitle_Tv)
    TextView mNoticeTitleTv;
    @BindView(R.id.ddresseeaTv)
    TextView mDdresseeaTv;
    @BindView(R.id.addressorTv)
    TextView mAddressorTv;
    @BindView(R.id.timeTv)
    TextView mTimeTv;
    @BindView(R.id.content_tv)
    TextView mContentTv;
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notice_detail);
        ButterKnife.bind(this);
        initData();
        mBackIv.setVisibility(View.VISIBLE);
    }

    private void initData() {

        flag = getIntent().getStringExtra(Constants.NOTICE_FLAG);
        switch (flag) {
            case Constants.FORWARD_MESSAGE:
                mTitleTv.setText("我发送的通知");
                break;
            case Constants.GET_MESSAGE:
                mTitleTv.setText("我收到的通知");
                break;
            default:
                break;
        }

    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }
}
