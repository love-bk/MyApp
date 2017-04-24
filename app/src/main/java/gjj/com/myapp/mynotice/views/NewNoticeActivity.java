package gjj.com.myapp.mynotice.views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;

public class NewNoticeActivity extends BaseActivity {

    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.noticeTitle_et)
    EditText mNoticeTitleEt;
    @BindView(R.id.ddresseeaTv)
    TextView mDdresseeaTv;
    @BindView(R.id.addressorTv)
    TextView mAddressorTv;
    @BindView(R.id.timeTv)
    TextView mTimeTv;
    @BindView(R.id.content_et)
    EditText mContentEt;
    @BindView(R.id.send)
    Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);
        ButterKnife.bind(this);
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText("发送通知");
    }

    @OnClick({R.id.ddresseeaTv, R.id.send, R.id.back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ddresseeaTv:
                break;
            case R.id.send:
                hideSoftKeyboard(view);
                break;
            case R.id.back_iv:
                onBackPressed();
                break;

        }
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



}
